package cn.tedu.store.service.ex;
/**
 * 当前数据是否是归属当前用户
 * @author JAVA
 *
 */
public class AccessDeniedException extends ServiceException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8709687206968657194L;

	public AccessDeniedException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AccessDeniedException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public AccessDeniedException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public AccessDeniedException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public AccessDeniedException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	
}
