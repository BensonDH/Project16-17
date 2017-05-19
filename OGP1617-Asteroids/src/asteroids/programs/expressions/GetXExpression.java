package asteroids.programs.expressions;

import asteroids.model.Entity;
import asteroids.part3.programs.SourceLocation;
import asteroids.programs.Program;
import asteroids.programs.exceptions.IllegalTypeException;

public class GetXExpression extends UnaryExpression implements ReturnTypeDouble {

	public GetXExpression(Expression expression, SourceLocation sourceLocation) {
		super(expression, sourceLocation);
		
		if (!(expression instanceof ReturnTypeEntity))
			throw new IllegalTypeException(ReturnTypeEntity.class, expression.getClass());
	}
	
	public GetXExpression(Expression expression){
		this(expression, null);
	}

	@Override
	public Literal eval(Program parentProgram) {
		Object entity = getExpression().eval(parentProgram).getValue(parentProgram);
		
		if (!(entity instanceof Entity))
			throw new IllegalTypeException(Entity.class, entity.getClass());
		
		double value = ((Entity)entity).getPosition().getX();
		
		return new DoubleLiteralExpression(value);
	}

}
