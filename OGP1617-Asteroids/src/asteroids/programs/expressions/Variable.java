package asteroids.programs.expressions;

public class Variable<T extends Literal> {
	
	/**
	 * Create a  new Variable with the given variableName and value.
	 * 
	 * @param variableName
	 * 		The name of this Variable.
	 * @param value
	 * 		The value that this variable has to recall.
	 */
	public Variable(Class<T> variableType, String variableName, T value){
		this.variableType = variableType;
		this.variableName = variableName;
		setValue(value);
	}
	
	/**
	 * Return the name of this Variable.
	 */
	public String getName(){
		return this.variableName;
	}
	
	/**
	 * Variable registering the name of this Variable.
	 */
	private final String variableName;
	
	/**
	 * Return the value of this Variable.
	 */
	public Literal getValue(){
		return this.variableValue;
	}
	
	/**
	 * Set this Variable's value to the given newValue.
	 * 
	 * @param newValue
	 * 		The new value of this Variable.
	 */
	public void setValue(T newValue){
		this.variableValue = newValue;
	}
	
	/**
	 * Variable registering the value of this Variable.
	 */
	private T variableValue;
	
	/**
	 * Return the Type of this variable.
	 */
	public Class<T> getVariableType() {
		return this.variableType;
	}
	
	/**
	 * Variable registering the Type T of this class
	 */
	private final Class<T> variableType;
}
