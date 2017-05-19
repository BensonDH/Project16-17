package asteroids.programs.expressions;

import java.util.*;

import asteroids.model.*;
import asteroids.model.Vector;
import asteroids.part3.programs.SourceLocation;
import asteroids.programs.Program;

public class EntityLiteralExpression<T extends Entity> extends Literal implements ReturnTypeEntity {

	public EntityLiteralExpression(Class<T> className, SourceLocation sourceLocation) {
		super(sourceLocation);
		this.className = className;
	}
	
	public EntityLiteralExpression(Class<T> className) {
		super(null);
		this.className = className;
	}

	@Override
	public Literal eval(Program parentProgram) {
		return this;
	}

	@Override
	public Class<T> getLiteralType(){
		return className;
	}
	
	private final Class<T> className;
	
	@Override
	public T getValue(Program parentProgram) {
		if (parentProgram == null)
			return null;
		
		Ship executingShip = parentProgram.getAssociatedShip();
		
		if (executingShip == null)
			throw new IllegalStateException("This program is not loaded on a ship.");
		
		World gameWorld = executingShip.getWorld();
		if (gameWorld == null)
			return null;
		
		Set<T> entities = (Set<T>) gameWorld.query(getLiteralType());
		
		return getClosestEntity(entities, executingShip);
	}
	
	private T getClosestEntity(Set<T> entities, Ship ship){
		double shipX = ship.getPosition().getX();
		double shipY = ship.getPosition().getY();
		
		double minDist = Double.POSITIVE_INFINITY;
		T result=null;
		
		for (T entity: entities){
			if (entity == ship)
				continue;
			
			Vector entityPos = ((Entity)entity).getPosition();
			
			double distance = Math.sqrt(Math.pow(shipX-entityPos.getX(), 2.0)+ Math.pow(shipY-entityPos.getY(), 2.0));
			
			if (distance < minDist) {
				minDist = distance;
				result = entity;
			}
		}
		return result;
	}
	
	@Override
	public String toString(){
		String classPath = getLiteralType().getName();
		String[] splittedClassPath = classPath.split("\\.");
		
		return splittedClassPath[splittedClassPath.length-1];
	}
}
