package asteroids.model;

import org.hamcrest.core.IsNull;
import be.kuleuven.cs.som.annotate.*;

public class Bullet extends Entity {
	/**
	 * Initializes the bullet with the given values
	 * @param positionX
	 * @param positionY
	 * @param velocityX
	 * @param velocityY
	 * @param radius
	 * @param speedLimit
	 * @effect
	 * 			|super(positionX,positionY,velocityX,velocityY,speedLimit)
	 * @Post
	 * 			|if (isValidRadius(radius))
	 * 			| then new.getRadius() == radius
	 * @throws IllegalArgumentException
	 * 			|if (!isValidRadius(radius))
	 */
	@Raw
	public Bullet(double positionX, double positionY, double velocityX, double velocityY,
				  double radius, double speedLimit) throws IllegalArgumentException{
		super(positionX, positionY, velocityX, velocityY, speedLimit);
		
		if (isValidRadius(radius))
			this.radius = radius;
		else
			throw new IllegalArgumentException("The given radius is not valid.");
	}
	
	/**
	 * Initializes the bullet with the given values and its speed limit set to the speed of light
	 * @param positionX
	 * @param positionY
	 * @param velocityX
	 * @param velocityY
	 * @param radius
	 * @effect
	 * 			|this(positionX,positionY,velocityX,velocityY,radius,c)
	 */
	@Raw
	public Bullet(double positionX,double positionY,double velocityX, double velocityY,double radius){
		this(positionX,positionY,velocityX,velocityY,radius,c);
	}
	
	/**
	 * Initializes the bullet with default parameters
	 * @effect
	 * 		|this(0,0,0,0,minRadius)
	 */
	@Raw
	public Bullet(){
		this(0,0,0,0,1,c);
	}
	
	
	// Radius [DEFENSIVE]
	/**
	 * Return the radius of this bullet.
	 */
	@Override @Basic @Immutable
	public double getRadius(){
		return this.radius;
	}
	
	/**
	 * Checks whether the given radius is a valid radius
	 * @param radius
	 * @see implementation
	 */
	@Basic
	public boolean isValidRadius(double radius) {
		return (!(Double.isNaN(radius)) && !Double.isInfinite(radius) && getMinimumRadius() <= radius);
	}
	
	/**
	 * variable registering the radius of this bullet
	 */
	private final double radius;
	
	/**
	 * Return the minimal radius of this bullet.
	 */
	@Basic @Immutable
	public double getMinimumRadius(){
		return Bullet.minimumRadius;
	}
	
	/**
	 * Variable registering the minimal radius of this bullet.
	 */
	private static double minimumRadius = 1;
	
	
	// Mass [TOTAL]
	/**
	 * returns the mass of this bullet.
	 * @see implementation 
	 */
	@Basic
	public double getTotalMass(){
		return (4.0/3)* Math.PI* Math.pow(this.getRadius(), 3.0)*getDensity();
	}
	
	/**
	 * returns the density of the bullet
	 */
	@Basic
	public double getDensity(){
		return Bullet.density;
	}
	
	/**
	 * Variable registering the density of bullets.
	 */
	private static double density = 7.12*Math.pow(10.0, 12.0);
	
	
	// Bounce counter
	/**
	 * Get the number of times a bullet has bounced off one of the boundaries of
	 * a world.
	 * 
	 * @see implementation	
	 */
	@Basic
	public int getNbTimesBounced(){
		return nbTimesBounced;
	}
	
	/**
	 * Variable registering how many times a bullet has bounced with a world's border.
	 */
	private int nbTimesBounced=0;
	
	/**
	 * Set the amount of times a bullet has bounced off a world boundary to
	 * the given times.
     * 
     * @see implementation
	 */
	@Raw
	public void setNbTimesBounced(int times){
		if (times <= getMaxTimesBounced())
			nbTimesBounced = times;
	}
	
	/**
	 * Get the maximum amount of times a bullet can bounce with a world's boundaries.
	 */
	@Immutable
	public int getMaxTimesBounced(){
		return maxTimesBounced;
	}
	
	/**
	 * Variable registering the maximum number of times a bullet can bounce off one of
	 * it's worlds borders.
	 */
	private final int maxTimesBounced = 2; 
	
	
	//----------------ASSOCIATIONS--------------
	//---------SHIP---------
	/**
	 * Returns the ship it belongs to. 
	 * 
	 * @return The ship carrying this bullet.
	 * @return Null if no ship is carrying this bullet
	 */
	@Basic
	public Ship getShip(){
		return this.ship;
	}
	
	/**
	 * makes the association between the Ship and the bullet
	 * @param ship
	 * @post
	 * 		| if (canHaveAsShip())
	 * 		| then new.getShip() == ship
	 */
	@Basic
	public void setShip(Ship ship){
		if (canHaveAsShip(ship)){
			this.ship = ship;
		}
	}
	
	/**
	 * checks whether the bullet can have the given ship as it's ship
	 * @param ship
	 * @return 
	 * 		| if( ship == null)
	 * 		| then return true
	 * @effect 
	 * 		|ship.canHaveAsBullet(this)
	 */
	@Basic
	public boolean canHaveAsShip(Ship ship){
		// Ship becomes null when the bullet is fired.
		if (ship == null)
			return true;
		return ship.canHaveAsBullet(this);
	}
	
	/**
	 * Variable registering the ship where this bullet lies in.
	 */
	private Ship ship;
	
	
	/**
	 * Get the ship that fired this bullet (the source ship)
	 * 
	 * @return if ifFired() then result == getShip()
	 * 			else result == null
	 */
	@Basic
	public Ship getSourceShip(){
		return this.sourceShip;
	}
	
	/**
	 * Sets the ship where the bullet came from as it source ship
	 * @param ship
	 * @see implementation
	 */
	@Basic @Raw
	public void setSourceShip(Ship ship){
		this.sourceShip = ship;
	}
	
	/**
	 * Variable registering the source ship when fired.
	 */
	private Ship sourceShip;
	

	/**
	 * Let the entity die
	 * When an entity dies, it will be removed from its world (if any).
	 */
	@Override
	public void die(){
		if (getWorld() != null) {
			getWorld().removeEntity(this);
			removeWorld();
		}
		if(getShip() != null) {
			getShip().removeBullet(this);
			setShip(null);
		}
		setSourceShip(null);
		this.isTerminated = true;
	}
	
	
	/**
	 * Variable registering the Speed of light.
	 */
   	static double c = 300000.0;	 
}
