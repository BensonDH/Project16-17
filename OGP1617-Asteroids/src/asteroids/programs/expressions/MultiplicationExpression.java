package asteroids.programs.expressions;

import asteroids.part3.programs.SourceLocation;
import asteroids.programs.Program;
import asteroids.programs.exceptions.IllegalTypeException;

public class MultiplicationExpression extends BinaryExpression<Double>{

	public MultiplicationExpression(Expression<Double> leftHandSide, Expression<Double> rightHandSide, SourceLocation sourceLocation) {
		super(leftHandSide, rightHandSide, sourceLocation);
	}
	
	public MultiplicationExpression(Expression<Double> leftHandSide, Expression<Double> rightHandSide) {
		this(leftHandSide, rightHandSide, null);
	}

	@Override
	public Literal<Double> eval(Program parentProgram) {
		Literal<?> leftSideEvaluated = getLeftHandSide().eval(parentProgram);
		Literal<?> rightSideEvaluated = getRightHandSide().eval(parentProgram);
		
		if (leftSideEvaluated instanceof NullType || rightSideEvaluated instanceof NullType)
			throw new IllegalTypeException(Double.class, NullType.class);
		if (!(leftSideEvaluated.getLiteralType().equals(Double.class)))
			throw new IllegalTypeException(Double.class, leftSideEvaluated.getLiteralType());
		if (!(rightSideEvaluated.getLiteralType().equals(Double.class)))
			throw new IllegalTypeException(Double.class, rightSideEvaluated.getLiteralType());
		
		Double leftValue = (Double)leftSideEvaluated.getValue(parentProgram);
		Double rightValue = (Double)rightSideEvaluated.getValue(parentProgram);
		
		return new Literal<Double>(Double.class, leftValue*rightValue);
	}

}
