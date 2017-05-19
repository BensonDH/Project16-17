package asteroids.programs.expressions;

import asteroids.part3.programs.SourceLocation;
import asteroids.programs.Program;
import asteroids.programs.exceptions.IllegalTypeException;

public class LessThanExpression extends BinaryExpression implements ReturnTypeBoolean {

	public LessThanExpression(Expression leftHandSide, Expression rightHandSide, SourceLocation sourceLocation) {
		super(leftHandSide, rightHandSide, sourceLocation);
	
		if (!(leftHandSide instanceof ReturnTypeDouble))
			throw new IllegalTypeException(ReturnTypeDouble.class, leftHandSide.getClass());
		else if (!(rightHandSide instanceof ReturnTypeDouble))
			throw new IllegalTypeException(ReturnTypeDouble.class, rightHandSide.getClass());
	}

	public LessThanExpression(Expression leftHandSide, Expression rightHandSide) {
			this(leftHandSide, rightHandSide, null);
	}
	
	@Override
	public Literal eval(Program parentProgram) {
		Literal leftHandEvaluated = getLeftHandSide().eval(parentProgram);
		Literal RightHandEvaluated = getRightHandSide().eval(parentProgram);
		
		if (!(leftHandEvaluated instanceof DoubleLiteralExpression))
			throw new IllegalTypeException(DoubleLiteralExpression.class, leftHandEvaluated.getClass());
		if (!(RightHandEvaluated instanceof DoubleLiteralExpression))
			throw new IllegalTypeException(DoubleLiteralExpression.class, RightHandEvaluated.getClass());
		
		Double leftValue = ((DoubleLiteralExpression)leftHandEvaluated).getValue(parentProgram);
		Double rightValue = ((DoubleLiteralExpression)RightHandEvaluated).getValue(parentProgram);
		
		return new BooleanLiteralExpression(leftValue < rightValue);
	}

}
