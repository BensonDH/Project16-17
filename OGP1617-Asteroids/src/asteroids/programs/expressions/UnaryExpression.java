package asteroids.programs.expressions;

import asteroids.part3.programs.SourceLocation;

public abstract class UnaryExpression<T> extends Expression<T> {

	public UnaryExpression(Class<T> returnType, Expression<?> expression, SourceLocation sourceLocation) {
		super(returnType, sourceLocation);
		this.expression = expression;
	}

	public UnaryExpression(Class<T> returnType, Expression<?> expression){
		super(returnType, null);
		this.expression = expression;
	}
	
	/**
	 * Get the expression of this UnaryExpression.
	 */
	public Expression<?> getExpression() {
		return this.expression;
	}
	
	/**
	 * Variable registering the expression in this UnaryExpression.
	 */
	private final Expression<?> expression;

}