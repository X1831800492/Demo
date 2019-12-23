package cn.tedu.store.service.ex;
/**
 *  用户登录，用户名不存在异常
 */
public class UserNotFoundException extends ServiceException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7991875652328476596L;

	public UserNotFoundException() {
		super();
	
	}

	public UserNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		
	}

	public UserNotFoundException(String message, Throwable cause) {
		super(message, cause);
		
	}

	public UserNotFoundException(String message) {
		super(message);
	
	}

	public UserNotFoundException(Throwable cause) {
		super(cause);
	
	}
	
}
