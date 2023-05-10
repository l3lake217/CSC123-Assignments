package Banking;

public class NoSuchAccountException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoSuchAccountException(String message) {
		 super(message);
	 }
}
