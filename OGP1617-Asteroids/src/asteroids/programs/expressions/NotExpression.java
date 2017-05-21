package asteroids.programs.expressions;

import asteroids.part3.programs.SourceLocation;
import asteroids.programs.Program;
import asteroids.programs.exceptions.IllegalTypeException;

public class NotExpression extends UnaryExpression<Boolean>{

	/**
	 * Create an expression that represents the logical not operation.
	 */
	public NotExpression(Expression<Boolean> expression, SourceLocation sourceLocation) {
		super(expression, sourceLocation);
	}
	
	public NotExpression(Expression<Boolean> expression) {
		super(expression, null);
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
	public Literal<Boolean> eval(Program parentProgram) {
		Literal<?> evaluatedExpression = getExpression().eval(parentProgram);
		
		if (evaluatedExpression instanceof NullType)
			throw new IllegalTypeException(Boolean.class, NullType.class);
		if (!(evaluatedExpression.getLiteralType().equals(Boolean.class)))
			throw new IllegalTypeException(Boolean.class, evaluatedExpression.getClass());
		
		Boolean result = (Boolean)evaluatedExpression.getValue(parentProgram);
		return new Literal<Boolean>(Boolean.class, !result);
	}

}
