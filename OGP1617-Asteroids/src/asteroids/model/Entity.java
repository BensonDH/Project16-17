package asteroids.model;

import java.nio.channels.IllegalSelectorException;

import be.kuleuven.cs.som.annotate.*;

/**
 * GitHub repository : https://github.com/BensonDH/Project16-17
 */

/**
 * A Class representing entities.
 *
 * - Entities are located on a certain position [km] with a certain velocity [km/s], 
 * 	 expressed in a Cartesian coordinate system with x- and y-coordinates.
 * - Entities also have a speed limit, meaning that the total velocity of an entity
 * 	 will never exceed the speed limit.
 * - Entities can be located in a world. Each entity can only be in one world at the
 * 	 same time
 * 
 * @invar Both X- and Y-components of the position must be valid for every
 * 		  entity.
 * 		  | isValidPositionComponent(positionX)
 * 		  | isValidPositionComponent(positionY)
 * @invar Every entity's speed limit must be valid
 * 		  | isValidSpeedLimit(speedLimit)
 * 
 * @version	1.0
 * @author 	De Heel Benson (burgerlijk ingenieur computerwetenschappen - elektrotechniek, 
 * 			De Jaegere Xander burgerlijk ingenieur computerwetenschappen - elektrotechniek) 
 */
public abstract class Entity {
	
	/**
   	 * Initialize this new entity with the given parameters.
   	 * 
   	 * @param positionX
   	 * 			The x-component of the position of the entity.
   	 * @param positionY
   	 * 			The y-component of the position of the entity.
   	 * @param velocityX
   	 * 			The x-component of the velocity of the entity.
   	 * @param velocityY
   	 * 			The y-component of the velocity of the entity.
   	 * @param radius
   	 * 			The radius of the entity.
   	 * @param speedLimit
   	 * 			The maximum speed that this entity can reach.
   	 * @post    If the given speedLimit is a valid speed limit,
   	 * 			the maximum speed of this entity will be equal to
   	 * 			the given speedLimit. If not, the maximum speed limit
   	 * 			of this entity will be equal to the constant speed of light c.
   	 * 			| if (isValidSpeedLimit(speedLimit)
   	 * 			|	then new.getSpeedLimit() == speedLimit
   	 * 			| else
   	 * 			| 	then new.getSpeedlimit() == c 
   	 * @effect	The position of this entity will be equal to the given
   	 * 			X- and Y-component (positonX and positionY).
   	 * 			| setPosition(positionX, positionY)
   	 * @effect	The velocity of this entity will be equal to the given
   	 * 			X- and Y-component (velocityX and velocityY)
   	 * 			| setVelocity(velocityX,velocityY)
   	 * @throws 	IllegalArgumentException
	 * 				The given positionX is not valid.
	 * 				| isValidPositionComponent(positionX)
	 * @throws	IllegalArgumentException
	 * 				The given positionY is not valid.
	 * 				| isValidPositionComponent(positionY)
   	 */
	public Entity(double positionX, double positionY, double velocityX, 
   			double velocityY, double speedLimit) throws IllegalArgumentException{
 		// !Important the speed limit has to be defined before the assignment of the velocity.
		if (isValidSpeedLimit(speedLimit))
   			this.speedLimit = speedLimit;
   		else
   			this.speedLimit = c;
   		
		setPosition(positionX, positionY);
 		setVelocity(velocityX, velocityY);
	}
	
	/**
	 * initializes this new entity with its given parameters and its speed limit set 
	 * to its default value, which is the speed of light.
	 * 
   	 * @param positionX
   	 * 				The x-component of the position of the entity.
   	 * @param positionY
   	 * 				The y-component of the position of the entity.
   	 * @param velocityX
   	 * 				The x-component of the velocity of the entity.
   	 * @param velocityY
   	 * 				The y-component of the velocity of the entity.
	 * @effect 		This entity will be set at the given coordinates with the given
	 * 				velocity and its speed limit set to the speed of light.
	 * 				| this(positionX, positionY, velocityX, velocityY, c)
   	 * @throws 	IllegalArgumentException
	 * 				The given positionX is not valid.
	 * 				| isValidPositionComponent(positionX)
	 * @throws	IllegalArgumentException
	 * 				The given positionY is not valid.
	 * 				| isValidPositionComponent(positionY)
	 */
   	public Entity(double positionX, double positionY, double velocityX, 
   			double velocityY) throws IllegalArgumentException{
   		this(positionX, positionY, velocityX, velocityY, c);
   	}
   	
