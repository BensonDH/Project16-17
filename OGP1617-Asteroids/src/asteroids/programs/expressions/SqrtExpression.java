package asteroids.programs.expressions;

import asteroids.part3.programs.SourceLocation;
import asteroids.programs.Executable;
import asteroids.programs.exceptions.IllegalTypeException;

public class SqrtExpression extends UnaryExpression<Double>{

	public SqrtExpression(Expression<Double> expression, SourceLocation sourceLocation) {
		super(Double.class, expression, sourceLocation);
	}

	@Override
	public Literal<Double> eval(Executable parentExecutor) {
		Literal<?> evaluatedExpression = getExpression().eval(parentExecutor);
		
		if (!(evaluatedExpression.getReturnType().equals(Double.class)))
			throw new IllegalTypeException(Double.class, evaluatedExpression.getReturnType());
		
		Double expressionValue = (Double)evaluatedExpression.getValue(parentExecutor);
		
		return new Literal<Double>(Double.class, Math.sqrt(expressionValue));
	}

}
