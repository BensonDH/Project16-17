package asteroids.programs.expressions;

import asteroids.part3.programs.SourceLocation;
import asteroids.programs.Program;
import asteroids.programs.exceptions.IllegalTypeException;

public class SqrtExpression extends UnaryExpression implements ReturnTypeDouble {

	public SqrtExpression(Expression expression, SourceLocation sourceLocation) {
		super(expression, sourceLocation);
		
		if (!(expression instanceof ReturnTypeDouble))
			throw new IllegalTypeException(ReturnTypeDouble.class, expression.getClass());
	}

	@Override
	public Literal eval(Program parentProgram) {
		Literal evaluatedExpression = getExpression().eval(parentProgram);
		
		if (!(evaluatedExpression instanceof DoubleLiteralExpression))
			throw new IllegalTypeException(DoubleLiteralExpression.class, evaluatedExpression.getClass());
		
		Double expressionValue = ((DoubleLiteralExpression)evaluatedExpression).getValue(parentProgram);
		
		return new DoubleLiteralExpression(Math.sqrt(expressionValue));
	}

}
