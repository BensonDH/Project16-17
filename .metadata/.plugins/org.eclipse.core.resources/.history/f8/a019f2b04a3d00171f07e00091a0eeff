package asteroids.programs.expressions;

import asteroids.part3.programs.SourceLocation;
import asteroids.programs.Program;
import asteroids.programs.exceptions.IllegalTypeException;

public class MultiplicationExpression extends BinaryExpression implements ReturnTypeDouble {

	public MultiplicationExpression(Expression leftHandSide, Expression rightHandSide, SourceLocation sourceLocation) {
		super(leftHandSide, rightHandSide, sourceLocation);
		
		if (!(leftHandSide instanceof ReturnTypeDouble))
			throw new IllegalTypeException(ReturnTypeDouble.class, leftHandSide.getClass());
		if (!(rightHandSide instanceof ReturnTypeDouble))
			throw new IllegalTypeException(ReturnTypeDouble.class, rightHandSide.getClass());
	}
	
	public MultiplicationExpression(Expression leftHandSide, Expression rightHandSide) {
		this(leftHandSide, rightHandSide, null);
	}

	@Override
	public Literal eval(Program parentProgram) {
		Literal leftSideEvaluated = getLeftHandSide().eval(parentProgram);
		Literal rightSideEvaluated = getRightHandSide().eval(parentProgram);
		
		if (!(leftSideEvaluated instanceof DoubleLiteralExpression))
			throw new IllegalTypeException(DoubleLiteralExpression.class, leftSideEvaluated.getClass());
		if (!(rightSideEvaluated instanceof DoubleLiteralExpression))
			throw new IllegalTypeException(DoubleLiteralExpression.class, rightSideEvaluated.getClass());
		
		Double leftValue = ((DoubleLiteralExpression)leftSideEvaluated).getValue(parentProgram);
		Double rightValue = ((DoubleLiteralExpression)rightSideEvaluated).getValue(parentProgram);
		
		return new DoubleLiteralExpression(leftValue*rightValue);
	}

}
