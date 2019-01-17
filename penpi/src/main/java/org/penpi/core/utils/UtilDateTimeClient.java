package org.penpi.core.utils;

import java.util.Date;

public class UtilDateTimeClient {
    public static final String DATE_PATTERN = "yyyy-MM-dd";
    public static final String MM_TIME_PATTERN = "HH:mm";
    public static final String TIME_PATTERN = "HH:mm:ss";

//	private static long MILIS_PER_DAY = 86400000;
	
	private UtilDateTimeClient(){}

	@SuppressWarnings("deprecation")
	public static final String convertDateTimeToString(Date aDate, final String datePattern) {
		String result = new String(datePattern);
		result = result.replaceFirst("yyyy", Integer.toString(aDate.getYear() > 1900 ?aDate.getYear() : 1900 + aDate.getYear())); //有的浏览器client的getyear为1900开始。。。
		result = result.replaceFirst("yy", Integer.toString(aDate.getYear()).substring(0, 2));
		result = result.replaceFirst("MM", (aDate.getMonth() < 9 ? "0" : "") + Integer.toString(aDate.getMonth() + 1));
		result = result.replaceFirst("dd", (aDate.getDate() < 10 ? "0" : "") + Integer.toString(aDate.getDate()));
		result = result.replaceFirst("HH", (aDate.getHours() < 10 ? "0" : "") + Integer.toString(aDate.getHours()));
		result = result.replaceFirst("mm", (aDate.getMinutes() < 10 ? "0" : "") + Integer.toString(aDate.getMinutes()));
		result = result.replaceFirst("ss", (aDate.getSeconds() < 10 ? "0" : "") + Integer.toString(aDate.getSeconds()));
		return result;
    }

	public static final String convertDateTimeToMmString(Date aDate) {
		return convertDateTimeToString(aDate, DATE_PATTERN + " " + MM_TIME_PATTERN);
	}

	public static final String convertDateTimeToString(Date aDate) {
		return convertDateTimeToString(aDate, DATE_PATTERN + " " + TIME_PATTERN);
	}

	public static final String convertDateToString(Date aDate, final String datePattern) {
		return convertDateTimeToString(aDate, datePattern);
	}

	public static final String convertDateToString(Date aDate) {
		return convertDateTimeToString(aDate, DATE_PATTERN);
	}

    /**
     * 尝试自动识别yyyy-MM-dd及yyyy-M-d及yyyyMMdd及后面空格的时间字符串及yyyyMMddHHmmss的格式
     * @param strDateTime
     * @return
     */
    @SuppressWarnings("deprecation")
	public static Date convertStringToDateTime(String strDateTime){
    	try{
    		if(strDateTime == null){
    			return null;
    		}
    		String[] spaceSplit = strDateTime.split(" ");
    		String dateTest = (spaceSplit.length == 2 ? spaceSplit[0] : strDateTime);
    		String timeTest = (spaceSplit.length == 2 ? spaceSplit[1] : "");
    		String[] dateSplit = dateTest.split("-");
    		if(dateSplit.length == 1){ //为compact串
    			switch(dateTest.length()){
    				case 8: //yyyyMMdd
    					return new Date(
    						Integer.parseInt(dateTest.substring(0, 4)) - 1900,
    						Integer.parseInt(dateTest.substring(4, 6)) - 1,
    						Integer.parseInt(dateTest.substring(6, 8))
    					);
    				case 14: //yyyyMMddHHmmss
    					return new Date(
    						Integer.parseInt(dateTest.substring(0, 4)) - 1900,
    						Integer.parseInt(dateTest.substring(4, 6)) - 1,
    						Integer.parseInt(dateTest.substring(6, 8)),
    						Integer.parseInt(dateTest.substring(8, 10)),
    						Integer.parseInt(dateTest.substring(10, 12)),
    						Integer.parseInt(dateTest.substring(12, 14))
    					);
    				default:
    					return null;
    			}
    		}else{ //非compact
    			if(dateSplit.length != 3){ //格式不正确
    				return null;
    			}
    			if(Integer.parseInt(dateSplit[0]) > 2999 || Integer.parseInt(dateSplit[1]) > 12 || Integer.parseInt(dateSplit[1]) > 31){
    				return null;
    			}
    			if("".equals(timeTest)){ //yyyy-MM-dd
					return new Date(
						Integer.parseInt(dateSplit[0]) - 1900,
						Integer.parseInt(dateSplit[1]) - 1,
						Integer.parseInt(dateSplit[2])
   					);    				
    			}else{ //yyyy-MM-dd
    				String[] timeSplit = timeTest.split(":");
    				if(timeSplit.length == 3){
            			if(Integer.parseInt(timeSplit[0]) > 24 || Integer.parseInt(timeSplit[1]) > 60 || Integer.parseInt(timeSplit[2]) > 60){
            				return null;
            			}
        				return new Date(
    						Integer.parseInt(dateSplit[0]) - 1900,
    						Integer.parseInt(dateSplit[1]) - 1,
    						Integer.parseInt(dateSplit[2]),
    						Integer.parseInt(timeSplit[0]),
    						Integer.parseInt(timeSplit[1]),
    						Integer.parseInt(timeSplit[2])
        				);
    				}else if(timeSplit.length == 2){
            			if(Integer.parseInt(timeSplit[0]) > 24 || Integer.parseInt(timeSplit[1]) > 60){
            				return null;
            			}
        				return new Date(
    						Integer.parseInt(dateSplit[0]) - 1900,
    						Integer.parseInt(dateSplit[1]) - 1,
    						Integer.parseInt(dateSplit[2]),
    						Integer.parseInt(timeSplit[0]),
    						Integer.parseInt(timeSplit[1]),
    						0
        				);
    				}else{
    					return null;
    				}
    			}
    		}
    	}catch(Exception e){
    		return null;
    	}
    }

}

