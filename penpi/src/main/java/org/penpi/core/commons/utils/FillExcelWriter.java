package org.penpi.core.commons.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
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
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;

/**
 * 无规则固定模版EXCEL填充. 通过xpath来确定对象数据.
 */
public class FillExcelWriter {

	private Logger logger = Logger.getLogger(this.getClass().getName());

	private Workbook templateWorkbook;
	private static Pattern pattern = Pattern.compile("\\{\\=(.+?)\\}");

	public FillExcelWriter(String templeteFile) throws IOException {
		FileInputStream fileInputStream = new FileInputStream(templeteFile);
		templateWorkbook = new HSSFWorkbook(new POIFSFileSystem(fileInputStream));
		fileInputStream.close();
	}

	private void setOutputCellType(Cell templateCell, Cell outputCell, JXPathContext objectContext) {
		switch (templateCell.getCellType()) {
		case Cell.CELL_TYPE_FORMULA:
			outputCell.setCellType(Cell.CELL_TYPE_FORMULA);
			outputCell.setCellFormula(templateCell.getCellFormula());
			break;
		case Cell.CELL_TYPE_NUMERIC:
			outputCell.setCellType(Cell.CELL_TYPE_NUMERIC);
			outputCell.setCellValue(templateCell.getNumericCellValue());
			break;
		case Cell.CELL_TYPE_STRING:
			String templateCellValue = templateCell.getStringCellValue();
			Matcher matcher = pattern.matcher(templateCellValue);
			Map<String, Object> replacerMap = new HashMap<String, Object>();
			while (matcher.find()) {
				String xpath = matcher.group(1);
				Object valueObj = null;
				try {
					valueObj = objectContext.getValue(xpath);
				} catch (JXPathNotFoundException e) {
					// forget it
				}
				replacerMap.put(matcher.group(0), valueObj == null ? "" : valueObj);
			}
			if (replacerMap.size() == 1 && templateCellValue.equals(replacerMap.keySet().iterator().next())) {
				Object valueObj = replacerMap.values().iterator().next();
				if (valueObj instanceof Integer) {
					outputCell.setCellType(Cell.CELL_TYPE_NUMERIC);
					outputCell.setCellValue((Integer) valueObj);
				} else if (valueObj instanceof Double) {
					outputCell.setCellType(Cell.CELL_TYPE_NUMERIC);
					outputCell.setCellValue((Double) valueObj);
				} else {
					outputCell.setCellType(Cell.CELL_TYPE_STRING);
					outputCell.setCellValue(valueObj.toString());
				}
				break;
			} else {
				for (Entry<String, Object> entry : replacerMap.entrySet()) {
					templateCellValue = templateCellValue.replace(entry.getKey(), entry.getValue().toString());
				}
			}
			outputCell.setCellType(Cell.CELL_TYPE_STRING);
			outputCell.setCellValue(templateCellValue);
			break;
		default:
			outputCell.setCellType(Cell.CELL_TYPE_BLANK);
			outputCell.setCellValue("");
			break;
		}
	}

	public void fillToFile(Object object, String outFile) {
		JXPathContext objectContext = JXPathContext.newContext(object);
		FileOutputStream fileOutputStream = null;
		Workbook workbook = new HSSFWorkbook();
		try {
			Sheet outputSheet = workbook.createSheet();
			Sheet templateSheet = ensureOpenSheet(templateWorkbook);

			// 拷贝行列结构
			for (int i = 0; i < templateSheet.getNumMergedRegions(); i++) {
				CellRangeAddress region = templateSheet.getMergedRegion(i);
				outputSheet.addMergedRegion(region.copy());
			}

			outputSheet.setDefaultColumnWidth(templateSheet.getDefaultColumnWidth());
			outputSheet.setDefaultRowHeight(templateSheet.getDefaultRowHeight());
			for (int i = 0; i < 100; i++) {
				outputSheet.setColumnWidth(i, (int) (templateSheet.getColumnWidth(i) * 1.15));
			}

			// 拷贝行列数据
			for (int j = 0; j < templateSheet.getLastRowNum(); j++) {
				Row outputRow = outputSheet.createRow(j);
				Row templateRow = templateSheet.getRow(j);

				outputRow.setHeight(templateRow.getHeight());
				Iterator<Cell> cellIterator = templateRow.cellIterator();

				for (int i = 0; cellIterator.hasNext(); i++) {
					Cell outputCell = outputRow.createCell(i);
					Cell templateCell = cellIterator.next();
					CellStyle style = workbook.createCellStyle();
					style.cloneStyleFrom(templateCell.getCellStyle());
					outputCell.setCellStyle(style);

					setOutputCellType(templateCell, outputCell, objectContext);
				}
			}

			File excelFile = new File(outFile);
			if (!excelFile.getParentFile().exists()) {
				excelFile.getParentFile().mkdirs();// NOSONAR
			}
			fileOutputStream = new FileOutputStream(outFile);
			workbook.write(fileOutputStream);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		} finally {
			IOUtils.closeQuietly(fileOutputStream);
			IOUtils.closeQuietly(workbook);
		}

	}

	protected Sheet ensureOpenSheet(Workbook workbook) {
		int sheetIndex = workbook.getNumberOfSheets();
		if (sheetIndex == 0) {
			workbook.createSheet();
		}
		return workbook.getSheetAt(0);
	}
}
