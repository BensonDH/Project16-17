package asteroids.programs.expressions;

import asteroids.model.Entity;
import asteroids.part3.programs.SourceLocation;
import asteroids.programs.Program;
import asteroids.programs.exceptions.VariableException;

public class SelfExpression extends Literal<Entity> {

	public SelfExpression(SourceLocation sourceLocation) {
		super(Entity.class, null, sourceLocation);
	}

	@Override
	public Entity getValue(Program parentProgram) {
		if (parentProgram == null)
			return null;
		
		Entity result = parentProgram.getAssociatedShip();
		if (result == null)
			throw new VariableException("self");
		
		return result;
	}
}
