package asteroids.programs.exceptions;

public class VariableException extends RuntimeException {
	
	public VariableException(String variableName){
		this.VariableName = variableName;
	}	
	
	/**
	 * Returns the variable name that did not exist when this
	 * exception was thrown.
	 */
	public String getVariableName(){
		return this.VariableName;
	}
	
	/**
	 * Variable registering the name of the variable that did not
	 * exist when this exception was thrown.
	 */
	private final String VariableName;
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
}