	/**
	 * Initialize this new entity with its default values.
	 * 
	 * @effect	This entity will be placed in the origin with no speed and
	 * 			with the speed of light as its speed limit.
	 * 			|this(0, 0, 0, 0, c)
	 */
   	public Entity() {
   		this(0, 0, 0, 0, c);
	}
	
   	
	// Position [DEFENSIVE]
	/**
	 * Return the position of this entity.
	 * 
	 * @return	The position of this entity represented by an 
	 * 			object of the class Vector.
	 */
	@Basic
	public Vector getPosition() {
		return this.position;
	}
	
	/**
	 * Set the position of this entity to the given x and y value.
	 * 
	 * @param x
	 * 			The x-component of the position of this entity.
	 * @param y
	 * 			The y-component of the position of this entity.
	 * @post 	The position of this entity will be equal to the given
	 * 			X-component and Y-component
	 * 		   	| new.getPosition().getX() == x
	 * 			| new.getPosition().getY() == y
	 * @throws IllegalArgumentException
	 * 			The given X-component is not valid.
	 * 			| !isValidPositionComponent(x)
	 * @throws IllegalArgumentException
	 * 			The given Y-component is not valid.
	 * 			| !isValidPositionComponent(y)
	 */
	@Basic
	public void setPosition(double x, double y) throws IllegalArgumentException{
		if (!isValidPositionComponent(x))
			throw new IllegalArgumentException("The given X-component is not valid.");
		if (!isValidPositionComponent(y))
			throw new IllegalArgumentException("The given Y-component is not valid.");
		
		this.position = new Vector(x, y);
	}
		
	/**
	 * Check whether the given value is a valid value for one of the components
	 * of the position of this ship.
	 * A value is a valid position component if it is finite and not NaN. 
	 * 
	 * @param value
	 * 			The value that has to be verified.
	 * @see implementation
	 */
	public boolean isValidPositionComponent(double value){
		return !(Double.isNaN(value));
	}
	
	/**
	 * Change the position of this entity based on the current position, velocity 
	 * and given duration.
	 * 
	 * @param duration
	 * 				The considered duration of the movement.
	 * @post		The position of this entity will be set to the position it has after the
	 * 				considered amount of time.
	 * 				| setPosition(getPosition().getX()+duration*getVelocity().getX(), 
	 * 						  	  getPosition().getY()+duration*getVelocity().getY())
	 * @throws	IllegalArgumentException
	 * 				The given duration is equal to NaN.
	 * 				| duration == Double.NaN
	 */
	public void move(double duration) throws IllegalArgumentException{
		if (Double.isNaN(duration))
			throw new IllegalArgumentException("Duration cannot be NaN");
		Vector current_pos = getPosition();
		Vector current_vel = getVelocity();
		setPosition(current_pos.getX()+current_vel.getX()*duration, current_pos.getY()+current_vel.getY()*duration);
	}
	
	/**
	 * Variable registering the position of this entity.
	 */
	private Vector position = new Vector(0, 0);


	// Velocity [TOTAL]
	/**
	 * Return the velocity of this entity.
	 * 
	 * @return The velocity of this entity represented as an 
	 * 		   object of the class Vector.
	 */	
	@Basic
	public Vector getVelocity() {
		return this.velocity;
	}

