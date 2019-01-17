package org.penpi.core.commons.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.IOUtils;
import org.apache.commons.jxpath.JXPathContext;
import org.apache.commons.jxpath.JXPathNotFoundException;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

/**
 * 循环列表EXCEL填充.
 * 通过xpath来确定对象数据.
 */
public class ListExcelWriter<T> {
	
    private Logger logger = Logger.getLogger(this.getClass().getName());
	
	int defaultColumnWidth;
	short defaultRowHeight;
	private List<Integer> columnWidthList = new ArrayList<Integer>();
	private List<CellStyle> titleStyle = new ArrayList<CellStyle>();
	private List<CellStyle> rowStyle = new ArrayList<CellStyle>();
	private List<RichTextString> titleText = new ArrayList<RichTextString>();
	private List<String> dataTemplata = new ArrayList<String>(); 

    private static Pattern pattern = Pattern.compile("\\{\\=(.+?)\\}");

	public ListExcelWriter(String templeteFilePath) throws IOException {
		FileInputStream fileInputStream = new FileInputStream(templeteFilePath);
		Workbook templateBook = new HSSFWorkbook(new POIFSFileSystem(fileInputStream));
		try{
			Sheet templateSheet = templateBook.getSheetAt(0);
			if (templateSheet.getLastRowNum() < 2) {
				throw new IllegalArgumentException("模版格式不正确");
			}
	
			Row titleRow = templateSheet.getRow(0);
	    	defaultColumnWidth = templateSheet.getDefaultColumnWidth();
	    	defaultRowHeight = templateSheet.getDefaultRowHeight();
	
	    	Iterator<Cell> cellIterator = titleRow.cellIterator();
	    	for(int i= 0; cellIterator.hasNext(); i ++){
	    		Cell titleCell = cellIterator.next();
	    		titleStyle.add(titleCell.getCellStyle());
	    		
	    		columnWidthList.add(templateSheet.getColumnWidth(i));
	    		titleText.add(titleCell.getRichStringCellValue());
	    	}
	
			Row dataRow = templateSheet.getRow(1);
			cellIterator = dataRow.cellIterator();
	    	while(cellIterator.hasNext()){
	    		Cell cell = cellIterator.next();
	    		rowStyle.add(cell.getCellStyle());
	    		dataTemplata.add(cell.getStringCellValue());
	    	}
		}finally{
			templateBook.close();
		}
	}

	private Row fillRows(Sheet outputSheet, T rowData, List<CellStyle> rowStyles, int rowNum){
    	JXPathContext objectContext = JXPathContext.newContext(rowData);
    	Row dataRow = outputSheet.createRow(1 + rowNum);
    	for(int i = 0; i < rowStyle.size(); i ++){
			Cell outputCell = dataRow.createCell(i);
			outputCell.setCellStyle(rowStyles.get(i));
			
			String templateCellValue = dataTemplata.get(i);
			Matcher matcher = pattern.matcher(templateCellValue);
			Map<String, Object> replacerMap = new HashMap<String, Object>();
			while(matcher.find()){
				String xpath = matcher.group(1);
				Object valueObj = null;
				try{
					valueObj = objectContext.getValue(xpath);
				}catch(JXPathNotFoundException e){
					//forget it
				}
				replacerMap.put(matcher.group(0), valueObj == null ? "" : valueObj);
			}
			if(replacerMap.size() == 1 && templateCellValue.equals(replacerMap.keySet().iterator().next())){
                outputCellSetTypeAndValue(replacerMap,outputCell);
				continue;
			}else{
				for(Entry<String, Object> entry: replacerMap.entrySet()){
					templateCellValue = templateCellValue.replace(entry.getKey(), entry.getValue().toString());
				}
			}

    		
			outputCell.setCellType(Cell.CELL_TYPE_STRING);
			outputCell.setCellValue(templateCellValue);
    	}
    	return dataRow;
	}
	
    public void fillToFile(List<T> objectList, String outputFilePath){
		FileOutputStream fileOutputStream = null;
    	Workbook workbook = new HSSFWorkbook();
    	try{
	    	Sheet outputSheet = workbook.createSheet();
	
	    	outputSheet.setDefaultColumnWidth(defaultColumnWidth);
	    	outputSheet.setDefaultRowHeight(defaultRowHeight);
	    	
	    	for(int i = 0; i < columnWidthList.size(); i ++){
	        	outputSheet.setColumnWidth(i, (int)(columnWidthList.get(i) * 1.15));
	    	}
	
	    	List<CellStyle> rowStyles = new ArrayList<CellStyle>();
	    	Row titleRow = outputSheet.createRow(0);
	    	for(int i = 0; i < titleStyle.size(); i ++){
				Cell cell = titleRow.createCell(i);
	    		CellStyle style = workbook.createCellStyle();
				style.cloneStyleFrom(titleStyle.get(i));
				cell.setCellStyle(style);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(titleText.get(i));
				
	    		CellStyle astyle = workbook.createCellStyle();
	    		astyle.cloneStyleFrom(rowStyle.get(i));
	    		rowStyles.add(astyle);
	    	}
	    	for(int j = 0; j < objectList.size(); j ++){
	    		fillRows(outputSheet, objectList.get(j), rowStyles, j);
	    	}
	    	
            File file = new File(outputFilePath);
            //父目录不存在则创建
            if(!file.getParentFile().exists()){
                file.getParentFile().mkdirs();
            }

			fileOutputStream = new FileOutputStream(file);
			workbook.write(fileOutputStream);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		} finally {
			IOUtils.closeQuietly(fileOutputStream);
			IOUtils.closeQuietly(workbook);
		}
	}

    private void outputCellSetTypeAndValue(Map<String, Object> replacerMap,Cell outputCell){
        Object valueObj = replacerMap.values().iterator().next();
        if(valueObj instanceof Integer){
            outputCell.setCellType(Cell.CELL_TYPE_NUMERIC);
            outputCell.setCellValue((Integer)valueObj);
        }
        else if(valueObj instanceof Double){
            outputCell.setCellType(Cell.CELL_TYPE_NUMERIC);
            outputCell.setCellValue((Double)valueObj);
        }else{
            outputCell.setCellType(Cell.CELL_TYPE_STRING);
            outputCell.setCellValue(valueObj.toString());
        }
    }
}
