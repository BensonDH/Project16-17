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
		if (entity == null)
			throw new NullPointerException();
		if (!canHaveAsEntity(entity))
			throw new IllegalArgumentException("This entity cannot be added.");
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
			throw new IllegalArgumentException("The given enitity is not in this world.");
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
	 * Return the set of all the ships that lie in this game world.
	 * 
	 * @return	A HashSet containing all ships that lie in this world.
	 */
	public Set<Ship> queryShips(){
		Set<Ship> result = new HashSet<Ship>();
		for (Entity entity: linkedEntities)
			if (entity instanceof Ship)
				result.add((Ship)entity);
		
		return result;
	}
	
	/**
	 * Return the set of all the bullets that lie in this game world.
	 * 
	 * @return A HashSet containing all the bullets that lie in this world.
	 */
	public Set<Bullet> queryBullets(){
		Set<Bullet> result = new HashSet<Bullet>();
		for (Entity entity: linkedEntities)
			if (entity instanceof Bullet)
				result.add((Bullet)entity);
		
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
	//TODO: Werkt niet.
	public Entity getEntityAtPosition(double[] position){
		Vector convertedPos = new Vector(position[0], position[1]);

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
	 * @return
	 * 			TODO: find something for this.
	 */
	public double getTimeToCollisionWithBoundaries(Entity entity){
		//TODO: IMPORTANT: Nog checken of entity in world ligt of moet dat niet?
		double radius = entity.getRadius();
		Vector current_pos = entity.getPosition();
		Vector current_vel = entity.getVelocity();
		
		// If the given entity is not moving or does not lie in this world,
		// it will never collide with the boundaries.
		if (!isInWorld(entity) || current_vel.getX() == 0 || current_vel.getY() == 0)
			return Double.POSITIVE_INFINITY;
		// Upper-Right Quadrant
		else if (current_vel.getX() > 0 && current_vel.getY() > 0){
			double x_time = (getWidth()-(radius+current_pos.getX()))/current_vel.getX();
			double y_time = (getHeight()-(radius+current_pos.getY()))/current_vel.getY();
			
			if (x_time <= y_time)
				return x_time;
			else
				return y_time;	
		}
		// Upper-Left Quadrant
		else if (current_vel.getX() < 0 && current_vel.getY() > 0){
			double x_time = (-radius-current_pos.getX())/current_vel.getX();
			double y_time = (getHeight()-(radius+current_pos.getY()))/current_vel.getY();
			
			if (x_time <= y_time)
				return x_time;
			else
				return y_time;	
		}
		// Lower-Left Quadrant
		else if (current_vel.getX() < 0 && current_vel.getY() < 0){
			double x_time = (-(current_pos.getX()-radius))/current_vel.getX();
			double y_time = (-(current_pos.getY()-radius))/current_vel.getY();
			
			if (x_time <= y_time)
				return x_time;
			else
				return y_time;	
		}
		// Lower-Right Quadrant
		else if (current_vel.getX() > 0 && current_vel.getY() < 0){
			double x_time = (getWidth()-(radius+current_pos.getX()))/current_vel.getX();
			double y_time = (-(current_pos.getY()-radius))/current_vel.getY();
			
			if (x_time <= y_time)
				return x_time;
			else
				return y_time;	
		}
		return 0;
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
		double time = getTimeToCollisionWithBoundaries(entity);
		
		if (Double.isInfinite(time))
			return null;
		
		Vector vel = entity.getVelocity();
		Vector pos = entity.getPosition();
		
		Vector velTimesTime = vel.multiply(time);
		
		return pos.add(velTimesTime);
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
		Entity[] involvedEntities = getFirstCollisionEntities();
		double firstCollisionTime = involvedEntities[0].getTimeToCollision(involvedEntities[1]);
		
		// -- Step 2: Check if firstCollisionTime is greater than deltaT
		if (firstCollisionTime > deltaT) {
			//if it is, advance all the entities deltaT seconds
			advanceEntities(deltaT);
			return;
		} else {
			// if it is not, advance all the entities to the time of collision and handle the collision.
			advanceEntities(firstCollisionTime);
			handleCollision(involvedEntities[0], involvedEntities[1]);
		}
		// -- Step 3: recursive call
		evolve(firstCollisionTime-deltaT);
		}
	
	/**
	 * Advance all entities that lie in this world with deltaT seconds.
	 * TODO: Documentation
	 * @param deltaT
	 */
	private void advanceEntities(double deltaT){
		
		for (Entity entity: linkedEntities){
			// If this entity is a Ship and its thruster is active, update its velocity
			if (entity instanceof Ship && ((Ship)entity).isShipThrusterActive())
				((Ship)entity).thrust(deltaT);
			// Move the entity.
						entity.move(deltaT);
			// Update the coordEntities map
			updateCoordMap();
		}
	}
	
	/**
	 * Update the coordinate map that holds all entities in this world,
	 * with their position as key.
	 */
	private void updateCoordMap(){
		for (Vector key: coordEntities.keySet()){
			// Remove the outdated coordinate-Entity pair and save the entity
			Entity currentEntity = coordEntities.remove(key);
			
			// Add the Entity again, with updated coordinates
			coordEntities.put(currentEntity.getPosition(), currentEntity);
		}
	}
	
	
	private Map<Vector, Entity> coordEntities = new HashMap<Vector, Entity>();
	
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
		// -- Case 1: Ship collides with world border
		if (secondEntity == null){		
			// It's a horizontal border
			if (apparentlyCollidesWithHorizontalBorder(firstEntity)) {
				Vector velocity = firstEntity.getVelocity();
				// Flip the Y-Component of the 
				firstEntity.setVelocity(velocity.getX(), -velocity.getY());
				
				// If the entity is a bullet, we have to check how many times the bullet has 
				// bounced off a boundary
				if (firstEntity instanceof Bullet) {
					int nbTimesBounced = ((Bullet)firstEntity).getNbTimesBounced();
					int maxTimesBounced = ((Bullet)firstEntity).getMaxTimesBounced();
					
					if (nbTimesBounced == maxTimesBounced)
						firstEntity.die();
					else
						((Bullet)firstEntity).setNbTimesBounced(((Bullet)firstEntity).getNbTimesBounced()+1);
				}
				
				// We're done, exit the method
				return;
			}
			// It's a vertical border
			else {
				Vector velocity = firstEntity.getVelocity();
				// Flip the X-Component of the 
				firstEntity.setVelocity(-velocity.getX(), velocity.getY());
				
				// If the entity is a bullet, we have to check how many times the bullet has 
				// bounced off a boundary
				if (firstEntity instanceof Bullet) {
					int nbTimesBounced = ((Bullet)firstEntity).getNbTimesBounced();
					int maxTimesBounced = ((Bullet)firstEntity).getMaxTimesBounced();
					
					if (nbTimesBounced == maxTimesBounced)
						firstEntity.die();
					else
						((Bullet)firstEntity).setNbTimesBounced(((Bullet)firstEntity).getNbTimesBounced()+1);
				}
				// We're done, exit the method
				return;
			}
			
		}
		
		// Case 2: 2 ships collide
		else if (firstEntity instanceof Ship && secondEntity instanceof Ship){
			
			double mi = firstEntity.getTotalMass();
			double mj = secondEntity.getTotalMass();
			double sigma = firstEntity.getRadius()+secondEntity.getRadius();
			
			Vector firstVel = firstEntity.getVelocity();
			Vector secondVel = secondEntity.getVelocity();
			
			Vector deltaPos = firstEntity.getPosition().subtract(secondEntity.getPosition());
			Vector deltaVel = firstVel.subtract(secondVel);
	
			double J = (2*mi*mj*deltaVel.dot(deltaPos))/ (sigma*(mi+mj));
			
			firstEntity.setVelocity(firstVel.getX()+(J*deltaPos.getX())/(sigma*mi),
									firstVel.getY()+(J*deltaPos.getY())/(sigma*mi));
			
			secondEntity.setVelocity(secondVel.getX()+(J*deltaPos.getX())/(sigma*mj),
									 secondVel.getX()+(J*deltaPos.getY())/(sigma*mj));
			
			// All done, exit the method.
			return;
		}
			
		// Case3+4.a: firstEntity is a Ship and secondEntity is a Bullet
		else if (firstEntity instanceof Ship && secondEntity instanceof Bullet){
			// Check whether the bullet belongs to the ship
			if (((Bullet)secondEntity).getSourceShip() == firstEntity)
				((Ship)firstEntity).addBullet((Bullet)secondEntity);
			// If the bullet does not belong to the ship, both ship and bullet die
			else 
				firstEntity.die();
				secondEntity.die();
		}
		// Case3+4.b: firstEntity is a Bullet and secondEntity is a Ship
		else if (firstEntity instanceof Bullet && secondEntity instanceof Ship) {
			// Check whether the bullet belongs to the ship
			if (((Bullet)firstEntity).getSourceShip() == secondEntity)
				((Ship)secondEntity).addBullet((Bullet)firstEntity);
			// If the bullet does not belong to the ship, both ship and bullet die
			else 
				firstEntity.die();
				secondEntity.die();
		}
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
	private boolean apparentlyCollidesWithHorizontalBorder(Entity entity){
		double radius = entity.getRadius();
		double posY = entity.getPosition().getY();
		
		return ((0.99*radius <= posY) && (posY <= 1.01*radius))
				|| ((0.99*radius <= Math.abs(getHeight()-posY)) && (Math.abs(getHeight())-posY) <= 1.01*radius);
	}
	
	/**
	 * Return the two entities that are involved in the next collision of this world.
	 * If the first element of the returned collection is null, there will be no collision
	 * in this world.
	 * When the second element of the returned collection is null, it indicates that the first element of the 
	 * returned collection will collide with a boundary.
	 * 
	 * @return	A 2 dimensional array of Entities that will be involved in the next collision in this world.
	 */
	public Entity[] getFirstCollisionEntities(){
		// if there are no entities in this world, we have to do nothing.
		if (linkedEntities.size() == 0)
			return new Entity[]{null, null};
		
		
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
					
				if (collisionTime < firstCollisionTime)
					firstCollisionTime = collisionTime;
					firstInvolvedEntity = firstEntity;
					secondInvolvedEntity = secondEntity;
					
			} // End For
			
			// Collision with the world border
			double borderCollisionTime = getTimeToCollisionWithBoundaries(firstEntity);
			
			if (borderCollisionTime < firstCollisionTime)
				firstCollisionTime = borderCollisionTime;
				firstInvolvedEntity = firstEntity;
				secondInvolvedEntity = null;
		} //End For
		return new Entity[]{firstInvolvedEntity, secondInvolvedEntity};
	}
	
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
	@Basic @Raw
	public boolean isTerminated(){
		return isTerminated;
	}
	
	
	/**
	 * Variable registering whether this world is terminated.
	 */
	private boolean isTerminated = false;
}
