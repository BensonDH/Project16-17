package asteroids.programs.expressions;

import asteroids.part3.programs.SourceLocation;
import asteroids.programs.*;

public class EqualityExpression extends BinaryExpression<Boolean>{

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
	public EqualityExpression(Expression<?> leftHandOperator, Expression<?> rightHandOperator, SourceLocation sourceLocation){
		super(leftHandOperator, rightHandOperator, sourceLocation);
	}
	
	public EqualityExpression(Expression<?> leftHandOperator, Expression<?> rightHandOperator){
		super(leftHandOperator, rightHandOperator, null);
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
	public Literal<Boolean> eval(Program parentProgram) {
		Literal<?> leftHandEvaluated = getLeftHandSide().eval(parentProgram);
		Literal<?> rightHandEvaluated = getRightHandSide().eval(parentProgram);
		
		if (leftHandEvaluated instanceof NullType) {
			if (rightHandEvaluated instanceof NullType)
				return new Literal<Boolean>(Boolean.class, true);
			else
				return new Literal<Boolean>(Boolean.class, false);
		}
		
		// If both literals are not of the same type, they can never be equal.
		if (!(leftHandEvaluated.getLiteralType().equals(rightHandEvaluated.getLiteralType())))
			return new Literal<Boolean>(Boolean.class, false);
		
		Boolean result = leftHandEvaluated.getValue(parentProgram).equals(rightHandEvaluated.getValue(parentProgram));
		
		return new Literal<Boolean>(Boolean.class, result);
	}

}
