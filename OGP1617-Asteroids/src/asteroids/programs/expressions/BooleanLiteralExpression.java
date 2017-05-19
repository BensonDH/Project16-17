package asteroids.programs.expressions;

import asteroids.part3.programs.SourceLocation;
import asteroids.programs.Program;

public class BooleanLiteralExpression extends Literal implements ReturnTypeBoolean {

	public BooleanLiteralExpression(SourceLocation sourceLocation, Boolean value) {
		super(sourceLocation);
		this.value = value;
	}
	
	public BooleanLiteralExpression(Boolean value) {
		super(null);
		this.value = value;
	}
	
	@Override
	public Literal eval(Program parentProgram) {
		return this;
	}

	@Override
	public Class<?> getLiteralType(){
		return Boolean.class;
	}
	
	public Boolean getValue(){
		return this.value;
	}
	
	@Override
	public Boolean getValue(Program parentProgram) {
		return this.value;
	}

	/**
	 * Variable registering the value of this Boolean.
	 */
	private final Boolean value;
	
	@Override
	public String toString(){
		return getValue().toString();
	}
}
