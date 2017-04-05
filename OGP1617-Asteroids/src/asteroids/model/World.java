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
	
	/**
	 * TODO: Documentation
	 * @return
	 */
	@Basic
	public double getWidth(){
		return this.width;	
	}
	
	/**
	 * Variable registering the width of this world.
	 */
	private final double width;
	
	/**
	 * TODO: Documentation
	 * @return
	 */
	@Basic
	public double getHeight(){
		return this.height;
	}
	
	/**
	 * Variable registering the height of this world.
	 */
	private final double height;
	
	
	// Relation World-Entity
	/**
	 * TODO: Documentation
	 * @param entity
	 */
	public void addEntity(Entity entity) throws NullPointerException, IllegalArgumentException{
		if (!canHaveAsEntity(entity))
			throw new IllegalArgumentException("This entity cannot be added.");
		else
			linkedEntities.add(entity);
	}
	
	/**
	 * TODO: Documentation
	 * @param entity
	 * @throws NullPointerException
	 * @throws IllegalArgumentException
	 */
	public void removeEntity(Entity entity) throws NullPointerException, IllegalArgumentException{
		if (entity == null)
			throw new NullPointerException("entity cannot be null.");
		else if (!linkedEntities.contains(entity))
			throw new IllegalArgumentException("The given enitity is not in this world.");
		else
			linkedEntities.remove(entity);
	}
	
	/**
	 * TODO: Documentation
	 * @param entity
	 * @return
	 */
	public boolean canHaveAsEntity(Entity entity) throws IllegalArgumentException{
		if (entity == null)
			throw new IllegalArgumentException("Entity cannot be null.");

		if (!contains(entity) || !(entity.getWorld() == null))
			return false;
		
		return true;
	}
	
	/**
	 * Return the set of all the ships that lie in this game world.
	 * @return	A HashSet containing all ships that lie in this world.
	 */
	public Set<Entity> queryShips(){
		Set<Entity> result = new HashSet<Entity>();
		for (Entity entity: linkedEntities)
			if (entity instanceof Ship)
				result.add(entity);
		
		return result;
	}
	
	/**
	 * Check whether the given entity lies in this world
	 * @param entity	
	 * 				The entity that has to be verified
	 * @return 		true if the given entity lies in this world
	 * 				false otherwise
	 */
	public boolean isInWorld(Entity entity){
		return linkedEntities.contains(entity);
	}
	
	// Queries
	
	/**
	 * Return the set of all the bullets that lie in this game world.
	 * @return A HashSet containing all the bullets that lie in this world.
	 */
	public Set<Entity> queryBullets(){
		Set<Entity> result = new HashSet<Entity>();
		for (Entity entity: linkedEntities)
			if (entity instanceof Bullet)
				result.add(entity);
		
		return result;
	}
	
	/**
	 * Return the set of all entities that lie in this world.
	 *
	 * TODO: moet documentatie zoals in Hoorcollege 7 -> Class Person -> Method getAllOwnings()?
	 * 
	 * @return
	 */
	public Set<Entity> queryEntities(){
		return new HashSet<Entity>(linkedEntities);
	}
	
	/**
	 * A set registering all the entities that lie in this world.
	 */
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
	/**
	 * TODO: Documentation
	 * @param entity
	 * @return
	 */
	public double getTimeToCollisionWithBoundaries(Entity entity){
		double radius = entity.getRadius();
		double[] current_pos = entity.getPosition();
		double[] current_vel = entity.getVelocity();
		
		// If the given entity is not moving or does not lie in this world,
		// it will never collide with the boundaries.
		if (!isInWorld(entity) || current_vel[0] == 0 || current_vel[1] == 0)
			return Double.POSITIVE_INFINITY;
		// Upper-Right Quadrant
		else if (current_vel[0] > 0 && current_vel[1] > 0){
			double x_time = (getWidth()-(radius+current_pos[0]))/current_vel[0];
			double y_time = (getHeight()-(radius+current_pos[1]))/current_vel[1];
			
			if (x_time <= y_time)
				return x_time;
			else
				return y_time;	
		}
		// Upper-Left Quadrant
		else if (current_vel[0] < 0 && current_vel[1] > 0){
			double x_time = (-radius-current_pos[0])/current_vel[0];
			double y_time = (getHeight()-(radius+current_pos[1]))/current_vel[1];
			
			if (x_time <= y_time)
				return x_time;
			else
				return y_time;	
		}
		// Lower-Left Quadrant
		else if (current_vel[0] < 0 && current_vel[1] < 0){
			double x_time = (-(current_pos[0]-radius))/current_vel[0];
			double y_time = (-(current_pos[1]-radius))/current_vel[1];
			
			if (x_time <= y_time)
				return x_time;
			else
				return y_time;	
		}
		// Lower-Right Quadrant
		else if (current_vel[0] > 0 && current_vel[1] < 0){
			double x_time = (getWidth()-(radius+current_pos[0]))/current_vel[0];
			double y_time = (-(current_pos[1]-radius))/current_vel[1];
			
			if (x_time <= y_time)
				return x_time;
			else
				return y_time;	
		}
		return 0;
	}
	
	/**
	 * TODO: Documentation
	 * @param entity
	 * @return
	 */
	public double[] getPositionToCollisionWithBoundaries(Entity entity){
		double time = getTimeToCollisionWithBoundaries(entity);
		
		if (Double.isInfinite(time))
			return null;
		
		double[] vel = entity.getVelocity();
		double[] pos = entity.getPosition();
		
		
		return new double[]{pos[0]+vel[0]*time, pos[1]+vel[1]*time};
	}
	
	
	// Destroy/Terminate methods
	/**
	 * Destroy this world by removing all the entities.
	 * 
	 * @post	This world is terminated
	 * 			| new.isTerminated()
	 * @post	All entities that were present in this world are removed from
	 * 			this world.
	 * 			| for each entity in Entity:
	 * 			|	new.entity.getWorld() == null
	 */
	public void destroy(){
		if (!this.isTerminated()){

			for (Entity entity: linkedEntities){
				entity.removeWorld();
			}
			linkedEntities.clear();
			this.isTerminated=true;
		}
	}
	
	/**
	 * Check whether this world is terminated.
	 */
	@Basic @Raw
	public boolean isTerminated(){
		return isTerminated;
	}
	
	/**
	 * Variable registering whether this world is terminated.
	 */
	private boolean isTerminated = false;
}
