package asteroids.programs.expressions;

import asteroids.part3.programs.SourceLocation;
import asteroids.programs.Executable;
import asteroids.programs.exceptions.IllegalTypeException;

public class NotExpression extends UnaryExpression<Boolean>{

	/**
	 * Create an expression that represents the logical not operation.
	 */
	public NotExpression(Expression<Boolean> expression, SourceLocation sourceLocation) {
		super(Boolean.class, expression, sourceLocation);
	}
	
	public NotExpression(Expression<Boolean> expression) {
		this(expression, null);
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
	public Literal<Boolean> eval(Executable parentExecutor) {
		Literal<?> evaluatedExpression = getExpression().eval(parentExecutor);
		
		if (evaluatedExpression instanceof NullType)
			throw new IllegalTypeException(Boolean.class, NullType.class);
		if (!(evaluatedExpression.getReturnType().equals(Boolean.class)))
			throw new IllegalTypeException(Boolean.class, evaluatedExpression.getClass());
		
		Boolean result = (Boolean)evaluatedExpression.getValue(parentExecutor);
		return new Literal<Boolean>(Boolean.class, !result);
	}

}