	/**
	 * Set the velocity of this entity to the given velocityX and velocityY values.
	 * 
	 * @param velocityX
	 * 			The x-component of the velocity of this entity.
	 * @param velocityY
	 * 			The y-component of the velocity of this entity.
	 * @post	If the X- and Y-components are valid, the velocity will be set to
	 * 			a vector representing the given velocity.
	 * 		   | if (isValidVelocity(velocityX, velocityY)
	 * 		   | 	then new.velocity == Vector(velocityX, velocityY)
	 * @post	If the Euclidean norm of velocityX and velocityY is larger than the
	 * 			Entity's speed limit, velocityX and velocityY will be rescaled such that
	 * 			the Euclidean norm of velocityX and velocityY is equal to the Entity's speed limit.	
	 * 		   | if (sqrt(velocityX^2+velocityY^2) > speedLimit)
	 * 		   | 	then new.velocity == [speedLimit*cos(theta), speedLimit*sin(theta)]
	 *		   |		with theta = atan(velocityY/velocitX)
	 */
	@Basic @Raw
	public void setVelocity(double velocityX, double velocityY){
		if (Double.isNaN(velocityX) || (Double.isNaN(velocityY)))
			return;
		if (isValidVelocity(velocityX, velocityY)){
			this.velocity = new Vector(velocityX, velocityY);
		} else if (Double.isInfinite(velocityX) || Double.isInfinite(velocityY))
			// Do nothing when one of the two components is infinite.
			return;
		else {
		// If isValidVelocity returns False, it means that the total velocity > c
		// So we will have to rescale the length of the vector and keep the original orientation.
		double theta = Math.atan2(velocityY, velocityX);
		this.velocity = new Vector(c*Math.cos(theta), c*Math.sin(theta));
		}
	}
	
	/**
	 * Return the speed limit of this entity.
	 * 
	 * @see implementation
	 */
	@Basic
	public double getSpeedLimit(){
		return this.speedLimit;
	}
	
   	/**
   	 * Return the length of the velocity vector of this entity.
   	 * 
   	 * @effect	The length of the velocity vector is equal to the norm
   	 * 			of the Vector representing this Entity's velocity.
   	 * 			| this.getVelocity().norm()
   	 */
	@Basic
	public double getTotalVelocity(){
		return getVelocity().norm();
	}
	
	/**
	 * Checks whether the total velocity is smaller than or equal to the speed limit of this entity.
	 * 
	 * @param velocityX 
	 * 		  	The x-component of the velocity vector.
	 * @param velocityY
	 * 		  	The y-component of the velocity vector.
	 * @return 	True if and only if the velocity is smaller than or equal to the speed limit of this entity.
	 * 		   | result == sqrt(velocityY^2 + velocityX^2) <= speedLimit
	 */
	@Basic
	public boolean isValidVelocity(double velocityX, double velocityY){
		Vector velocity = new Vector(velocityX,velocityY);
		return velocity.norm() <= getSpeedLimit();
	}
	
	/**
	 * Checks whether the given speedLimit is a valid speed.
	 * 
	 * @param speedLimit
	 * 			The speed limit that needs to be verified.
	 * @return 	True if and only if the given speed limit is larger than 0 and smaller
	 * 			than the constant speed of light c (300 000 km/s).
	 * 			| if (0 <= speedLimit <= c) then true
	 * 			| false otherwise
	 */
	@Basic
	public boolean isValidSpeedLimit(double speedLimit){
		return ((speedLimit >= 0) && (speedLimit <= c));
	}
	
	/**
	 * Variable registering the velocity of this entity.
	 */
	private Vector velocity = new Vector(0, 0);
	
	/**
	 * Variable registering the maximum velocity of this entity expressed in kilometers/s
	 */
	private final double speedLimit;
	
	
	// Radius [DEFENSIVE]
	/**
	 * get the radius of an Entity
	 */
	@Basic
	public abstract double getRadius();
	
	
   	// Mass [TOTAL]
	/**
	 * get the total mass of an entity
	 */
	@Basic
   	public abstract double getTotalMass();
	
   	
   	// World-Entity Relation
   	/**
   	 * Sets the world of the entity to the given world.
   	 * 
   	 * @param world
   	 * @post The entity's world will be set to the given world if
   	 * 		 the given world is valid.
   	 * 		| if (canHaveAsWorld)
   	 * 		| then new.getWorld() == world
   	 * @throws IllegalStateException
   	 * 		When this entity already lies in a world or the entity is terminated.
   	 * 		| if (!(getWorld() == null) || if (this.isDead())
   	 */
	@Raw @Basic
   	public void setWorld(World world) throws IllegalStateException {
   		if (!(getWorld() == null))
   			throw new IllegalStateException("This entity already has a world.");
   		if (this.isDead())
			throw new IllegalStateException("this entity is terminated and can't have a world");
   		if (canHaveAsWorld(world))
   			this.world = world;
   			
   	}
   	
