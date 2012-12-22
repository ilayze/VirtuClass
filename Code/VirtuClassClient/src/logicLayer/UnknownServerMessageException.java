/**
 * 
 */
package logicLayer;

/**
 * @author Raz
 *
 */
public class UnknownServerMessageException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1011283447852238542L;
	
	public UnknownServerMessageException() { super(); }
	public UnknownServerMessageException(String message) { super(message); }
	public UnknownServerMessageException(String message, Throwable cause) { super(message, cause); }
	public  UnknownServerMessageException(Throwable cause) { super(cause); }
	
}
