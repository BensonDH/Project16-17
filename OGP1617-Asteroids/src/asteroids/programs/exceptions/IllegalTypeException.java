package asteroids.programs.exceptions;

import asteroids.programs.expressions.Expression;

public class IllegalTypeException extends RuntimeException {

	public IllegalTypeException(Class<?> expectedType, Class<?> actualType){
		this.acutalType = actualType;
		this.expectedType = expectedType;
	}

	/**
	 * Get type that was expected
	 */
	public Class<?> getExpectedType() {
		return this.expectedType;
	}
	
	private final Class<?> expectedType;
	
	/**
	 * Get the actual class that was passed before this exception occured.
	 */
	public Class<?> getActualType() {
		return this.acutalType;
	}
	
	private final Class<?> acutalType;	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
}
