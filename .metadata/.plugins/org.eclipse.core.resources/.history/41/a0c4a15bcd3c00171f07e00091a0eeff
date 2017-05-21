package asteroids.programs.expressions;

import asteroids.part3.programs.SourceLocation;

public abstract class BinaryExpression extends Expression {
	
	/**
	 * Create a new Binary Expression
	 * 
	 * Binary expressions are represented as:
	 * 	leftHandSide {Operator} rightHandSide
	 * 
	 * @param LeftHandSide
	 * 			The left hand side of this BinaryExpression.
	 * @param RightHandSide
	 * 			The right hand side of this BinaryExpression.
	 */
	public BinaryExpression(Expression leftHandSide, Expression rightHandSide, SourceLocation sourceLocation) {
		super(sourceLocation);
		
		this.leftHandExpression = leftHandSide;
		this.rightHandExpression = rightHandSide;
	}
	
	
	/**
	 * Return the left hand operator of this BinaryExpression.
	 */
	public Expression getLeftHandSide(){
		return this.leftHandExpression;
	}
	
	/**
	 * Variable registering the left hand operator of this BinaryExpression. 
	 */
	private final Expression leftHandExpression;

	/**
	 * Returns the right hand operator of this BinaryExpression.
	 * @return
	 */
	public Expression getRightHandSide(){
		return this.rightHandExpression;
	}
	
	/**
	 * Variable registering the right hand operator of this BinaryExpression.
	 */
	private final Expression rightHandExpression;
}
