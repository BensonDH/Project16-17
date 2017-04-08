package asteroids.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hamcrest.core.IsNull;
import be.kuleuven.cs.som.annotate.*;

public class World {
	
	/**
	 * Initialize this new world with the given width and height.
	 * @param width
	 * 		  The width of this new world.
	 * @param height
	 * 		  The height of this new world.
	 * @post 
	 * 		  | if (!isValidBorderDistance(width)
	 * 		  | then new.getWidth() == getDefaultWidth()
	 * 		  | else new.getWidth() == width
	 * @post
	 * 		  | if (!isValidBorderDistance(height)
	 * 		  | then new.getHeight() == getDefaultHeight()
	 * 		  | else new.getHeight() == height	
	 */
	public World(double width, double height){
		if (isValidBorderDistance(width))
			this.width = width;
		else
			this.width = getDefaultWidth();
		
		if (isValidBorderDistance(height))
			this.height = height;
		else
			this.height = getDefaultHeight();
	}
	
	/**
	 * Initialize this new world with its default values.
	 * 
	 * @effect 	
	 * 			| World(getDefaultWidth(), getDefaultHeight())
	 */
	public World(){
		this(World.getDefaultWidth(), World.getDefaultHeight());
	}
	
	/**
	 * Check whether the given value is a valid border Height or width.
	 * 
	 * @param value
	 * 		   	The value that needs to be verified.
	 * @see implementation
	 * 			
	 */
	public static boolean isValidBorderDistance(double value){
		return (value > 0 && value < Double.MAX_VALUE);
	}
	
	/**
	 * Return the width of this world.
	 */
	@Basic @Immutable
	public double getWidth(){
		return this.width;	
	}
	
	/**
	 * Variable registering the width of this world.
	 */
	private final double width;
	
	/**
	 * Return the height of this world.
	 */
	@Basic @Immutable
	public double getHeight(){
		return this.height;
	}
	
	/**
	 * Variable registering the height of this world.
	 */
	private final double height;
	
	/**
	 * Return the default width of this world.
	 */
	@Basic @Immutable
	public static double getDefaultWidth(){
		return World.defaultWidth;
	}
	
	/**
	 * Variable registering the default width of this world
	 */
	private static double defaultWidth = 1;
	
	/**
	 * Return the default height of this world.
	 */
	@Basic @Immutable
	public static double getDefaultHeight(){
		return World.defaultHeight;
	}
	
	/**
	 * Variable registering the default height of this world
	 */
	private static double defaultHeight = 1;

	
	// Relation World-Entity
	/**
	 * Add a given entity to this world.
	 * 
	 * @param entity
	 * 		 	The entity that has to be added.
	 * @post	
	 * 		  	| new.isInWorld(entity) == true
	 * 			| new.entity.getWorld() == this
	 * @throws	NullPointerException
	 * 			| entity == null
	 * @throws	IllegalArgumentException
	 * 			| !canHaveAsEntity(entity)
	 */
	public void addEntity(Entity entity) throws NullPointerException, IllegalArgumentException{
		if (!canHaveAsEntity(entity))
			throw new IllegalArgumentException("This entity cannot be added.");
		else
			linkedEntities.add(entity);
	}
	
	/**
	 * Remove a given entity from this world.
	 * 
	 * @param entity
	 * 			The entity that has to be removed.
	 * @post
	 * 			| new.isInWorld(entity) == false
	 * 			| new.entity.getWorld() == null
	 * @throws NullPointerException
	 * 			| entity == null
	 * @throws IllegalArgumentException
	 * 			| !isInWorld(entity)
	 */
	public void removeEntity(Entity entity) throws NullPointerException, IllegalArgumentException{
		if (entity == null)
			throw new NullPointerException("entity cannot be null.");
		else if (!isInWorld(entity))
			throw new IllegalArgumentException("The given enitity is not in this world.");
		else
			linkedEntities.remove(entity);
	}
	
	/**
	 * Check whether the given entity can be added to this world.
	 * An entity can be added to a world if the following conditions are fulfilled:
	 * 1. The given entity is not null
	 * 2. The given entity does not lie any world
	 * 3. The given entity does not overlap with any of the entities in this world
	 * 
	 * @param entity
	 * 			The entity that has to be verified.
	 * @return
	 * 			| true if (entity != null) &&
	 * 			|		  (!isInWorld(entity) && entity.getWorld() == null) &&
	 * 			|		  (for each entity in this world: 
	 * 			|				!entity.overlapSignificantly(anyEntity))
	 * 			| false otherwise
	 * @throws	NullPointerException
	 * 			| entity == null
	 */
	public boolean canHaveAsEntity(Entity entity) throws NullPointerException{
		if (entity == null)
			throw new NullPointerException("Entity cannot be null.");
		
		// Check whether entity fully lies between the borders of this world
		// and whether entity does not have a world yet.
		if (!contains(entity) || !(entity.getWorld() == null))
			return false;
		
		// Check whether entity does not overlap with any of the existing entities in this world
		for (Entity existingEntity: linkedEntities){
			if (entity.overlapSignificantly(existingEntity))
				return false;
		}
		
		return true;
	}
	
	/**
	 * Return the set of all the ships that lie in this game world.
	 * 
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
	 * 
	 * @param entity	
	 * 				The entity that has to be verified
	 * @see implementation
	 */
	public boolean isInWorld(Entity entity){
		return linkedEntities.contains(entity);
	}
	
	// Queries
	
	/**
	 * Return the set of all the bullets that lie in this game world.
	 * 
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
	 * @return A HashSet which contains all the entities that lie in this world.
	 */
	public Set<Entity> queryEntities(){
		return new HashSet<Entity>(linkedEntities);
	}
	
