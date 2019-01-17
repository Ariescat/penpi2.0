package org.penpi.subsys;

public interface Global {
	
	String INDEX_NAME = "index_penpi";
	String CACHE_NAME = "cache_penpi";
	
	String SERVICE_CLASS_SUFFIX = "Service";
	String SEARCH_SERVICE_CLASS_SUFFIX = "SearchService";
	
	//---文件系统 start---
	int DEFAULT_SMALL_PICT_SIZE = 150; //默认小图大小
	int PICT_QUALITY_SCALE_SIZE = 200; //小于200的采用100质量压缩

	String UPLOAD_DIR = "upload";
	String SMALL_PICT_DIR = "smallpict";
	String TEMP_DIR = "temp";
	String INIT_IMAGE_DIR = "initImage";
	
	String SMALL_PICT_DEFAULT_DIR = "default"; 
	String SMALL_PICT_COMMON_DIR = "common"; 
	String DEFAULT_SMALL_PICT = "no-data.png";
	
	String SMALL_PICT_SIZE_SPLIT_CHAR = "x";
	String DEFAULT_TEXT_SPLIT_CHAR = ",";
	
	//4个entity需要自动保存到文件系统的变量命名后缀
	String PICT_ID_FIELD_SUFFIX = "PictId";
	String PICT_IDS_FIELD_SUFFIX = "PictIds";
	String FILEINF_ID_FIELD_SUFFIX = "fileInfId";
	String FILEINF_IDS_FIELD_SUFFIX = "fileInfIds";
	//---文件系统 end---
	
	double DEFAULT_LNG = 113.36967;
	double DEFAULT_LAT = 23.132384;
	
	
	String SESSION_LOGIN_MEMBER = "login_member"; //客户端登录用户
	
	String SESSION_LOGIN_USER = "login_user";
	String LOGIN_MESSAGE = "message";
	
	Integer size = 6;  ////设置每页显示的数据数
	
}
