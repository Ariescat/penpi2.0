package org.penpi.core.commons.exception;

/**
 * 业务逻辑异常封装类，当在业务逻辑处理时出现异常，可抛出此异常，由全局定义的BusinessExceptionHandler处理。
 *  if (service.isContractDuplicated(contractNo))
 *	  throw new BusinessException("bizErr.duplicatedContractNo",new String[]{contractNo});//根据具体情况，传入的参数最多可至4个
 * 常见的参数构建含义：
 * code 错误码. 可以根据该字符串判断做出不同的异常处理方式。也是国际化资源key
 * args 参数. 当code对应的资源有{number}占位符时，将该字符串序列依次替换内容
 * message 自定义的异常message. 异常toString的输出内容
 * useSimpleLog 是否使用简单日志. 如果是非系统跟踪性的常规异常，只输出异常的内容。减少日志
 * viewData 错误对象. 当采用MVC页面跳转时，可以将该数据带到错误页面，提示更多的信息
 *  
 * @author Jim Wu 
 * @since 1.0
 */
public class BusinessException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7970756494558100175L;

    private String code;
	private Object[] args = new Object[0];
    private Object viewData; //to be presented in the exception error page,will be stored as request attribute with key of Constants.RESULT_KEY
    /**
     * 决定了日志是否需要输出全部，例如登陆错误，可以不需要记录详细stacktrace
     */
    private boolean useSimpleLog;

    public Object getViewData() {
        return viewData;
    }

    public void setViewData(Object viewData) {
        this.viewData = viewData;
    }

    @Override
	public boolean equals(Object obj) {
    	if(obj == null){
    		return false;
    	}
    	if(!(obj instanceof BusinessException)){
    		return false;
    	}
    	BusinessException targetBusinessException = (BusinessException)obj;
    	if(!targetBusinessException.getCode().equals(this.getCode())){
    		return false;
    	}
    	Object[] targetArgs = targetBusinessException.getArgs();
    	if(getArgs().length != targetArgs.length){
    		return false;
    	}
    	if(getArgs().length == 0){
    		return true;
    	}
    	for(int i = 0; i < getArgs().length; i ++){
    		if(!getArgs()[i].equals(targetArgs[i])){
    			return false;
    		}
    	}
    	return true;
	}

	@Override
	public int hashCode() { //NOSONAR
		return super.hashCode(); //NOSONAR
	}

	public String getCode(){
		return code;
	}

	public Object[] getArgs(){
		return args;
	}

	public BusinessException(String code, boolean useSimpleLog){
		this.code = code;
		this.useSimpleLog = useSimpleLog;
	}

	public BusinessException(String code){
		this.code = code;
	}

	public BusinessException(String code,Object[] args){
		this(code, args, true);
	}
	
	public BusinessException(String code,Object[] args, boolean useSimpleLog){
		this.code = code ;
		this.args = args;
		this.useSimpleLog = useSimpleLog;
	}

	public BusinessException(String code,Object[] args, String message, boolean useSimpleLog){
		super(message);
		this.code = code;
		this.args = args;
		this.useSimpleLog = useSimpleLog;
	}

	public BusinessException(String code,Throwable cause){
		super(cause);
		this.code = code;
	}

	public BusinessException(String code,Object[] args,String message,Throwable cause){
		super(message,cause);
		this.code = code;
		this.args = args;
	}

    public BusinessException(String code,Object viewData){
		this.code = code;
		this.viewData = viewData;
	}

	public BusinessException(String code,Object[] args,Object viewData){
		this.code = code ;
		this.args = args;
        this.viewData = viewData;
	}

	public BusinessException(String code,Object[] args,String message,Object viewData){
		super(message);
		this.code = code;
		this.args = args;
        this.viewData = viewData;
	}

	public BusinessException(String code,Throwable cause,Object viewData){
		super(cause);
		this.code = code;
        this.viewData = viewData;
	}

	public BusinessException(String code,Object[] args,String message,Throwable cause,Object viewData){
		super(message,cause);
		this.code = code;
		this.args = args;
        this.viewData = viewData;
	}

	public boolean isUseSimpleLog() {
		return useSimpleLog;
	}

	public void setUseSimpleLog(boolean useSimpleLog) {
		this.useSimpleLog = useSimpleLog;
	}
}