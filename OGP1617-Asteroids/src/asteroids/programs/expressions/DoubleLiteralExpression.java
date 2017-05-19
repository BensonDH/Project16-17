package asteroids.programs.expressions;

import asteroids.part3.programs.SourceLocation;
import asteroids.programs.Program;

public class DoubleLiteralExpression extends Literal implements ReturnTypeDouble {

	public DoubleLiteralExpression(Double value, SourceLocation sourceLocation) {
		super(sourceLocation);
		this.value = value;
	}
	
	public DoubleLiteralExpression(Double value) {
		super(null);
		this.value = value;
	}

	@Override
	public Literal eval(Program parentProgram) {
		return this;
	}

	@Override
	public Class<?> getLiteralType(){
		return Double.class;
	}

	public Double getValue(){
		return this.value;
	}
	
	@Override
	public Double getValue(Program parentProgram) {
		return this.value;
	}

	/**
	 * Variable registering the value of this DoubleLiteral
	 */
	private final Double value;
	
	@Override
	public String toString(){
		return getValue().toString();
	}
}
