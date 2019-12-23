package cn.tedu.store.controller.ex;

/**
 * 没有选择文件，或选择的是0字节的文件
 */
public class FileEmptyException extends FileUploadException {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 5216328143694529891L;

	public FileEmptyException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FileEmptyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public FileEmptyException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public FileEmptyException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public FileEmptyException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	
	
	
}
