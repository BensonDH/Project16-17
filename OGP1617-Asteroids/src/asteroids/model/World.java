package asteroids.model;

import java.util.HashSet;
import java.util.Set;

import org.hamcrest.core.IsNull;
import be.kuleuven.cs.som.annotate.*;

public class World {

	public World(double width, double height){
		if (width >= 0 && width < Double.MAX_VALUE)
			this.width = width;
		else
			this.width = 0;
		
		if (height >= 0 && height < Double.MAX_VALUE)
			this.height = height;
		else
			this.height = 0;
		
	}
	
	public double getWidth(){
		return this.width;
			
	}
	
	private final double width;
	
	public double getHeight(){
		return this.height;
	}
	
	private final double height;
	
	// Relation World-Entity
	public void addEntity(Entity entity){
		
	}
	
	/**
	 * TODO: Documentation
	 * @param entity
	 * @return
	 */
	private boolean canHaveAsEntity(Entity entity){
		double[] pos = entity.getPosition();
		if (!contains(entity) || !(entity.getWorld() == null))
			return false;
		
		return true;
		
	}
	/**
	 * TODO: Documentation
	 * @return
	 */
	public Set<Entity> query(){
		// TODO: Implementation
		return null;
	}
	private Set<Entity> linkedEntities = new HashSet<Entity>();
	// other methods
	/**
	 * TODO: Documentation
	 * @param position
	 * @return
	 */
	public Entity getEntityAtPosition(double[] position){
		// TODO: Implementation -> Posities in EnumSet stoppen
		return null;
	}
	
	/**
	 * TODO: Documentation
	 * @param entity
	 * @return
	 */
	public boolean contains(Entity entity){
		double[] entityPos = entity.getPosition();
		double radius = entity.getRadius();
		// Left and under border 
		if (entityPos[0] < 0.99*radius || entityPos[1] < 0.99*radius)
			return false;
		// Right and upper border
		else if ((getWidth()-entityPos[0]) < 0.99*radius || (getHeight()-entityPos[1]) < 0.99*radius)
			return false;
		
		return true;
	}
}
