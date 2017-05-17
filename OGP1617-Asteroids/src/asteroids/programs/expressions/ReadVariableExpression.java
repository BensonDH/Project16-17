package asteroids.programs.expressions;

import asteroids.part3.programs.SourceLocation;
import asteroids.programs.*;

public class ReadVariableExpression<T> extends Expression {
	
	public ReadVariableExpression(String variableName, SourceLocation sourceLocation) {
		super(sourceLocation);
		this.variableName = variableName;
	}
	
	/**
	 * Return the name of this ReadVariableExpression.
	 */
	public String getVariableName(){
		return this.variableName;
	}
	
	/**
	 * Variable registering the name of this VariableExpression
	 */
	private final String variableName;
	
	@Override
	public T eval() {
		// TODO implementation
		return null;
	}

}
