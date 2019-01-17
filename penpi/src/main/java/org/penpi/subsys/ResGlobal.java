package org.penpi.subsys;

public interface ResGlobal {

	String ERRORS_USER_DEFINED = "message"; // 自定义内容异常
	String ERRORS_EXCEPTION = "exception"; // 系统异常
	String ERRORS_SQL_ERROR = "sqlError"; // SQL异常

	String[] LOGIG_ILLEGAL = new String[] { "信息检验错误，请重登录" };
}
