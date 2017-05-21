package asteroids.programs.expressions;

import asteroids.part3.programs.SourceLocation;
import asteroids.programs.Program;
import asteroids.programs.exceptions.IllegalTypeException;

public class LessThanExpression extends BinaryExpression<Boolean>{

	public LessThanExpression(Expression<Double> leftHandSide, Expression<Double> rightHandSide, SourceLocation sourceLocation) {
		super(leftHandSide, rightHandSide, sourceLocation);
	}

	public LessThanExpression(Expression<Double> leftHandSide, Expression<Double> rightHandSide) {
			this(leftHandSide, rightHandSide, null);
	}
	
	@Override
	public Literal<Boolean> eval(Program parentProgram) {
		Literal<?> leftHandEvaluated = getLeftHandSide().eval(parentProgram);
		Literal<?> rightHandEvaluated = getRightHandSide().eval(parentProgram);
		
		if (leftHandEvaluated instanceof NullType || rightHandEvaluated instanceof NullType)
			throw new IllegalTypeException(Double.class, NullType.class);
		
		if (!(leftHandEvaluated.getLiteralType().equals(Double.class)))
			throw new IllegalTypeException(Double.class, leftHandEvaluated.getLiteralType());
		if (!(rightHandEvaluated.getLiteralType().equals(Double.class)))
			throw new IllegalTypeException(Double.class, rightHandEvaluated.getLiteralType());
		
		Double leftValue = (Double)leftHandEvaluated.getValue(parentProgram);
		Double rightValue = (Double)rightHandEvaluated.getValue(parentProgram);
		
		return new Literal<Boolean>(Boolean.class, leftValue < rightValue);
	}

}
