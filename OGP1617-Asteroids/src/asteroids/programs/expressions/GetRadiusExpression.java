package asteroids.programs.expressions;

import asteroids.model.Entity;
import asteroids.part3.programs.SourceLocation;
import asteroids.programs.Program;
import asteroids.programs.exceptions.IllegalTypeException;

public class GetRadiusExpression extends UnaryExpression<Double>{

	public GetRadiusExpression(Expression<Entity> expression, SourceLocation sourceLocation) {
		super(expression, sourceLocation);
	}
	
	public GetRadiusExpression(Expression<Entity> expression) {
		this(expression, null);
	}
	
	@Override
	public Literal<Double> eval(Program parentProgram) {
		Literal<?> evaluatedExpression = getExpression().eval(parentProgram);
		
		if ((evaluatedExpression instanceof NullType))
			throw new IllegalTypeException(Entity.class, NullType.class);
		
		Object entity = evaluatedExpression.getValue(parentProgram);
		
		if (!(entity instanceof Entity))
			throw new IllegalTypeException(Entity.class, entity.getClass());
		
		double value = ((Entity)entity).getRadius();
		
		return new Literal<Double>(Double.class, value);
	}

}
