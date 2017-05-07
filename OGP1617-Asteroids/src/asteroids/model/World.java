package asteroids.model;

import asteroids.filters.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import asteroids.part2.CollisionListener;
import be.kuleuven.cs.som.annotate.*;

/**
 *  GitHub repository : https://github.com/BensonDH/Project16-17
 */

/**
 * A class representing a game world.
 * 
 * - Game worlds have an immutable width and height.
 * - A game world can contain Entities.
 * 
 * @version	1.0
 * @author 	De Heel Benson (burgerlijk ingenieur computerwetenschappen - elektrotechniek, 
 * 			De Jaegere Xander burgerlijk ingenieur computerwetenschappen - elektrotechniek) 
 */
public class World {
	
	/**
	 * Initialize this new world with the given width and height.
	 * 
	 * @param width
	 * 		  The width of this new world.
	 * @param height
	 * 		  The height of this new world.
	 * @post
	 * 		  | if (isValidBorderDistance(width))
	 * 		  |		then new.getWidth() == width
	 * @post 
	 * 		  | if (!isValidBorderDistance(width)
	 * 		  |	 	then new.getWidth() == getDefaultWidth()
	 * @post
	 * 		  | if (isValidBorderDistance(height)
	 * 		  | 	then new.getHeight() == height
	 * @post
	 * 		  | if (!isValidBorderDistance(height)
	 * 		  | 	then new.getHeight() == getDefaultHeight()
	 */
	@Raw
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
	@Raw
	public World(){
		this(World.getDefaultWidth(), World.getDefaultHeight());
	}
	
	/**
	 * Check whether the given value is a valid border Height or width.
	 * 
	 * @param value
	 * 		   	The value that needs to be verified.
	 * @return	True if and only if the given value is between 0 and 
	 * 			Double.MAX_VALUE, false otherwise
	 * 			| result == (0 < value < Double.MAX_VALUE)
	 */
	@Basic
	public static boolean isValidBorderDistance(double value){
		return (value > 0 && value < Double.MAX_VALUE);
	}
	
	/**
	 * Return the width of this world.
	 * 
	 * @see implementation
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
	 * 
	 * @see implementation
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
	 * 
	 * @see implementation
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
	 * 
	 * @see implementation
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
		if (entity == null)
			throw new NullPointerException();
		if (!canHaveAsEntity(entity))
			throw new IllegalArgumentException("This entity cannot be added."+entity.getClass().toString());
		else {
			entity.setWorld(this);
			linkedEntities.add(entity);
			coordEntities.put(entity.getPosition(), entity);
		}
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
			throw new IllegalArgumentException("The given entity is not in this world.");
		else {
			entity.removeWorld();
			linkedEntities.remove(entity);
			coordEntities.remove(entity.getPosition());
		}
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
	@Basic
	public boolean canHaveAsEntity(Entity entity) throws NullPointerException{
		if (entity == null)
			throw new NullPointerException("Entity cannot be null.");
		
		// Check whether entity fully lies between the borders of this world
		// and whether entity does not have a world yet.
		if (!contains(entity) || !(entity.getWorld() == null))
			return false;
		// Check whether entity does not overlap with any of the existing entities in this world
		if (overlapsWithAnyEntity(entity))
			return false;
		
		return true;
	}
	
	/**
	 * Check whether the given entity is present in this world.
	 * 
	 * @param entity	
	 * 				The entity that has to be verified
	 * @see implementation
	 */
	@Basic
	public boolean isInWorld(Entity entity){
		return linkedEntities.contains(entity);
	}
	
	
	// Queries
	/**
	 * Query the Entities that lie in this world after
	 * after they have been filtered by the given extractor.
	 * 
	 * @param extractor
	 * 			The extractor to be used as a filter.
	 * @return	A HashSet containing entities that lie in this world
	 * 			filtered by applying the given extractor on these 
	 * 			entities.
	 * 			|result ==
	 * 			|		HashSet({entity in getAllEntities() : extractor.getItem(entity)})
	 * @throws IllegalArgumentException
	 * 			If the given extractor is null
	 * 			| extractor == null
	 * @throws IllegalStateException
	 * 			If this world is terminated
	 * 			| isTerminated()
	 */
	//TODO: iets vinden voor die Set<...> => Zien we nog later in de les? Generic types
	public Set<? extends Entity> query(Extractor extractor) 
			throws IllegalArgumentException, IllegalStateException{
		if (extractor == null)
			throw new IllegalArgumentException("The extractor cannot be null.");
		if (isTerminated())
			throw new IllegalStateException("This world is terminated.");
		Set<Entity> result = new HashSet<Entity>();
		
		for (Entity entity: linkedEntities) {
			Entity extractorResult = extractor.getItem(entity);
			if (extractorResult != null)
				result.add(extractorResult);
		}
		
		return result;
	}
	
	
	/**
	 * Return the set of all entities that lie in this world.
	 * 
	 * @return A HashSet which contains all the entities that lie in this world.
	 */
	public Set<Entity> getAllEntities(){
		return new HashSet<Entity>(linkedEntities);
	}
	
