package cn.tedu.store.service.ex;
/**
 * 商品id不存在
 * @author JAVA
 *
 */
public class ProductNotFoundException extends ServiceException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6109047540146573846L;

	public ProductNotFoundException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProductNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public ProductNotFoundException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public ProductNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public ProductNotFoundException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	

}
