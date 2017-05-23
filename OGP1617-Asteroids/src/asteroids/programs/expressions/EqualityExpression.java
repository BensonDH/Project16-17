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
		super(Boolean.class, leftHandOperator, rightHandOperator, sourceLocation);
	}
	
	public EqualityExpression(Expression<?> leftHandOperator, Expression<?> rightHandOperator){
		super(Boolean.class, leftHandOperator, rightHandOperator, null);
	}
	
	/**
	 * This constructor should not be used.
	 * @throws IllegalStateException
	 * 			Always
	 * 			| true
	 */
	public EqualityExpression(){
		super(null, null, null, null);
		throw new IllegalStateException("Cannot create an equals expression without any operants.");
	}
	
	
	@Override
	public Literal<Boolean> eval(Executable parentExecutor) {
		Literal<?> leftHandEvaluated = getLeftHandSide().eval(parentExecutor);
		Literal<?> rightHandEvaluated = getRightHandSide().eval(parentExecutor);
		
		if (leftHandEvaluated instanceof NullType) {
			if (rightHandEvaluated instanceof NullType)
				return new Literal<Boolean>(Boolean.class, true);
			else
				return new Literal<Boolean>(Boolean.class, false);
		}
		
		// If both literals are not of the same type, they can never be equal.
		if (!(leftHandEvaluated.getReturnType().equals(rightHandEvaluated.getReturnType())))
			return new Literal<Boolean>(Boolean.class, false);
		
		Boolean result = leftHandEvaluated.getValue(parentExecutor).equals(rightHandEvaluated.getValue(parentExecutor));
		
		return new Literal<Boolean>(Boolean.class, result);
	}

}
