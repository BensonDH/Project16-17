package asteroids.programs.expressions;

import asteroids.part3.programs.SourceLocation;
import asteroids.programs.Executable;
import asteroids.programs.exceptions.IllegalTypeException;

public class MultiplicationExpression extends BinaryExpression<Double>{

	public MultiplicationExpression(Expression<Double> leftHandSide, Expression<Double> rightHandSide, SourceLocation sourceLocation) {
		super(Double.class, leftHandSide, rightHandSide, sourceLocation);
	}
	
	public MultiplicationExpression(Expression<Double> leftHandSide, Expression<Double> rightHandSide) {
		this(leftHandSide, rightHandSide, null);
	}

	@Override
	public Literal<Double> eval(Executable parentExecutor) {
		Literal<?> leftSideEvaluated = getLeftHandSide().eval(parentExecutor);
		Literal<?> rightSideEvaluated = getRightHandSide().eval(parentExecutor);
		
		if (leftSideEvaluated instanceof NullType || rightSideEvaluated instanceof NullType)
			throw new IllegalTypeException(Double.class, NullType.class);
		if (!(leftSideEvaluated.getReturnType().equals(Double.class)))
			throw new IllegalTypeException(Double.class, leftSideEvaluated.getReturnType());
		if (!(rightSideEvaluated.getReturnType().equals(Double.class)))
			throw new IllegalTypeException(Double.class, rightSideEvaluated.getReturnType());
		
		Double leftValue = (Double)leftSideEvaluated.getValue(parentExecutor);
		Double rightValue = (Double)rightSideEvaluated.getValue(parentExecutor);
		
		return new Literal<Double>(Double.class, leftValue*rightValue);
	}

}