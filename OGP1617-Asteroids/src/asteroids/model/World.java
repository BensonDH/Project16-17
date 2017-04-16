package asteroids.model;

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
		for (Entity existingEntity: linkedEntities){
			if (entity.overlapSignificantly(existingEntity)){	
				return false;
			}		
		}
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
		if (!isInWorld(entity) || current_vel.getX() == 0 || current_vel.getY() == 0)
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
		} else {
			// if it is not, advance all the entities to the time of collision and handle the collision.
			advanceEntities(firstCollisionTime);
			handleCollision(firstCollision, collisionListener);
		
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
	 * 
	 * This is a helper method of the method evolve.
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
	private void handleCollision(Collision firstCollision, CollisionListener collisionListener){
		Entity firstEntity = firstCollision.getFistInvolvedEntity();
		Entity secondEntity = firstCollision.getSecondInvolvedEntity();
		Vector collisionPosition = firstCollision.getCollisionPosition();
		
		// -- Case 1: Entity collides with world border
		if (secondEntity == null){		
			if(collisionListener != null)
				collisionListener.boundaryCollision(firstEntity, collisionPosition.getX(), collisionPosition.getY());
			
			borderCollision(firstEntity);	
			// We're done, exit the method
			return;
		}
		
		if (collisionListener != null)
			collisionListener.objectCollision(firstEntity, secondEntity, collisionPosition.getX(), collisionPosition.getY());
		
		// Case 2: 2 ships collide
		if (firstEntity instanceof Ship && secondEntity instanceof Ship){
			shipCollision((Ship)firstEntity, (Ship)secondEntity);

			// We're done, exit the method
			return;
		}
			
		// Case3: firstEntity is a Ship and secondEntity is a Bullet or vice versa
		else if ((firstEntity instanceof Ship && secondEntity instanceof Bullet) ||
				 (secondEntity instanceof Ship && firstEntity instanceof Bullet)){
			if (firstEntity instanceof Ship)
				shipBulletCollision((Ship)firstEntity, (Bullet)secondEntity);
			else
				shipBulletCollision((Ship)secondEntity, (Bullet)firstEntity);
		}
		// Case4: both entities are bullets
		else if (firstEntity instanceof Bullet && secondEntity instanceof Bullet){
			firstEntity.die();
			secondEntity.die();
		}
	}
	
	/**
	 * Handle a collision of an entity with a boundary.
	 * 
	 * When the entity is a Bullet and it has bounced off a boundary too many
	 * times, the bullet will die.
	 * If the entity collides with a horizontal border, the Y-component of its
	 * velocity vector is reversed.
	 * If the entity collides with a vertical border, the X-component of its
	 * velocity vector is reversed.
	 * 
	 * @param entity
	 * 			The entity that will collide with a boundary
	 * @post	If the entity is a bullet and it has reached the maximum amount of collisions 
	 * 			with boundaries, the bullet will die.
	 * 		    | if (entity instanceof Bullet && 
	 * 			|	  entity.getNbTimesBounced()+1 == entity.getMaxTimesBounced())
	 * 			| then new.entity.isDead() == true
	 * @post	If the entity is a bullet and it did not reach its maximum amount of collisions
	 * 			with boundaries yet, the bullet's NbTimesBounced will be increased with 1.
	 * 			| if (entity instanceof Bullet &&
	 * 			| 	  entity.getNbTimesBounced()+1 < entity.getMaxTimesBounced())
	 * 			| then new.entity.getNbTimesBounced() == old.entity.getNbTimesBounced()+1
	 * @effect	
	 * 			| if (apparentlyCollidesWithHorizontalBorder(entity))
	 * 			|		entity.setVelocity(-1*entity.getVelocity().getX(),
	 * 			|						   entity.getVelocity().getY())
     * 			| else
     * 			|		entity.setVelocity(entity.getVelocity().getX(),
     * 			|						   -1*entity.getVelocity()*getY())
     * 
     * This is a helper method for the method handleCollisions.
	 */
	private void borderCollision(Entity entity){
		// If the entity is a bullet, we have to check how many times the bullet has 
		// bounced off a boundary
		if (entity instanceof Bullet) {
			int nbTimesBounced = ((Bullet)entity).getNbTimesBounced();
			int maxTimesBounced = ((Bullet)entity).getMaxTimesBounced();
			
			if (nbTimesBounced == maxTimesBounced)
				entity.die();
			else
				((Bullet)entity).setNbTimesBounced(((Bullet)entity).getNbTimesBounced()+1);
		}
		
		// It's a horizontal border
		if (apparentlyCollidesWithHorizontalBorder(entity)) {
			Vector velocity = entity.getVelocity();
			// Flip the Y-Component of the 
			entity.setVelocity(velocity.getX(), -velocity.getY());
		}
		// It's a vertical border
		else {
			Vector velocity = entity.getVelocity();
			// Flip the X-Component of the 
			entity.setVelocity(-velocity.getX(), velocity.getY());
			
			// We're done, exit the method
			return;
		}
	}
	
	/**
	 * Handle a collision with two ships.
	 * 
	 * @param firstShip
	 * 			The first ship involved in the collision.
	 * @param secondShip
	 * 			The second ship involved in the collision.
	 * 
	 * @post	Both ships their velocity will be changed in a way
	 * 			that they bounce off each other.
	 * 			The mass of both ships influences the collision's resolution.
	 * 
	 * This is a helper method for the method handleCollision.
	 */
	private void shipCollision(Ship firstShip, Ship secondShip){
		double mi = firstShip.getTotalMass();
		double mj = secondShip.getTotalMass();
		double sigma = firstShip.getRadius()+secondShip.getRadius();
		
		Vector firstVel = firstShip.getVelocity();
		Vector secondVel = secondShip.getVelocity();
		
		Vector deltaPos = secondShip.getPosition().subtract(firstShip.getPosition());
		Vector deltaVel = secondVel.subtract(firstVel);

		double J = (2*mi*mj*deltaVel.dot(deltaPos))/ (sigma*(mi+mj));
		
		firstShip.setVelocity(firstVel.getX()+(J*deltaPos.getX())/(sigma*mi),
								firstVel.getY()+(J*deltaPos.getY())/(sigma*mi));
		
		secondShip.setVelocity(secondVel.getX()-(J*deltaPos.getX())/(sigma*mj),
								 secondVel.getX()-(J*deltaPos.getY())/(sigma*mj));
	}
	
	/**
	 * Handle a collision between a bullet and a ship.
	 * 
	 * @param ship
	 * 			The ship involved in the collision.
	 * @param bullet
	 * 			The bullet involved in the collision.
	 * @post	If the bullet belongs to the ship, the bullet is
	 * 			loaded on the ship.
	 * 			| if (bullet.getSourceShip() == ship)
	 * 			| then ship.addBullet(bullet);
	 * @post 	If the bullet does not belong to the ship,
	 * 			both bullet and ship die.
	 * 			| if (bullet.getSourceShip() != ship)
	 * 			| then ship.die()
	 * 			|	   bullet.die()
	 * 
	 * This is a helper method for the method handleCollision.
	 */
	private void shipBulletCollision(Ship ship, Bullet bullet){
		// Check whether the bullet belongs to the ship
		if (bullet.getSourceShip() == ship)
			ship.loadBullets(bullet);
		// If the bullet does not belong to the ship, both ship and bullet die
		else {
			ship.die();
			bullet.die();
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
