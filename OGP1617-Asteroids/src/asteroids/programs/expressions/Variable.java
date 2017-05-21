package asteroids.programs.expressions;

public class Variable<T extends Literal<?>> {
	
	/**
	 * Create a  new Variable with the given variableName and value.
	 * 
	 * @param variableName
	 * 		The name of this Variable.
	 * @param value
	 * 		The value that this variable has to recall.
	 */
	public Variable(String variableName, Literal<?> value){
		this.variableName = variableName;
		this.variableValue= value;
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
	public Literal<?> getValue(){
		return this.variableValue;
	}
	
	public Class<?> getLiteralType(){
		return getValue().getLiteralType();
	}
	
	/**
	 * Variable registering the value of this Variable.
	 */
	private final Literal<?> variableValue;
}
