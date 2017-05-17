package asteroids.programs.expressions;

import asteroids.part3.programs.SourceLocation;
import asteroids.programs.*;

public class ChangeSignExpression extends Expression {
	
	public ChangeSignExpression(Expression expression, SourceLocation sourceLocation) {
		super(sourceLocation);
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
	private Expression expression;
	
	@Override
	public Double eval() {
		return (Double)getExpression().eval();
	}

}
