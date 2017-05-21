package asteroids.programs.expressions;

import asteroids.part3.programs.SourceLocation;
import asteroids.programs.Program;
import asteroids.programs.exceptions.IllegalTypeException;

public class NotExpression extends UnaryExpression implements ReturnTypeBoolean {

	/**
	 * Create an expression that represents the logical not operation.
	 */
	public NotExpression(Expression expression, SourceLocation sourceLocation) {
		super(expression, sourceLocation);
		
		if (!(expression instanceof ReturnTypeBoolean))
				throw new IllegalTypeException(ReturnTypeBoolean.class, expression.getClass());
	}
	
	/**
	 * This Constructor should not be used.
	 * @throws IllegalStateException
	 */
	public NotExpression() throws IllegalStateException {
		super(null, null);
		throw new IllegalStateException("Cannot create a NotExpression without any expression");
	}
	
	@Override
	public Literal eval(Program parentProgram) {
		Literal evaluatedExpression = getExpression().eval(parentProgram);
		
		if (!(evaluatedExpression instanceof BooleanLiteralExpression))
			throw new IllegalTypeException(BooleanLiteralExpression.class, evaluatedExpression.getClass());
		
		Boolean result = ((BooleanLiteralExpression)evaluatedExpression).getValue(parentProgram);
		return new BooleanLiteralExpression(!result);
	}

}
