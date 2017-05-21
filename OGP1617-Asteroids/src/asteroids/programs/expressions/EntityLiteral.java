package asteroids.programs.expressions;

import java.util.*;

import asteroids.model.*;
import asteroids.model.Vector;
import asteroids.part3.programs.SourceLocation;
import asteroids.programs.Program;

public class EntityLiteral<T extends Entity> extends Literal<T>{

	public EntityLiteral(Class<T> className, SourceLocation sourceLocation) {
		super(className, null, sourceLocation);
	}
	
	public EntityLiteral(Class<T> className) {
		super(className, null, null);
	}

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
		
		if (getLiteralType() == Bullet.class)
			return getFiredBullet(entities, executingShip);
		else
			return getClosestEntity(entities, executingShip);
	}
	
	private T getFiredBullet(Set<T> bullets, Ship ship){
		for (T bullet: bullets){
			if (((Bullet)bullet).getSourceShip() == ship)
				return bullet;
		}
		return null;
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
