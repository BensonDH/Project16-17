package asteroids.programs.expressions;

import asteroids.part3.programs.SourceLocation;
import asteroids.programs.*;
import asteroids.programs.exceptions.IllegalTypeException;

public class ChangeSignExpression extends UnaryExpression<Double>{
	
	public ChangeSignExpression(Expression<Double> expression, SourceLocation sourceLocation) {
		super(Double.class, expression, sourceLocation);
	}
	
	public ChangeSignExpression(Expression<Double> expression) {
		super(Double.class, expression, null);
	}
	
	@Override
	public Literal<Double> eval(Executable parentExecutor) {
		Literal<?> evaluatedExpression = getExpression().eval(parentExecutor);
		
		if (evaluatedExpression instanceof NullType)
			throw new IllegalTypeException(Double.class, NullType.class);
		else if (!(evaluatedExpression.getReturnType().equals(Double.class)))
			throw new IllegalTypeException(Double.class, evaluatedExpression.getReturnType());
		
		Double newValue = -(Double)evaluatedExpression.getValue(parentExecutor);
		return new Literal<Double>(Double.class, newValue);	
	}

}