	/**
	 * A set registering all the entities that lie in this world.
	 */
	private List<Entity> linkedEntities = new ArrayList<Entity>();
	
	
	// other methods
	/**
	 * Return the entity, if any, whose center coincidences with the given position.
	 * 
	 * @param position
	 * 			The position of the returned entity, expressed as [X_component, Y_component]
	 * @return
	 * 		 	| for entity in queryEntities():
	 * 			| 	if (entity.getPosition() == position)
	 * 			| 		result == entity
	 * 			| if no such entity exists:
	 * 			|		result == null
	 */
	public Entity getEntityAtPosition(double[] position){
		// TODO: Implementation -> Posities in EnumSet stoppen
		return null;
	}
	
	/**
	 * Check whether the position of the given entity is apparently within the boundaries of this world.
	 * 
	 * An entity is apparently within the boundaries if the distance between each boundary of this world
	 * and the center of the given entity >= 99% of the given entity's radius.
	 * 
	 * @param entity
	 * 			The entity that has to be verified.
	 * @return
	 * 			| false if (getDistanceToClosestBorder(entity) < 0.99*entity.getRadius())
	 * 			| true otherwise
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
	 * Calculate the time it takes for the given entity to reach the boundary it is heading to.
	 * 
	 * @param entity
	 * 			The entity whose time to collision with a boundary has to be calculated.
	 * @return
	 * 			TODO: find something for this.
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
	 * Return the coordinates where the given entity will collide with a boundary.
	 * 
	 * @param entity
	 * 			The entity involved in the collision with a boundary.
	 * @return
	 * 			| result == getTimeToCollisionWithBoundaries(entity)*entity.getVelocity()
	 */
	public double[] getPositionToCollisionWithBoundaries(Entity entity){
		double time = getTimeToCollisionWithBoundaries(entity);
		
		if (Double.isInfinite(time))
			return null;
		
		double[] vel = entity.getVelocity();
		double[] pos = entity.getPosition();
		
		
		return new double[]{pos[0]+vel[0]*time, pos[1]+vel[1]*time};
	}
	
	/**
	 * No Documentation required.
	 * @param deltaT
	 */
	public void evolve(double deltaT){
		if (isTerminated())
			throw new IllegalStateException("This world has been terminated.");
		if (Double.isNaN(deltaT) || Double.isInfinite(deltaT))
			throw new IllegalArgumentException("The given deltaT is not valid.");
		
		// -- Step 1: predict the first collision
		double firstCollisionTime = Double.POSITIVE_INFINITY;
		Entity firstInvolvedEntity=null;
		// secondInvolvedEntity is null if the first collision is with a border.
		Entity secondInvolvedEntity=null;
		
		// first entity
		for (int firstIndex=0; firstIndex < linkedEntities.size(); firstIndex++){
			// Collision with another entity
			Entity firstEntity = linkedEntities.get(firstIndex);
			
			for (int secondIndex= firstIndex+1; secondIndex < linkedEntities.size(); secondIndex++){
					double collisionTime = firstEntity.getTimeToCollision(linkedEntities.get(secondIndex));
					
					if (collisionTime < firstCollisionTime)
						firstCollisionTime = collisionTime;
						firstInvolvedEntity = firstEntity;
						secondInvolvedEntity = linkedEntities.get(secondIndex);
			}
			// Collision with the world border
			double borderCollisionTime = getTimeToCollisionWithBoundaries(firstEntity);
			
			if (borderCollisionTime < firstCollisionTime)
				firstCollisionTime = borderCollisionTime;
				firstInvolvedEntity = firstEntity;
				secondInvolvedEntity = null;
		}
		
		// -- Step 2: Check if firstCollisionTime is greater than deltaT
		if (firstCollisionTime > deltaT) {
			//if it is, advance all the entities deltaT seconds
			
			// TODO: for all Entities -> move(deltaT) + change velocity
			return;
		} else {
			// if it is not, advance all the entities to the time of collision and handle the collision.
			
			// TODO: for all Entities -> move(firstCollisionTime) + change velocity
			handleCollision(firstInvolvedEntity, secondInvolvedEntity);
		}
			
		}

	/**
	 * Handle the collision that will happen in this world.
	 * 
	 * @param firstEntity	The first Entity that will be involved in the collision.
	 * 
	 * @param secondEntity	The second Entity that will be involved in the collision
	 * 						If secondEntity is null, firstEntity will collide with a border.
	 * 
	 * This is a helper method for the method evolve.
	 */
	private void handleCollision(Entity firstEntity, Entity secondEntity){
		// Case 1: Ship collides with world border
		if (secondEntity == null){
			//PROGRESS 
		}
		// Case 2: 2 ships collide
		else if (firstEntity instanceof Ship && secondEntity instanceof Ship){
			double firstMass = firstEntity.getMass();
			double secondMass = secondEntity.getMass();
			
			double deltaV_X = firstEntity.getVelocity()[0]- secondEntity.getVelocity()[0];
			double deltaV_Y = firstEntity.getVelocity()[1]- secondEntity.getVelocity()[1];
			
			double deltaR_X = firstEntity.getPosition()[0]- secondEntity.getPosition()[0];
			double deltaR_Y = firstEntity.getPosition()[1]- secondEntity.getPosition()[1];
			
			// TODO: Implementation, Assignment is unclear about J.
		}
		// Case3+4: Collision between bullet and ship
		else if ((firstEntity instanceof Ship && secondEntity instanceof Bullet) || (firstEntity instanceof Bullet && secondEntity instanceof Ship)) {
			// TODO: Bullet implementation necessary -> bullet.getShip()
			
		}
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
				//entity.removeWorld();
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
