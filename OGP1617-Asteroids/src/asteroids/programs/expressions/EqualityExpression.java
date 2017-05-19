package asteroids.programs.expressions;

import asteroids.model.Entity;
import asteroids.part3.programs.SourceLocation;
import asteroids.programs.*;

public class EqualityExpression extends BinaryExpression implements ReturnTypeBoolean{

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
	public EqualityExpression(Expression leftHandOperator, Expression rightHandOperator, SourceLocation sourceLocation){
		super(leftHandOperator, rightHandOperator, sourceLocation);
	}
	
	/**
	 * This constructor should not be used.
	 * @throws IllegalStateException
	 * 			Always
	 * 			| true
	 */
	public EqualityExpression(){
		super(null, null, null);
		throw new IllegalStateException("Cannot create an equals expression without any operants.");
	}
	
	
	@Override
	public Literal eval(Program parentProgram) {
		Literal leftHandEvaluated = getLeftHandSide().eval(parentProgram);
		Literal rightHandEvaluated = getRightHandSide().eval(parentProgram);
		
		// If both literals are not of the same type, they can never be equal.
		if (!(leftHandEvaluated.getClass().equals(rightHandEvaluated.getClass())))
			return new BooleanLiteralExpression(false);

		Boolean result = leftHandEvaluated.getValue(parentProgram).equals(rightHandEvaluated.getValue(parentProgram));
		return new BooleanLiteralExpression(result);

			
	}

}