	/**
	 * A set registering all the entities that lie in this world.
	 */
	private List<Entity> linkedEntities = new ArrayList<Entity>();
	
	
	// other methods
	/**
	 * Check whether the given Entity overlaps significantly with any
	 * other Entity that is present in this world.
	 * 
	 * @param entity
	 * 			The entity that has to be verified.
	 * @return	False if and only if there exists no entity in this world
	 * 			that overlaps significantly with the given Entity.
	 * 			Returns true otherwise.
	 * 			| if (for every presentEntity in this world:
	 * 			|		presentEntity.overlapSignificantly(entity)):
	 * 			|			result == false 
	 * 			| else result == true
	 */
	public boolean overlapsWithAnyEntity(Entity entity){
		for (Entity presentEntity:linkedEntities){
			if (presentEntity.overlapSignificantly(entity))
				return true;
		}
		return false;
	}
	
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
	public Entity getEntityAtPosition(double x, double y){
		Vector convertedPos = new Vector(x, y);

		return coordEntities.get(convertedPos);
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
	@Basic
	public boolean contains(Entity entity){
		Vector entityPos = entity.getPosition();
		double radius = entity.getRadius();
		// Left and under border 
		if (entityPos.getX() < 0.99*radius || entityPos.getY() < 0.99*radius)
			return false;
		// Right and upper border
		else if ((getWidth()-entityPos.getX()) < 0.99*radius || (getHeight()-entityPos.getY()) < 0.99*radius)
			return false;
		
		return true;
	}
	/**
	 * Calculate the time it takes for the given entity to reach the boundary it is heading to.
	 * 
	 * @param entity
	 * 			The entity whose time to collision with a boundary has to be calculated.
	 * @return	The time until the given entity collides with one of the boundaries of this world.
	 */
	public double getTimeToCollisionWithBoundaries(Entity entity){	
		double radius = entity.getRadius();
		Vector current_pos = entity.getPosition();
		Vector current_vel = entity.getVelocity();
		
		// If the given entity is not moving or does not lie in this world,
		// it will never collide with the boundaries.
		if (!isInWorld(entity) || (current_vel.getX() == 0 && current_vel.getY() == 0))
			return Double.POSITIVE_INFINITY;
		double x_time;
		double y_time;
		// Upper-Right Quadrant
		if (current_vel.getX() >= 0 && current_vel.getY() >= 0){
			x_time = (getWidth()-(radius+current_pos.getX()))/current_vel.getX();
			y_time = (getHeight()-(radius+current_pos.getY()))/current_vel.getY();
		}
		// Upper-Left Quadrant
		else if (current_vel.getX() <= 0 && current_vel.getY() >= 0){
			x_time = (radius-current_pos.getX())/current_vel.getX();
			y_time = (getHeight()-(radius+current_pos.getY()))/current_vel.getY();
		}
		// Lower-Left Quadrant
		else if (current_vel.getX() <= 0 && current_vel.getY() <= 0){
			x_time = (radius-current_pos.getX())/current_vel.getX();
			y_time = (radius-current_pos.getY())/current_vel.getY();
		}
		// Lower-Right Quadrant
		else {
			x_time = (getWidth()-(radius+current_pos.getX()))/current_vel.getX();
			y_time = (radius-current_pos.getY())/current_vel.getY();
		}
		if (x_time <= y_time)
			return x_time;
		else
			return y_time;
	}
	
	/**
	 * Return the coordinates where the given entity will collide with a boundary as a Vector.
	 * 
	 * @param entity
	 * 			The entity involved in the collision with a boundary.
	 * @return
	 * 			| result == getTimeToCollisionWithBoundaries(entity)*entity.getVelocity()
	 */
	public Vector getPositionToCollisionWithBoundaries(Entity entity){
		double radius = entity.getRadius();
		Vector current_pos = entity.getPosition();
		Vector current_vel = entity.getVelocity();
		
		// If the given entity is not moving or does not lie in this world,
		// it will never collide with the boundaries.
		if (!isInWorld(entity) || current_vel.getX() == 0 || current_vel.getY() == 0)
			return null;
		double x_time;
		double y_time;
		Vector extraPos;
		// Upper-Right Quadrant
		if (current_vel.getX() > 0 && current_vel.getY() > 0){
			x_time = (getWidth()-(radius+current_pos.getX()))/current_vel.getX();
			y_time = (getHeight()-(radius+current_pos.getY()))/current_vel.getY();
			
			if (x_time <= y_time){
				extraPos= current_vel.multiply(x_time).add(new Vector(radius, 0));
				return current_pos.add(extraPos);
			}
			extraPos= current_vel.multiply(y_time).add(new Vector(0, radius));
			return current_pos.add(extraPos);
		}
		// Upper-Left Quadrant
		else if (current_vel.getX() < 0 && current_vel.getY() > 0){
			x_time = (radius-current_pos.getX())/current_vel.getX();
			y_time = (getHeight()-(radius+current_pos.getY()))/current_vel.getY();
			
			if (x_time <= y_time){
				extraPos= current_vel.multiply(x_time).add(new Vector(-radius, 0));
				return current_pos.add(extraPos);
			}
			extraPos= current_vel.multiply(y_time).add(new Vector(0, radius));
			return current_pos.add(extraPos);
		}
		// Lower-Left Quadrant
		else if (current_vel.getX() < 0 && current_vel.getY() < 0){
			x_time = (radius-current_pos.getX())/current_vel.getX();
			y_time = (radius-current_pos.getY())/current_vel.getY();
			
			if (x_time <= y_time){
				extraPos= current_vel.multiply(x_time).add(new Vector(-radius, 0));
				return current_pos.add(extraPos);
			}
			extraPos= current_vel.multiply(y_time).add(new Vector(0, -radius));
			return current_pos.add(extraPos);
		}
		// Lower-Right Quadrant
		else {
			x_time = (getWidth()-(radius+current_pos.getX()))/current_vel.getX();
			y_time = (radius-current_pos.getY())/current_vel.getY();
			
			if (x_time <= y_time){
				extraPos= current_vel.multiply(x_time).add(new Vector(radius, 0));
				return current_pos.add(extraPos);
			}
			extraPos= current_vel.multiply(y_time).add(new Vector(0, -radius));
			return current_pos.add(extraPos);
		}
	}
	
	/**
	 * No Documentation required.
	 * 
	 * @throws IllegalStateException
	 * 			| isTerminated()
	 * @throws IllegalArgumentException
	 * 			| Double.isNaN(deltaT)
	 * @throws IllegalArgumentException
	 * 			| Double.isInfinite(deltaT)
	 * @throws IllegalArgumentException
	 * 			| (deltaT < 0)
	 */
	public void evolve(double deltaT, CollisionListener collisionListener){
		if (isTerminated())
			throw new IllegalStateException("This world has been terminated.");
		if (Double.isNaN(deltaT) || Double.isInfinite(deltaT) || (deltaT < 0))
			throw new IllegalArgumentException(deltaT+" is not valid.");
		
		// -- Step 1: predict the first collision
		Collision firstCollision = getFirstCollision();
		double firstCollisionTime = firstCollision.getTimeToCollision();
		
		// -- Step 2: Check if firstCollisionTime is greater than deltaT
		if (firstCollisionTime > deltaT) {
			//if it is, advance all the entities deltaT seconds
			advanceEntities(deltaT);
		} 
		else {
			firstCollision.handleCollision(collisionListener);
			// -- Step 3: recursive call
			evolve(deltaT-firstCollisionTime, collisionListener);
		}
	}
	
	/**
	 * Return a FirstCollision object that contains all the information about the
	 * first collision that will happen in this world.
	 * 
	 * @return A FirstCollision object that contains all the information 
	 * 		   about the next collision in this world.
	 */
	public Collision getFirstCollision(){
		// if there are no entities in this world, we have to do nothing.
		if (linkedEntities.size() == 0)
			return new Collision();
		
		double firstCollisionTime = Double.POSITIVE_INFINITY;
		Entity firstInvolvedEntity=null;
		// secondInvolvedEntity is null if the first collision is with a border.
		Entity secondInvolvedEntity=null;
		
		for (int firstIndex=0; firstIndex < linkedEntities.size(); firstIndex++){
			// first entity
			Entity firstEntity = linkedEntities.get(firstIndex);
			
			for (int secondIndex= firstIndex+1; secondIndex < linkedEntities.size(); secondIndex++){
				Entity secondEntity = linkedEntities.get(secondIndex);
				double collisionTime = firstEntity.getTimeToCollision(secondEntity);
					
				if (collisionTime < firstCollisionTime) {
					firstCollisionTime = collisionTime;
					firstInvolvedEntity = firstEntity;
					secondInvolvedEntity = secondEntity;
				}	
			} // End For
			
			// Collision with the world border
			double borderCollisionTime = getTimeToCollisionWithBoundaries(firstEntity);
			
			if (borderCollisionTime < firstCollisionTime) {
				firstCollisionTime = borderCollisionTime;
				firstInvolvedEntity = firstEntity;
				secondInvolvedEntity = null;
			}
		} //End For
		if (firstInvolvedEntity == null)
			return new Collision();
		
		Vector collisionPosition;
		if (secondInvolvedEntity == null)
			collisionPosition = getPositionToCollisionWithBoundaries(firstInvolvedEntity);
		else
			collisionPosition = firstInvolvedEntity.getCollisionPosition(secondInvolvedEntity);
	
		return new Collision(firstInvolvedEntity, secondInvolvedEntity, collisionPosition, firstCollisionTime);
	}
	
	/**
	 * Advance all entities that lie in this world with deltaT seconds.
	 * 
	 * @param deltaT
	 * 				The considered time interval of the Entities' movement.
	 * @effect	
	 * 			| for every entity in queryEntities():
	 * 			|		entity.move(deltaT)
	 * @effect	
	 * 			| for every entity of type Ship in queryEntities():
	 * 			|		if ship.isShipThrusterActive()
	 * 			|			ship.thrust(deltaT)
	 */
	public void advanceEntities(double deltaT){
		
		for (Entity entity: linkedEntities){
			// If this entity is a Ship and its thruster is active, update its velocity
			if (entity instanceof Ship && ((Ship)entity).isShipThrusterActive())
				((Ship)entity).thrust(deltaT);
			// Move the entity.
			entity.move(deltaT);
		}
		// Update the coordEntities map
		updateCoordMap();
	}	
	
	/**
	 * Check whether the given entity apparently collides with a horizontal border.
	 * 
	 * An entity apparently collides with a border if and only if the distance
	 * between the border and the center of the entity is between 99% and 101%
	 * of the entity's radius.
	 * 
	 * @param entity
	 * 			The entity that has to be verified.
	 * @See implementation
	 */
	public boolean apparentlyCollidesWithHorizontalBorder(Entity entity){
		double radius = entity.getRadius();
		double posY = entity.getPosition().getY();
		
		return ((0.99*radius <= posY) && (posY <= 1.01*radius))
				|| ((0.99*radius <= Math.abs(getHeight()-posY)) && (Math.abs(getHeight())-posY) <= 1.01*radius);
	}
	
	/**
	 * Update the coordinate map that holds all entities in this world,
	 * with their position as key.
	 */
	public void updateCoordMap(){
		Map<Vector, Entity> tempMap = new HashMap<Vector, Entity>();
		
		for (Vector key: coordEntities.keySet()){
			Entity entity = coordEntities.get(key);
			tempMap.put(entity.getPosition(), entity);					
		}
		coordEntities = tempMap;
	}
	
	/**
	 * A Map that contains an entity that lies in this world as value and the entity's
	 * position as the corresponding key.
	 */
	private Map<Vector, Entity> coordEntities = new HashMap<Vector, Entity>();
	
	
	// Destroy/Terminate methods
	/**
	 * Destroy this world by removing all the entities and terminating the world.
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
	@Basic
	public boolean isTerminated(){
		return isTerminated;
	}
	
	
	/**
	 * Variable registering whether this world is terminated.
	 */
	private boolean isTerminated = false;
}
