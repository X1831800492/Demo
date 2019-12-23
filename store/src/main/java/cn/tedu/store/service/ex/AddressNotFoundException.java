package cn.tedu.store.service.ex;
/**
 * 收货地址是否存在
 * @author JAVA
 *
 */
public class AddressNotFoundException extends ServiceException {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4234461510773896049L;

	public AddressNotFoundException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AddressNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public AddressNotFoundException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public AddressNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public AddressNotFoundException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	
}
