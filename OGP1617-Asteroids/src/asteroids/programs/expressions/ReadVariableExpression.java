package asteroids.programs.expressions;

import asteroids.part3.programs.SourceLocation;
import asteroids.programs.*;
import asteroids.programs.exceptions.VariableException;

public class ReadVariableExpression extends Expression<Object> {
	
	public ReadVariableExpression(String variableName, SourceLocation sourceLocation) {
		super(Object.class, sourceLocation);
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
	public Literal<Object> eval(Executable parentExecutor) {
		Literal<?> tempVar = parentExecutor.findVariable(getVariableName());
		
		if (tempVar == null)
			throw new VariableException(getVariableName());
		
		return (Literal<Object>) tempVar;
	}

}
