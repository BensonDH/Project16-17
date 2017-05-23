package asteroids.programs.expressions;

import asteroids.part3.programs.SourceLocation;
import asteroids.programs.*;
import asteroids.programs.exceptions.IllegalTypeException;

public class ChangeSignExpression extends UnaryExpression<Double>{
	
	public ChangeSignExpression(Expression<Double> expression, SourceLocation sourceLocation) {
		super(expression, sourceLocation);
	}
	
	public ChangeSignExpression(Expression<Double> expression) {
		super(expression, null);
	}
	
	@Override
	public Literal<Double> eval(Program parentProgram) {
		Literal<?> evaluatedExpression = getExpression().eval(parentProgram);
		
		if (evaluatedExpression instanceof NullType)
			throw new IllegalTypeException(Double.class, NullType.class);
		else if (!(evaluatedExpression.getLiteralType().equals(Double.class)))
			throw new IllegalTypeException(Double.class, evaluatedExpression.getLiteralType());
		
		Double newValue = -(Double)evaluatedExpression.getValue(parentProgram);
		return new Literal<Double>(Double.class, newValue);	
	}

}
