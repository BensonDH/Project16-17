package asteroids.programs.expressions;

import asteroids.model.Entity;
import asteroids.part3.programs.SourceLocation;
import asteroids.programs.Program;
import asteroids.programs.exceptions.IllegalTypeException;

public class GetRadiusExpression extends UnaryExpression implements ReturnTypeDouble {

	public GetRadiusExpression(Expression expression, SourceLocation sourceLocation) {
		super(expression, sourceLocation);
		
		if (!(expression instanceof ReturnTypeEntity))
			throw new IllegalTypeException(ReturnTypeEntity.class, expression.getClass());
	}
	
	public GetRadiusExpression(Expression expression) {
		this(expression, null);
	}
	
	@Override
	public Literal eval(Program parentProgram) {
		Object entity = getExpression().eval(parentProgram).getValue(parentProgram);
		
		if (!(entity instanceof Entity))
			throw new IllegalTypeException(Entity.class, entity.getClass());
		
		double value = ((Entity)entity).getRadius();
		
		return new DoubleLiteralExpression(value);
	}

}
