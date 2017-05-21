package asteroids.programs.expressions;

import asteroids.part3.programs.SourceLocation;
import asteroids.programs.Program;
import asteroids.programs.exceptions.IllegalTypeException;

public class SqrtExpression extends UnaryExpression<Double>{

	public SqrtExpression(Expression<Double> expression, SourceLocation sourceLocation) {
		super(expression, sourceLocation);
	}

	@Override
	public Literal<Double> eval(Program parentProgram) {
		Literal<?> evaluatedExpression = getExpression().eval(parentProgram);
		
		if (!(evaluatedExpression.getLiteralType().equals(Double.class)))
			throw new IllegalTypeException(Double.class, evaluatedExpression.getLiteralType());
		
		Double expressionValue = (Double)evaluatedExpression.getValue(parentProgram);
		
		return new Literal<Double>(Double.class, Math.sqrt(expressionValue));
	}

}
