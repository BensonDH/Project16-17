package asteroids.programs.expressions;

import asteroids.part3.programs.SourceLocation;
import asteroids.programs.*;
import asteroids.programs.exceptions.IllegalTypeException;

public class ChangeSignExpression extends Expression implements ReturnTypeDouble {
	
	public ChangeSignExpression(Expression expression, SourceLocation sourceLocation) {
		super(sourceLocation);
		
		if (!(expression instanceof ReturnTypeDouble))
			throw new IllegalTypeException(ReturnTypeDouble.class, expression.getClass());
		this.expression = expression;
	}
	
	/**
	 * Return the expression whose sign has to be changed.
	 */
	public Expression getExpression(){
		return this.expression;
	}
	
	/**
	 * Variable registering the expression whose sign has to be. 
	 */
	private final Expression expression;
	
	@Override
	public Literal eval(Program parentProgram) {
		Literal evaluatedExpression = getExpression().eval(parentProgram);
		
		if (!(evaluatedExpression instanceof DoubleLiteralExpression))
			throw new IllegalTypeException(DoubleLiteralExpression.class, evaluatedExpression.getClass());
		
		Double newValue = -((DoubleLiteralExpression)evaluatedExpression).getValue(parentProgram);
		return new DoubleLiteralExpression(newValue);	
	}

}