   	/**
   	 * Removes the entity from it's world.
   	 * 
   	 * @post	The entity's world will be set to null
   	 * 			| new.getWorld() == null
   	 */
	@Raw
   	public void removeWorld() {
   		this.world = null;
   	}
   	
   	/**
   	 * Returns the world of this entity.
   	 * 
   	 * @see implementation
   	 */
	@Basic
   	public World getWorld(){
   		return this.world;
   	}
   	
   	/**
   	 * Checks whether this entity can have the given world as its world.
   	 * 
   	 * @param world
   	 * 			The world that has to be verified.
   	 * @effect	Returns true if the given world can hold this entity, returns
   	 * 			false otherwise.
   	 * 			| world.canHaveAsEntity(this)
   	 */
	@Basic
   	public boolean canHaveAsWorld(World world){
   		return world.canHaveAsEntity(this);
   	}
   	
   	/**
   	 * Variable registering the world where this entity is in contained.
   	 */
   	private World world;
   	
   	
   	// ------- Other functions --------
   	
   	
   	/**
	 * Checks whether an entity lies completely within another entity
	 * 
	 * @param entity
	 * 			The entity that has to be verified.
	 * @return
	 * 			|True if (distanceBetweenCenters + entity.getRadius() <= totalRadius);
	 */
	public boolean contains(Entity entity){
		double distanceBetweenCenters = getDistanceBetweenCenters(entity);
		double radius = getRadius();
		return (distanceBetweenCenters + entity.getRadius() <= radius);
	}
	
	
   	/**
   	 * Get the distance between this Entity and the given other Entity.
   	 * 
   	 * @param otherEntity
   	 * 				The other entity involved in the calculations.
   	 * @see implementation
   	 */
   	public double getDistanceBetweenCenters(Entity otherEntity){
 		Vector differenceBetweenVectors = getPosition().subtract(otherEntity.getPosition());
 		
   		return differenceBetweenVectors.norm(); 
   	}
   	
   	/**
   	 * Checks whether an entity overlaps significantly with another entity.
   	 * 
   	 * @param otherEntity
   	 * 			The other entity that is involved in the calculations.
     * @return True if and only if the distance between both entities' centers
     * 		   is smaller than or equal to 99% of the sum of both entities' radiuses.
     * 		   | result == (getDistanceBetweenCenters(otherEntity) 
     * 		   |			<= 0.99*(getRadius() + otherEntity.getRadius()) 
   	 */
   	public boolean overlapSignificantly(Entity otherEntity){
   		double distance = getDistanceBetweenCenters(otherEntity);
   		double totalRadius = getRadius() + otherEntity.getRadius();
   		
   		return distance <= (0.99*totalRadius);
   	}
   	
   	/**
   	 * Checks whether an entity overlaps with another entity.
   	 * 
   	 * @param otherEntity
   	 * @return	True if and only if the distance between both entities' centers
   	 * 			is between 99% and 101% of the sum of both entities' radiuses.
   	 * 			False otherwise
   	 * 			| let (totalRadius = getRadius() + otherEntity.getRadius())
   	 * 			|	 result == (0.99*totalRadius <= getDistanceBetweenCenters(otherEntity)
   	 * 			|								 <= 1.01*totalRadius)
   	 */
   	public boolean apparentlyCollide(Entity otherEntity) {
   		double distance = getDistanceBetweenCenters(otherEntity);
   		double totalRadius = getRadius() + otherEntity.getRadius();
   		
   		return (0.99*totalRadius <= distance) && (distance <= 1.01*totalRadius);  
   	}

