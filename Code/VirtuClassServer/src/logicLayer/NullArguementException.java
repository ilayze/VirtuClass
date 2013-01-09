package logicLayer;

public class NullArguementException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 886192629173852588L;

	
	public NullArguementException() { super(); }
	public NullArguementException(String message) { super(message); }
	public NullArguementException(String message, Throwable cause) { super(message, cause); }
	public NullArguementException(Throwable cause) { super(cause); }
}
