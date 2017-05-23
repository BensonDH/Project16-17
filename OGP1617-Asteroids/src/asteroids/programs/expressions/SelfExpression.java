package asteroids.programs.expressions;

import asteroids.model.Entity;
import asteroids.part3.programs.SourceLocation;
import asteroids.programs.Executable;
import asteroids.programs.exceptions.VariableException;

public class SelfExpression extends Literal<Entity> {

	public SelfExpression(SourceLocation sourceLocation) {
		super(Entity.class, null, sourceLocation);
	}

	@Override
	public Entity getValue(Executable parentExecutor) {
		if (parentExecutor == null)
			return null;
		
		Entity result = parentExecutor.getAssociatedShip();
		if (result == null)
			throw new VariableException("self");
		
		return result;
	}
}
