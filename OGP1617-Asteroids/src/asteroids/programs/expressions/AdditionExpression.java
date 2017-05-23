package asteroids.programs.expressions;

import asteroids.part3.programs.SourceLocation;
import asteroids.programs.Executable;
import asteroids.programs.exceptions.IllegalTypeException;

public class AdditionExpression extends BinaryExpression<Double>{

	public AdditionExpression(Expression<Double> leftHandSide, Expression<Double> rightHandSide, SourceLocation sourceLocation) {
		super(Double.class, leftHandSide, rightHandSide, sourceLocation);
		
	}
	
	public AdditionExpression(Expression<Double> leftHandSide, Expression<Double> rightHandSide) {
		this(leftHandSide, rightHandSide, null);
	}

	@Override
	public Literal<Double> eval(Executable parentExecutor) {
		Literal<?> leftHandEvaluated = getLeftHandSide().eval(parentExecutor);
		Literal<?> rightHandEvaluated = getRightHandSide().eval(parentExecutor);
		
		// Check if one of the two represents an NullLiteral
		if (leftHandEvaluated instanceof NullType || rightHandEvaluated instanceof NullType) {
			throw new IllegalTypeException(Double.class, NullType.class);
		}
		
		if (!(leftHandEvaluated.getReturnType().equals(Double.class)))
			throw new IllegalTypeException(Double.class, leftHandEvaluated.getReturnType());
		if (!(rightHandEvaluated.getReturnType().equals(Double.class)))
			throw new IllegalTypeException(Double.class, rightHandEvaluated.getReturnType());
		
		Double leftValue = (Double)leftHandEvaluated.getValue(parentExecutor);
		Double rightValue = (Double)rightHandEvaluated.getValue(parentExecutor);
		
		return new Literal<Double>(Double.class, Double.valueOf(leftValue+rightValue));
	}

}
