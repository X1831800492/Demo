package cn.tedu.store.service.ex;
/**
 * 收货地址条数超出数量
 * @author JAVA
 *
 */
public class AddressCountLimitException extends ServiceException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 212741039716856010L;

	public AddressCountLimitException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AddressCountLimitException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public AddressCountLimitException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public AddressCountLimitException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public AddressCountLimitException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	
}
