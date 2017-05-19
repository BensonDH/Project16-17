package asteroids.programs.expressions;

import asteroids.part3.programs.SourceLocation;
import asteroids.programs.Program;
import asteroids.programs.exceptions.IllegalTypeException;

public class AdditionExpression extends BinaryExpression implements ReturnTypeDouble {

	public AdditionExpression(Expression leftHandSide, Expression rightHandSide, SourceLocation sourceLocation) {
		super(leftHandSide, rightHandSide, sourceLocation);
		
		if (!(leftHandSide instanceof ReturnTypeDouble))
			throw new IllegalTypeException(ReturnTypeDouble.class, leftHandSide.getClass());
		if (!(rightHandSide instanceof ReturnTypeDouble))
			throw new IllegalTypeException(ReturnTypeDouble.class, rightHandSide.getClass());
	}
	
	public AdditionExpression(Expression leftHandSide, Expression rightHandSide) {
		this(leftHandSide, rightHandSide, null);
	}

	@Override
	public Literal eval(Program parentProgram) {
		Literal leftHandEvaluated = getLeftHandSide().eval(parentProgram);
		Literal rightHandEvaluated = getRightHandSide().eval(parentProgram);
		
		if (!(leftHandEvaluated instanceof DoubleLiteralExpression))
			throw new IllegalTypeException(DoubleLiteralExpression.class, leftHandEvaluated.getClass());
		if (!(rightHandEvaluated instanceof DoubleLiteralExpression))
			throw new IllegalTypeException(DoubleLiteralExpression.class, rightHandEvaluated.getClass());
		
		Double leftValue = ((DoubleLiteralExpression)leftHandEvaluated).getValue(parentProgram);
		Double rightValue = ((DoubleLiteralExpression)rightHandEvaluated).getValue(parentProgram);
		
		return new DoubleLiteralExpression(leftValue+rightValue);
	}

}
