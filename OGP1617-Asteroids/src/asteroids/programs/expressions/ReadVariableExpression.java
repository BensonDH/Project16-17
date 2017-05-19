package asteroids.programs.expressions;

import asteroids.part3.programs.SourceLocation;
import asteroids.programs.*;
import asteroids.programs.exceptions.VariableException;

public class ReadVariableExpression extends Expression implements ReturnTypeBoolean, ReturnTypeDouble, ReturnTypeEntity {
	
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
	public Literal eval(Program parentProgram) {
		Variable<?> tempVar = parentProgram.findGlobalVariable(getVariableName());
		
		if (tempVar == null)
			throw new VariableException(getVariableName());
		
		return tempVar.getValue();
	}

}
