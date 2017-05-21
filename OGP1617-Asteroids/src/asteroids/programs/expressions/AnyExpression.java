package asteroids.programs.expressions;

import java.util.*;

import asteroids.model.*;
import asteroids.part3.programs.SourceLocation;
import asteroids.programs.Program;

public class AnyExpression extends Literal<Entity>{

	public AnyExpression(SourceLocation sourceLocation) {
		super(Entity.class, null, sourceLocation);
	}
	
	public AnyExpression() {
		super(Entity.class, null, null);
	}

	@Override
	public Entity getValue(Program parentProgram) {
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
		
		// Keep choosing another entity when the chosen entitiy is the ship itself.
		while (presentEntities[randInt] == executingShip){
			randInt = rand.nextInt(amountOfEntities);
		}
		
		return (Entity)presentEntities[randInt];
	}

	@Override
	public Literal<Entity> eval(Program parentProgram) {
		return this;
	}

}
