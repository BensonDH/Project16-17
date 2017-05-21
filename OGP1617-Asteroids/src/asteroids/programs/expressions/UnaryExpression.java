package asteroids.programs.expressions;

import asteroids.part3.programs.SourceLocation;

public abstract class UnaryExpression<T> extends Expression<T> {

	public UnaryExpression(Expression<?> expression, SourceLocation sourceLocation) {
		super(sourceLocation);
		this.expression = expression;
	}

	public UnaryExpression(Expression<?> expression){
		super(null);
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
