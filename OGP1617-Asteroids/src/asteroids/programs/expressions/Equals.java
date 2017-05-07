package asteroids.programs.expressions;

public class Equals extends LogicalExpression {

	/**
	 * Initialize this comparison with a given leftHandOperator and rightHandOperator.
	 * This comparison is represented as:
	 * 		leftHandOperator == rightHandOperator
	 * 
	 * @param leftHandOperator
	 * 			The left hand operator of this comparison.
	 * @param rightHandOperator
	 * 			The right hand operator of this comparison.
	 */
	public Equals(Expression leftHandOperator, Expression rightHandOperator){
		this.leftHandOperator = leftHandOperator;
		this.rightHandOperator = rightHandOperator;
	}
	
	/**
	 * This constructor should not be used.
	 * @throws IllegalStateException
	 * 			Always
	 * 			| true
	 */
	public Equals(){
		throw new IllegalStateException("Cannot create an equals expression without any operants.");
	}
	
	/**
	 * Return the left hand operator of this comparison
	 */
	public Expression getLeftHandOperator(){
		return this.leftHandOperator;
	}
	
	/**
	 * Variable registering the leftHandOperator of this comparison. 
	 */
	private final Expression leftHandOperator;
	
	/**
	 * Return the right hand operator of this comparison.
	 */
	public Expression getRightHandOperator(){
		return this.rightHandOperator;
	}
	
	/**
	 * Variable registering the rightHandOperator of this comparison.
	 */
	private final Expression rightHandOperator;
	
	@Override
	public boolean eval() {
		// TODO Auto-generated method stub
		return false;
	}

}