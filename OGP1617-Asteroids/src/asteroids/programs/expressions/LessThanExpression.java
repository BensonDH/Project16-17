package asteroids.programs.expressions;

import asteroids.part3.programs.SourceLocation;
import asteroids.programs.Executable;
import asteroids.programs.exceptions.IllegalTypeException;

public class LessThanExpression extends BinaryExpression<Boolean>{

	public LessThanExpression(Expression<Double> leftHandSide, Expression<Double> rightHandSide, SourceLocation sourceLocation) {
		super(Boolean.class, leftHandSide, rightHandSide, sourceLocation);
	}

	public LessThanExpression(Expression<Double> leftHandSide, Expression<Double> rightHandSide) {
			this(leftHandSide, rightHandSide, null);
	}
	
	@Override
	public Literal<Boolean> eval(Executable parentExecutor) {
		Literal<?> leftHandEvaluated = getLeftHandSide().eval(parentExecutor);
		Literal<?> rightHandEvaluated = getRightHandSide().eval(parentExecutor);
		
		if (leftHandEvaluated instanceof NullType || rightHandEvaluated instanceof NullType)
			throw new IllegalTypeException(Double.class, NullType.class);
		
		if (!(leftHandEvaluated.getReturnType().equals(Double.class)))
			throw new IllegalTypeException(Double.class, leftHandEvaluated.getReturnType());
		if (!(rightHandEvaluated.getReturnType().equals(Double.class)))
			throw new IllegalTypeException(Double.class, rightHandEvaluated.getReturnType());
		
		Double leftValue = (Double)leftHandEvaluated.getValue(parentExecutor);
		Double rightValue = (Double)rightHandEvaluated.getValue(parentExecutor);
		
		return new Literal<Boolean>(Boolean.class, leftValue < rightValue);
	}

}