	/**
	 * Calculate the distance in between this entity and a given other entity.
	 * 
	 * @param otherEntity
	 * 			The other entity involved in the calculation of the distance in between
	 * 			both entities.
	 * @return 	Zero if this entity equals the given other entity.
	 * 			| if(this == otherEntity)
	 * 			| 	then result == 0
	 * 
	 * @return	The distance between this entity and otherEntity. 
	 * 			The distance may be negative if both entities overlap. 
	 * 			| if (this.overlap(otherEntity))
	 * 			|	then result < 0
	 * 			| else
	 * 			| 	result > 0
	 * 
	 * @throws	NullPointerException
	 * 			The given otherEntity is null.
	 * 			| otherEntity == null
	 * @throws IllegalStateException
	 * 			| if (this.isDead() or otherEntity.isDead())
	 */
	public double getDistanceBetween(Entity otherEntity)throws NullPointerException,IllegalStateException{
		if (otherEntity == null)
			throw new NullPointerException();
		if (this.isDead() || otherEntity.isDead())
			throw new IllegalStateException("can't get distance between an terminated entity");
		if (this == otherEntity)
			return 0;
		
		Vector thisPos = getPosition();
		Vector otherPos = otherEntity.getPosition();
				
		Vector deltaPos = thisPos.subtract(otherPos);
		double distanceBetweenMid = deltaPos.norm(); 
		double minRadius = Math.min(getRadius(), otherEntity.getRadius());
		double maxRadius = Math.max(getRadius(), otherEntity.getRadius());
		// The center of a ship is in the others radius.
		if ((distanceBetweenMid < minRadius) && (2*minRadius <= maxRadius)){
			return -(maxRadius -distanceBetweenMid-minRadius);
		}
			
		return distanceBetweenMid-(getRadius()+otherEntity.getRadius());
	}
   	
	/**
	 * Checks whether two entities overlap.
	 * 
	 * @param otherEntity
	 * 			The other entity involved in the calculations.
	 * @return	True if and only if the distance between this entity and 
	 * 			otherEntity is zero or negative.
	 * 			| result == (this.getDistanceBetween(otherEntity) <= 0)
	 * @throws IllegalStatException
	 * 			| if (this.isDead() || otherEntity.isDead())
	 * @throws NullPointerException
	 * 			| if otherEntity == null
	 */
	public boolean overlap(Entity otherEntity) throws NullPointerException,IllegalStateException{
		if (otherEntity == null)
			throw new NullPointerException("otherEntity is null");
		if (this.isDead() || otherEntity.isDead())
			throw new IllegalStateException("can't get distance between an terminated entity");
		return getDistanceBetween(otherEntity) <= 0;
	}
	/**

	*/
	/**
	 * Calculate the time it takes for this entity and the given otherEntity to collide.
	 * 
	 * @param otherEntity
	 * 			The other entity that collides with this entity.
	 * @return The resulting double will be the time in seconds that it takes for the 
	 * 			two entities to collide.
     * 			| if( this.move(result) && otherEntity.move(result) 
	 * 			|	then let result == new.getDistanceBetween(newOtherEntity) == 0
	 * @return The resulting double will be the time to the first collision 
	 * to occur between the two ships
	 * 			| for all doubles where 
	 * 			|		this.move(double).overlapSignificantly(otherEntity.move(double)):
	 * 			| 	then let double >= result
	 * @throws	NullPointerException
	 * 			If otherEntity is null
	 * 			| otherEntity == null
	 * @throws	IllegalArgumentException
	 * 			If both entities already collided (if they overlap)
	 * 			| overlapSignificantly(otherEntity)
	 * @throws IllegalStateException
	 * 			| if (this.isDead() || otherEntity.isDead())
	 */
	public double getTimeToCollision(Entity otherEntity) 
			throws NullPointerException,IllegalArgumentException,IllegalStateException{
		if (otherEntity == null)
			throw new NullPointerException("otherEntity is null");
		if (this.isDead() || otherEntity.isDead())
			throw new IllegalStateException("can't get time to colission between an terminated entity");
		if(overlapSignificantly(otherEntity))
			throw new IllegalArgumentException("The entities overlap significantly");
		// if one of the two entities is not in a world, the two entities will never collide
		if (getWorld() == null || otherEntity.getWorld() == null)
			return Double.POSITIVE_INFINITY;
		// both entities must be in the same world
		if (getWorld() != otherEntity.getWorld())
			return Double.POSITIVE_INFINITY;
			
		double totalRadius = getRadius()+otherEntity.getRadius();
		
		Vector thisPos = getPosition();
		Vector otherPos = otherEntity.getPosition();
		Vector deltaPos = otherPos.subtract(thisPos);
		
		Vector thisVel = getVelocity();
		Vector otherVel = otherEntity.getVelocity();
		Vector deltaVel = otherVel.subtract(thisVel);
		
		double VTimesR = deltaPos.dot(deltaVel);
		double Vquad = deltaVel.squaredNorm(); 
		double Rquad = deltaPos.squaredNorm();
		
		double d = Math.pow(VTimesR, 2.0)- Vquad*(Rquad-Math.pow(totalRadius, 2.0));
		
		if (VTimesR >= 0 || d <= 0)
			return Double.POSITIVE_INFINITY;
		
		return -(VTimesR+Math.sqrt(d))/Vquad;
	}

