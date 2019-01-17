package org.penpi.core.commons.exception;

/**
 * 异常对象. 所有的失败情况尽量使用exception来完成，否则需要定义对应的DTO来返回相应数据.
 * errorData只有在非ajax方式页面跳转时来使用.
 */
public class ErrorObjectResp {

	public static final String ERROR_CODE = "errorCode";
	public static final String ERROR_TEXT = "errorText";
	public static final String ERROR_DATA = "errorData";

	private String errorCode;
	private String errorText;
	private Object errorData;

	public ErrorObjectResp(String errorCode, String errorText, Object errorData) {
		this.errorCode = errorCode;
		this.errorText = errorText;
		this.errorData = errorData;
	}

	public String getErrorCode() {
		return errorCode;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.tcstudy.client.dto.IErrorObject#setErrorCode(java.lang.String)
	 */
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.tcstudy.client.dto.IErrorObject#getErrorText()
	 */
	public String getErrorText() {
		return errorText;
	}

	public void setErrorText(String errorText) {
		this.errorText = errorText;
	}

	public Object getErrorData() {
		return errorData;
	}

	public void setErrorData(Object errorData) {
		this.errorData = errorData;
	}
}
