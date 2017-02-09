package com.liyun.car.core.exeption;


public class YooliException extends Exception {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -5800211407233640418L;

	/**
	 * 根异常
	 */
	protected Throwable rootException = null;

	/**
	 * 一般错误
	 */
	public final static int GENERAL_ERROR = 1000;

	/**
	 * ISO8583报文格式错误
	 */
	public final static int FORMAT_ERROR = 1001;

	/**
	 * 通讯超时错误
	 */
	public final static int TIMEOUT_ERROR = 1003;

	/**
	 * 通讯IO错误
	 */
	public final static int IO_ERROR = 1004;

	/**
	 * 资源找不到
	 */
	public final static int RESOURCE_NOT_FOUND = 1005;
	
	/**
	 * SQL语句执行出错
	 */
	public final static int SQL_EXCUTE_ERROR = 1007;

	/**
	 * 数据库访问出错
	 */
	public final static int DB_ACCESS_ERROR = 1008;

	/**
	 * 必要域为空
	 */
	public final static int NECESSARY_NULL = 1009;

	/**
	 * 无效类型(不支持解析的类型)
	 */
	public final static int INVALID_TYPE = 1010;
	
	/**
	 * 计算结果异常
	 */
	public final static int COMPUTE_ERROR = 1011;
	
	/**
	 * 值不在范围内
	 */
	public final static int VALUE_RANGE_OUT = 1012;

	// 错误码
	protected int iCode;
	protected String code;
	protected String className;
	protected String methodName;
	protected String errorMsg;

	/**
	 * @param message
	 */
	public YooliException(int iCode, String errorMsg) {
		super();
		this.iCode = iCode;
		this.errorMsg = errorMsg;
	}

	/**
	 * @param message
	 */
	public YooliException(int iCode, String errorMsg, Throwable e) {
		super();
		this.iCode = iCode;
		this.errorMsg = errorMsg;
		initCause(e);
	}

	/**
	 * @param message
	 */
	public YooliException(String className, String methodName, String errorMsg, Throwable e) {
		super();
		this.className = className;
		this.methodName = methodName;
		this.errorMsg = errorMsg;
		initCause(e);
	}

	/**
	 * @param message
	 */
	public YooliException(String eCode, String className, String methodName, String errorMsg, Throwable e) {
		super();
		this.code = eCode;
		this.className = className;
		this.methodName = methodName;
		this.errorMsg = errorMsg;
		initCause(e);
	}

	/**
	 * @param message
	 */
	public YooliException(Throwable e) {
		super();
		initCause(e);
	}

	public Throwable getRootCause() {
		return rootException;
	}

	/**
	 * 设置异常的root异常
	 * 
	 * @param e 根异常
	 */
	public void setRootCause(Throwable e) {
		if (e != this) {
			rootException = e;
		}
	}

	public Throwable getCause() {
		return getRootCause();
	}

	public Throwable initCause(Throwable cause) {
		super.initCause(cause);
		setRootCause(cause);
		return this;
	}

	public String toString() {
		String answer = super.toString();
		answer = "ErrorCode:" + getCode() + "\n" + answer;
		if (rootException != null) {
			answer += " [Root exception is " + rootException + "]";
		}
		return answer;
	}

	/**
	 * @return Returns the Code.
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param errorCode The errorCode to set.
	 */
	public void setCode(String errorCode) {
		this.code = errorCode;
	}

	/**
	 * @return Returns the className.
	 */
	public String getClassName() {
		return className;
	}

	/**
	 * @param className The className to set.
	 */
	public void setClassName(String className) {
		this.className = className;
	}

	/**
	 * @return Returns the errorMsg.
	 */
	public String getErrorMsg() {
		return errorMsg;
	}

	/**
	 * @param errorMsg The errorMsg to set.
	 */
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	/**
	 * @return Returns the methodName.
	 */
	public String getMethodName() {
		return methodName;
	}

	/**
	 * @param methodName The methodName to set.
	 */
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	/**
	 * @return Returns the rootException.
	 */
	public Throwable getRootException() {
		return rootException;
	}

	/**
	 * @param rootException The rootException to set.
	 */
	public void setRootException(Throwable rootException) {
		this.rootException = rootException;
	}

	public int getiCode() {
		return iCode;
	}

	public void setiCode(int iCode) {
		this.iCode = iCode;
	}
}
