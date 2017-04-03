package asteroids.model;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;

public abstract class Entity {
	// TODO: Constructor
	
	
	// Position [DEFENSIVE]
	
	/**
	 * Return the position of this entity.
	 * @return	The position of this entity represented by an array as [xPosition, yPosition].
	 */
	@Basic
	public double[] getPosition() {
		return this.position.clone();
	}
	
	/**
	 * Set the position of this entity to the given x and y value.
	 * @param x
	 * 			The x-component of the position of this entity.
	 * @param y
	 * 			The y-component of the position of this entity.
	 * @post 	
	 * 		   | new.getPosition() == [x, y]
	 * @throws IllegalArgumentException
	 * 				The given parameter x or y is infinite or equal to NaN
	 * 				| x == INFINITE OR y == INFINITE OR x == NaN OR y == NaN
	 */
	@Basic
	public void setPosition(double x, double y) throws IllegalArgumentException{
		if (Double.isNaN(x) || Double.isInfinite(x))
			throw new IllegalArgumentException("The given x-value is not valid.");
		else if (Double.isNaN(y) || Double.isInfinite(y))
			throw new IllegalArgumentException("The given y-value is not valid.");
		
		this.position[0] = x;
		this.position[1] = y;
	}
		
	/**
	 * Variable registering the position of this entity.
	 */
	private double[] position = {0, 0};


	// Velocity [TOTAL]
	
	/**
	 * Return the velocity of this entity.
	 * @return
	 * 		An array representing the velocity of this entity as [xVelocity, yVelocity]
	 */	
	@Basic
	public double[] getVelocity() {
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
	 * @post
	 * 		   | if (isValidVelocity(velocityX, velocityY)
	 * 		   | 	then new.velocity == [velocityX, velocityY]
	 * @post	
	 * 		   | if (sqrt(velocityX^2+velocityY^2) > speedLimit)
	 * 		   | 	then new.velocity == [speedLimit*cos(theta), speedLimit*sin(theta)]
	 *		   |		with theta = atan(velocityY/velocitX)
	 */
	@Basic
	public void setVelocity(double velocityX, double velocityY){
		if (Double.isNaN(velocityX) || (Double.isNaN(velocityY)))
			return;
		if (isValidVelocity(velocityX, velocityY)){
			this.velocity[0] = velocityX;
			this.velocity[1] = velocityY;
		} else if (Double.isInfinite(velocityX) || Double.isInfinite(velocityY))
			// Do nothing when one of the two components is infinite.
			return;
		else {
		// If isValidVelocity returns False, it means that the total velocity > c
		// So we will have to rescale the length of the vector and keep the original orientation.
		double theta = Math.atan2(velocityY, velocityX);
		this.velocity[0] = c*Math.cos(theta);
		this.velocity[1] = c*Math.sin(theta);
		}
	}
	
   	/**
   	 * Return the length of the velocity vector of this entity.
   	 * 
   	 * @return	
   	 * 			| sqrt(getVelocityX()^2+getVelocityY()^2)
   	 */
	public double getTotalVelocity(){
		double[] vel = getVelocity();
		return Math.sqrt(Math.pow(vel[0], 2.0)+Math.pow(vel[1], 2.0));
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
		return Math.sqrt(Math.pow(velocityY, 2.0)+Math.pow(velocityX, 2.0))<= getSpeedLimit();
		
	}
	
	/**
	 * Checks whether the given speedLimit is a valid speed.
	 * @param speedLimit
	 * 			The speed limit that needs to be verified.
	 * @return 	
	 * 			| if (0 <= speedLimit <= c) then true
	 * 			| false otherwise
	 */
	public boolean isValidSpeedLimit(double speedLimit){
		return ((speedLimit >= 0) && (speedLimit <= c));
	}
	
	/**
	 * Variable registering the velocity of this entity.
	 */
	private double[] velocity = {0, 0};
	
	/**
	 * Variable registering the maximum velocity of this entity expressed in kilometers/s
	 */
	private double speedLimit;
	
	
	// radius [DEFENSIVE]
	// TODO: Hoe gaan we radius doen want rMin is verschillend voor bullet en ship
	
	/**
	 * return the radius of this spaceship, expressed in kilometers.
	 */
	@Basic @Immutable
	public double getRadius(){
		return this.radius;
	}	
	
	/**
	 * Check whether the given radius is a valid radius.
	 * @param radius
	 * 			The radius that needs to be verified.
	 * @return
	 * 			| if (radius < getMinimalRadius()) then True
	 * 			| False otherwise
	 */		
	public boolean isValidRadius(double radius){
		return radius > getRadiusLimit();
	}
	
	/**
	 * Variable registering the radius of the spaceship, expressed in kilometers.
	 */
	private final double radius;
	
	/**
	 * Get the minimal radius of this entity.
	 *  @return The minimal radius of this entity.
	 */
	public double getRadiusLimit(){
		return this.rLimit;
	}
	
	/**
   	 * Variable registering the minimal radius of a spaceship
   	 */
   	private double rLimit = 10;
   	
   	// Orientation [NOMINAL]
   	// TODO: hebben bullets ook een orientation?
   	
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
   	
   	public World getWorld(){
   		return this.world;
   	}
   	
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
   	 * TODO: documentatie
   	 * @param otherEntity
   	 * @return
   	 */
   	public double getDistanceBetweenCenters(Entity otherEntity){
   		double[]  thisPosition = getPosition();
   		double[] otherPosition = otherEntity.getPosition();
   		
   		return Math.sqrt(Math.pow(thisPosition[0]-otherPosition[1],2.0)+Math.pow(thisPosition[1]-otherPosition[1], 2.0));
   		
   	}
   	/**
   	 * TODO: Documentation
   	 * @param otherEntity
   	 * @return
   	 */
   	public boolean overlapSignificantly(Entity otherEntity){
   		double distance = getDistanceBetweenCenters(otherEntity);
   		double totalRadius =getRadius() + otherEntity.getRadius();
   		
   		return distance <= 0.99*totalRadius;
   	}























	/**
	 * Variable registering the Speed of light [km/s].
	 */
   	private static double c = 300000.0;

}
 
