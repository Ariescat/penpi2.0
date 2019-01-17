package org.penpi.subsys;

public interface ControllerMapping {

	String APP_BASE = "app/";
	String ADMIN_BASE = "/admin/";

	//Controller
	String APP_USER = APP_BASE + "user";
	String APP_ORDER = APP_BASE + "order";
	String APP_TOPIC = APP_BASE + "topic";
	String APP_NOTE = APP_BASE + "note";
	String APP_LOGIN = APP_BASE + "login";
	String APP_IMAGE = APP_BASE + "image";

	String AMIN_LOGIN = ADMIN_BASE + "login";
	String ADMIN_USER = ADMIN_BASE + "user";
	String ADMIN_UI = ADMIN_BASE + "ui";
	String ADMIN_OPLOG = ADMIN_BASE+"oplog";
	String ADMIN_ORDER = ADMIN_BASE + "order";
	String ADMIN_ORDER_Excep_Record = ADMIN_BASE+"orderexcep";
	String ADMIN_TOPIC = ADMIN_BASE+"topic";
	String ADMIN_NOTE = ADMIN_BASE+"note";
	String ADMIN_MSG = ADMIN_BASE+"msg";
	String ADMIN_UPLOAD = ADMIN_BASE + "upload";
	
	//方法
	String ADMIN_HANDLE_LOGIN = "handlelogin.do";
	String ADMIN_MAIN = "main.do";
	String ADMIN_FIND_ALL_USER = "findalluser.do";
	String ADMIN_FIND_USERS_BY_PAGE = "findusersbypage.do";
	String ADMIN_FIND_USERS_BY_CONDITIONS = "findusersbyconditions.do";
	String ADMIN_UPDATE_USERS = "updateuser.do";
	String ADMIN_DELETE_USERS = "deleteuser.do";	
	String ADMIN_UPDATE_USERSTATCODE = "updateUserStatCode.do";
	String ADMIN_OPERATE_USER_LOG = "operateUserLog.do";
	String ADMIN_OPERATE_USER_LOG_BY_PAGE = "findOpUserLogByPage.do";
	String ADMIN_OPERATE_USER_LOG_SELECT_BY_OPER = "findOpUserLogByOper.do";
	String ADMIN_TO_PRINT_USER = "toPrintUser.do";
	String ADMIN_PRINT_USER = "printUser.do";
	String ADMIN_FIND_ALL_ORDERS = "findallorders.do";
	String ADMIN_FIND_ORDERS_BY_SEND_USER = "findOrdersBySendUser.do";
	String ADMIN_SET_EXCEPTION_ORDER = "setExcepOrder.do";
	String ADMIN_FIND_ALL_ORDERS_EXCEP_RECORD = "findallordersExcepRecord.do";
	String ADMIN_FIND_AN_ORDER = "findAnOrder.do";
	String ADMIN_REPAIR_EXCEP_RECORD = "repairOrder.do";
	String ADMIN_TO_PRINT_ORDERS = "toPrintOrders.do";
	String ADMIN_PRINT_ORDER = "printOrder.do";
	String ADMIN_TOPIC_MAIN = "topicMain.do";
	String ADMIN_TOPIC_DETAIL = "topicDetail.do";
	String ADMIN_TO_USER_STATISTIC = "toUserStatistic.do";
	String ADMIN_USER_SEX_STATISTIC = "sexStatistic.do";
	String ADMIN_USER_STAT_STATISTIC = "userStatStatistic.do";
	String ADMIN_TO_ORDER_STATISTIC = "toOrderStatistic.do";
	String ADMIN_ORDER_STAT_STATISTIC = "statStatistic.do";
	String ADMIN_NOTE_DETAIL = "noteDetail.do";
	String ADMIN_NOTE_DELETE = "deleteNote.do";
	String ADMIN_MODIFY_TOPIC = "modifyTopic.do";
	String ADMIN_FIND_TOPIC_BY_KEY_WORD = "findTopicByKeyWord.do";
	String ADMIN_TO_MAIN_TOPIC = "mainTopic.do";
	String ADMIN_TO_TOPIC_STATISTIC = "toTopicStatistic.do";
	String ADMIN_TOPIC_TYPE_STATISTIC = "topicTypeStatistic.do";
	String ADMIN_MSG_LIST = "messageList.do";
	String ADMIN_MSG_PUBLISH = "publishMsg.do";
	String ADMIN_FIND_BY_MSG_TYPE = "findByMsgType.do";
	String ADMIN_NOTE_COMMENT_DELETE = "deleteNoteComment.do";
	String ADMIN_FIND_NOTE_BY_KEY_WORD = "findNoteByKeyWord.do";
	String ADMIN_FILE_UPLOAD = "fileUpload.do";
	String ADMIN_TO_UPLOAD_FILE = "toFileUpload.do";
	
	//图片上传
	String IMAGE_UPLOAD = "imageUpload";
	
	//订单
	String GET_ORDERS = "getOrders";
	String GET_ORDERS_BY_PARAMS = "getOrdersByParams";
	String SEND_ORDER = "sendOrder";
	String GRAB_ORDER = "grabOrder";
	String QUERY_ORDERBY_ID = "queryOrdersById";
	
	//话题
	String GET_ALL_TOPIC_CATEGORYS = "getTopicCategorys";
	String GET_TOPICS_BY_CATEGORYID = "getTopics";
	String GET_TOPIC_DETAIL = "getTopicDetail";
	String GET_TOPIC_BIG_IMAGE = "getTopicBigImage";
	String GET_NOTE_DETAIL = "getNoteDetail";
	String GET_NOTE_BIG_IMAGE = "getNoteBigImage";
	String CREATE_TOPIC = "createTopic";
	String CREATE_NOTE = "createNote";
	String SEARCH = "search";
	String COMMENT = "comment";
	String REPLY = "reply";
	String TOPIC_FOLLOWER = "topicFollower";
	String NOTE_FOLLOWER = "noteFollower";
	String QUERY_NOTE_BY_ID = "queryNoteById";
	
	//登陆
	String MEMBER_LOGIN = "memberLogin";
	String RE_MEMBER_LOGIN = "reMemberLogin";
	String GET_VALIDATE_CODE = "getValidateCode";

	
	
	
	
	

}
