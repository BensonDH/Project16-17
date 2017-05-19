package asteroids.programs.exceptions;

public class SyntaxException extends RuntimeException {

	public SyntaxException(String reason){
		this.reason = reason;
	}
	
	/**
	 * Returns the reason why this SyntaxException was thrown
	 */
	public String getReason(){
		return this.reason;
	}
	
	private final String reason;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