	/** 
	 * Return the position of this entity when this entity and the given otherEntity collide.
	 * This method returns null if this entity and the given other entity never collide.
	 * 
	 * @param otherEntity
	 * 			The other entity that collides with this entity.
	 * @return  Null if both entities never collide.
	 * @return 	A vector representing the coordinates where the collision will take place.
	 * @throws	NullPointerException
	 * 			The given otherEntity is null
	 * 			| otherEntity == null
	 * @throws IllegalArgumentException
	 * 			the entity overlaps with the other entity
	 * 			| this.overlapSignificantly(otherEntity)
	 * @throws IllegalStateException
	 * 			| if (this.isDead() || otherEntity.isDead())
	 */
	public Vector getCollisionPosition(Entity otherEntity) throws NullPointerException,IllegalArgumentException,IllegalStateException{
		if (otherEntity == null)
			throw new NullPointerException("otherEntity is null");
		if (this.isDead() || otherEntity.isDead())
			throw new IllegalStateException("can't get distance between an terminated entity");
		if (overlapSignificantly(otherEntity))
			throw new IllegalArgumentException("This entity overlaps significantly with otherEntity");
		// if one of the two entities is not in a world, the two entities will never collide
		if (getWorld() == null || otherEntity.getWorld() == null)
			return null;
		
		double deltaT = getTimeToCollision(otherEntity);
		
		if (Double.isInfinite(deltaT))
			return null;
		
		Vector pos = getPosition();
		Vector vel = getVelocity();
		Vector pos1 = otherEntity.getPosition();
		Vector vel1 = otherEntity.getVelocity();
		
		double theta = Math.acos(( (pos1.getX()+vel1.getX()*deltaT) - (pos.getX()+vel.getX()*deltaT))/(getRadius()+otherEntity.getRadius()));
		if (pos.getY() +vel.getY()*deltaT < pos1.getY() + vel1.getY()*deltaT)
			return new Vector(pos.getX()+vel.getX()*deltaT+getRadius()*Math.cos(theta), pos.getY()+vel.getY()*deltaT+getRadius()*Math.sin(theta));
		else
			return new Vector(pos.getX()+vel.getX()*deltaT+ getRadius()*Math.cos(-theta), pos.getY()+vel.getY()*deltaT+getRadius()*Math.sin(-theta));
	}
	
	// Entity termination
	/**
	 * Let the entity die
	 * When an entity dies, it will be removed from its world (if any).
	 */
	public void die(){
		if (getWorld() != null)
			getWorld().removeEntity(this);
		
		removeWorld();
		this.isTerminated=true;
	}
	
	/**
	 * Check whether this entity is dead (terminated) or not.
	 */
	@Basic
	public boolean isDead(){
		return this.isTerminated;
	}
	
	/**
	 * Variable registering whether the entity has died.
	 */
	protected boolean isTerminated=false;
	
	/**
	 * Variable registering the Speed of light [km/s].
	 */
   	protected static double c = 300000.0;

}
 
