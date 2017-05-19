package asteroids.programs.expressions;

import java.util.*;

import asteroids.model.*;
import asteroids.part3.programs.SourceLocation;
import asteroids.programs.Program;

public class AnyExpression extends Literal implements ReturnTypeEntity {

	public AnyExpression(SourceLocation sourceLocation) {
		super(sourceLocation);
	}

	@Override
	public Object getValue(Program parentProgram) {
		if (parentProgram == null)
			return null;
		
		Ship executingShip = parentProgram.getAssociatedShip();
		if (executingShip == null)
			throw new IllegalStateException("The executing program is not loaded on a ship.");
		
		World gameworld = executingShip.getWorld();
		if (gameworld == null)
			return null;
		
		Object[] presentEntities = gameworld.getAllEntities().toArray();
		int amountOfEntities = presentEntities.length;
		
		Random rand = new Random();
		int randInt = rand.nextInt(amountOfEntities);
		
		// Keep chosing another entity when the chosen entitiy is the ship itself.
		while (presentEntities[randInt] == executingShip){
			randInt = rand.nextInt(amountOfEntities);
		}
		
		return presentEntities[randInt];
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
