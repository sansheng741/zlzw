package service.myException;

public class MyDBException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MyDBException() {
		super();
		
	}

	public MyDBException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		
	}

	public MyDBException(String message, Throwable cause) {
		super(message, cause);
		
	}

	public MyDBException(String message) {
		super(message);
		
	}

	public MyDBException(Throwable cause) {
		super(cause);
		
	}

}
