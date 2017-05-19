package asteroids.programs.expressions;

import asteroids.model.Entity;
import asteroids.part3.programs.SourceLocation;
import asteroids.programs.Program;
import asteroids.programs.exceptions.VariableException;

public class SelfExpression extends Literal implements ReturnTypeEntity {

	public SelfExpression(SourceLocation sourceLocation) {
		super(sourceLocation);
	}

	@Override
	public Object getValue(Program parentProgram) {
		if (parentProgram == null)
			return null;
		
		Entity result = parentProgram.getAssociatedShip();
		if (result == null)
			throw new VariableException("self");
		
		return result;
	}

	@Override
	public Class<?> getLiteralType() {
		return Entity.class;
	}

	@Override
	public Literal eval(Program parentProgram) {
		return this;
	}

}
