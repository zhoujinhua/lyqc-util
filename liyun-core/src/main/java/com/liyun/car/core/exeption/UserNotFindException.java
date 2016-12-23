package com.liyun.car.core.exeption;


/**
 * @author majing E-mail:qept@sina.com
 * @createTime：2011-5-24 上午09:50:32
 * @version Revision: 1.0
 * @description 用户不存在抛出此异常。
 */
public class UserNotFindException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public UserNotFindException() {
		super();
	}
	
    public UserNotFindException(String message) {
    	super(message);
    }

    public UserNotFindException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserNotFindException(Throwable cause) {
        super(cause);
    }
	
	

}
