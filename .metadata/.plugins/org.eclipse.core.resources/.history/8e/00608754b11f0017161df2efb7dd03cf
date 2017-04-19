package asteroids.model;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;

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
   	 * 			The given position is infinite or has Double.Nan as a value 
   	 * 			| isInfinite(positionX) || isNaN(positionX)
   	 * 			| isInfinite(positionY) || isNan(positionY)
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
	 * TODO: Documentation
	 * @param positionX
	 * @param positionY
	 * @param velocityX
	 * @param velocityY
	 * @throws IllegalArgumentException
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
	 * @return	The position of this entity represented by a Vector as [xPosition, yPosition].
	 */
	@Basic
	public Vector getPosition() {
		// TODO: .clone is niet nodig als Vector immutable is
		return this.position.clone();
	}
	
	/**
	 * Set the position of this entity to the given x and y value.
	 * @param x
	 * 			The x-component of the position of this entity.
	 * @param y
	 * 			The y-component of the position of this entity.
	 * @post 	The position of this entity will be equal to the given
	 * 			X-component and Y-component
	 * 		   | new.getPosition() == [x, y]
	 * @throws IllegalArgumentException
	 * 				The given parameter x or y is infinite or equal to NaN
	 * 				| x == INFINITE || y == INFINITE || x == NaN || y == NaN
	 */
	@Basic
	public void setPosition(double x, double y) throws IllegalArgumentException{
		if (Double.isNaN(x) || Double.isInfinite(x))
			throw new IllegalArgumentException("The given x-value is not valid.");
		else if (Double.isNaN(y) || Double.isInfinite(y))
			throw new IllegalArgumentException("The given y-value is not valid.");
		
		this.position = new Vector(x, y);
	}
		
	/**
	 * Change the position of this entity based on the current position, velocity 
	 * and given duration.
	 * @param duration
	 * 			The considered duration of the movement.
	 * @post	The position of this entity will be set to the position it has after the
	 * 			considered amount of time.
	 * 			| setPosition(getPosition().getX()+duration*getVelocity().getX(), 
	 * 						  getPosition().getY()+duration*getVelocity().getY())
	 * @throws	IllegalArgumentException
	 * 			The given duration is equal to NaN.
	 * 			| duration == Double.NaN
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
	 * @return
	 * 		A Vector representing the velocity of this entity as [xVelocity, yVelocity]
	 */	
	@Basic
	public Vector getVelocity() {
		return this.velocity.clone();
	}

	/**
	 * Returns the speed limit of this entity.
	 * @return The speed limit of this entity.
	 */
	@Basic
	public double getSpeedLimit(){
		return this.speedLimit;
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
	@Basic
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
   	 * Return the length of the velocity vector of this entity.
   	 * 
   	 * @effect	The length of the velocity vector is equal to the norm
   	 * 			of the Vector representing this Entity's velocity.
   	 * 			| this.getVelocity().norm()
   	 */
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
	public boolean isValidVelocity(double velocityX, double velocityY){
		Vector velocity = new Vector(velocityX,velocityY);
		return velocity.norm() <= getSpeedLimit();
	}
	
	/**
	 * Checks whether the given speedLimit is a valid speed.
	 * @param speedLimit
	 * 			The speed limit that needs to be verified.
	 * @return 	True if and only if the given speed limit is larger than 0 and smaller
	 * 			than the constant speed of light c (300 000 km/s).
	 * 			| if (0 <= speedLimit <= c) then true
	 * 			| false otherwise
	 */
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
	public abstract double getRadius();
   	// Mass [TOTAL]
   	public abstract double getMass();
	
   	
   	// World-Entity Relation
   	/**
   	 * TODO: Documentation
   	 * @param world
   	 * @throws IllegalStateException
   	 */
   	public void setWorld(World world) throws IllegalStateException {
   		if (!(getWorld() == null))
   			throw new IllegalStateException("This entity already has a world.");
   		if (canHaveAsWorld(world))
   			this.world = world;
   	}
   	
   	/**
   	 * TODO: Documentation
   	 * @throws IllegalStateException
   	 */
   	public void removeWorld() throws IllegalStateException {
   		if (getWorld() == null)
   			throw new IllegalStateException("This entity does not have a world.");
   		
   		this.world = null;
   	}
   	
   	/**
   	 * TODO: Documentation
   	 * @return
   	 */
   	public World getWorld(){
   		return this.world;
   	}
   	
   	/**
   	 * TODO: Documentation
   	 * @param world
   	 * @return
   	 */
   	public boolean canHaveAsWorld(World world){
   		return world.canHaveAsEntity(this);
   	}
   	
   	/**
   	 * Variable registering the world where this entity is in contained.
   	 */
   	private World world;
   	
   	
   	// ------- Other functions --------
   	/**
   	 * Get the distance between this Entity and the other Entity
   	 * TODO: Documentation
   	 * @param otherEntity
   	 * @return
   	 */
   	public double getDistanceBetweenCenters(Entity otherEntity){
 		Vector differenceBetweenVectors = getPosition().subtract(otherEntity.getPosition());
 		
   		return differenceBetweenVectors.norm(); 
   	}
   	
   	/**
   	 * TODO: Documentation
   	 * @param otherEntity
   	 * @return
   	 */
   	public boolean overlapSignificantly(Entity otherEntity){
   		double distance = getDistanceBetweenCenters(otherEntity);
   		double totalRadius = getRadius() + otherEntity.getRadius();
   		
   		return distance <= 0.99*totalRadius;
   	}
   	/**
   	 * TODO: Documentation
   	 * @param otherEntity
   	 * @return
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
	 * @return	The distance between this entity and otherEntity. The distance may be
	 * 			negative if both entities overlap. if this == otherEntity the distance will be zero
	 * 			| if(this== otherEntity)
	 * 			| 	then this.getDistanceBetween(otherEntity) == 0
	 * 			| if(this.overlap(otherEntity))
	 * 			|	then this.getDistanceBetween(otherEntity) < 0
	 * 			| else
	 * 			| 	this.getDistanceBetween(otherEntity) > 0
	 * 
	 * @throws	NullPointerException
	 * 			The given otherEntity is null.
	 * 			| otherEntity == null
	 */
	public double getDistanceBetween(Entity otherEntity)throws NullPointerException{
		if (otherEntity == null)
			throw new NullPointerException();
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
	 * @param otherENtity
	 * 			The other entity involved in the calculations.
	 * @return	True if and only if this entity and otherEntity overlap.
	 * 			| this.getDistanceBetween(otherEntity) <= 0
	 */
	public boolean overlap(Entity otherEntity) throws NullPointerException{
		if (otherEntity == null)
			throw new NullPointerException("otherEntity is null");
		return getDistanceBetween(otherEntity) <= 0;
	}
	
	/**
	 * @param otherEntity
	 * 			The other entity that collides with this entity.
	 * @return The resulting double will be the time in seconds that it takes for the two entities to collide.
	 * 			| let
	 * 			| 	new = this.move(result)
	 * 			|   newOtherEntity = otherEntity.move(result)
	 * 			| in
	 * 			|	new.getDistanceBetween(newOtherEntity) == 0
	 */
	public double getTimeToCollision(Entity otherEntity) throws NullPointerException,IllegalArgumentException{
		if (otherEntity == null)
			throw new NullPointerException("otherEntity is null");
		if(overlap(otherEntity))
			throw new IllegalArgumentException("The entities overlap");
			
		double totalRadius = getRadius()+otherEntity.getRadius();
		
		Vector thisPos = getPosition();
		Vector otherPos = otherEntity.getPosition();
		Vector deltaPos = thisPos.subtract(otherPos);
		
		Vector thisVel = getVelocity();
		Vector otherVel = otherEntity.getVelocity();
		Vector deltaVel = thisVel.subtract(otherVel);
		
		double VTimesR = deltaPos.dot(deltaVel);
		double Vquad = deltaVel.norm(); 
		double Rquad = deltaPos.norm();
		
		double d = Math.pow(VTimesR, 2.0)- Vquad*(Rquad-Math.pow(totalRadius, 2.0));
		
		if (VTimesR >= 0 || d <= 0)
			return Double.POSITIVE_INFINITY;
		
		return -(VTimesR+Math.sqrt(d))/Vquad;
	}

	/** 
	 * Return the position of this entity when this ship and the given other entity collide.
	 * 	This method returns null if this entity and the given other entity never collide.
	 * @param otherEntity
	 * 			The other entity that collides with this entity.
	 * @return 	An array with the x- and y-components of the position where this entity and the given otherEntity collide.
	 * @throws	NullPointerException
	 * 			The given otherEntity is null
	 * 			| otherEntity == null
	 * @throws IllegalArgumentException
	 * 			the entity overlaps with the other entity
	 * 			| this.overlap(otherEntity)
	 */
	public double[] getCollisionPosition(Entity otherEntity) throws NullPointerException,IllegalArgumentException{
		if (otherEntity == null)
			throw new NullPointerException("otherShip is null");
		if (overlap(otherEntity))
			throw new IllegalArgumentException("ship overlaps with othership");
		
		double deltaT = getTimeToCollision(otherEntity);
		
		if (Double.isInfinite(deltaT))
			return null;
		
		Vector pos = getPosition();
		Vector vel = getVelocity();
		Vector pos1 = otherEntity.getPosition();
		Vector vel1 = otherEntity.getVelocity();
		
		double theta = Math.acos(( (pos1.getX()+vel1.getX()*deltaT) - (pos.getX()+vel.getX()*deltaT))/(getRadius()+otherEntity.getRadius()));
		if (pos.getY() +vel.getY()*deltaT < pos1.getY() + vel1.getY()*deltaT)
			return new double[]{pos.getX()+vel.getX()*deltaT+getRadius()*Math.cos(theta), pos.getY()+vel.getY()*deltaT+getRadius()*Math.sin(theta)};
		else
			return new double[]{pos.getX()+vel.getX()*deltaT+ getRadius()*Math.cos(-theta), pos.getY()+vel.getY()*deltaT+getRadius()*Math.sin(-theta)};
	}

	// Entity termination
	/**
	 * Let the entity die
	 * When an entity dies, it will be removed from its world (if any).
	 */
	public void die(){
		if (getWorld() != null)
			getWorld().removeEntity(this);
		
		setWorld(null);
	}
	
	/**
	 * Variable registering the Speed of light [km/s].
	 */
   	private static double c = 300000.0;

}
 
