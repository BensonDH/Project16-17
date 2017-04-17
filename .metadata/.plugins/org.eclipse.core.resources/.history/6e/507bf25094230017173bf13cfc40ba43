package asteroids.model;

import be.kuleuven.cs.som.annotate.*;

/**
 *  GitHub repository : https://github.com/BensonDH/Project16-17
 */

/**
 * A class representing Bullets that can be located in a game world or loaded in a Ship.
 * 
 * - Bullets are represented as circles with radiuses r [km].
 * - They have a position [km] and velocity [km/s] expressed in a Cartesian coordinate 
 * 	 system with x- and y-coordinates.
 * - They also have a speed limit, the Bullets' total velocity will never
 * 	 exceed this limit.
 * - Bullets can be loaded in a Ship, a bullet can only be loaded in one ship at
 * 	 the same time.
 * - Bullets can also be located in a game World. Each bullet can only be in one
 * 	 game world at te same time.
 * 
 * 
 * @invar	The X- and Y-component of every bullet's position
 * 			must be valid.
 * 			| isValidPositionComponent(positionX)
 * 			| isValidPositionComponent(positionY)
 * @invar	Every bullet's speed limit must be valid.
 * 			| isValidSpeedLimit(speedLimit)
 * @invar	The radius of every bullet must be valid.
 * 			| isValidRadius(radius)
 *  
 * @version	1.0
 * @author 	De Heel Benson (burgerlijk ingenieur computerwetenschappen - elektrotechniek, 
 * 			De Jaegere Xander burgerlijk ingenieur computerwetenschappen - elektrotechniek) 
 *
 */
public class Bullet extends Entity {
	/**
	 * Initialize a bullet with the given values.
	 * 
   	 * @param positionX
   	 * 			The x-component of the position of this bullet.
   	 * @param positionY
   	 * 			The y-component of the position of this bullet.
   	 * @param velocityX
   	 * 			The x-component of the velocity of this bullet.
   	 * @param velocityY
   	 * 			The y-component of the velocity of this bullet.
   	 * @param radius
   	 * 			The radius of this bullet.
   	 * @param speedLimit
   	 * 			The maximum speed that this bullet can reach.
	 * @effect
	 * 			|super(positionX, positionY, velocityX, velocityY, speedLimit)
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
	 * Initialize a bullet with the given values and its speed limit set 
	 *  to its default value, which is the speed of light
	 * 
   	 * @param positionX
   	 * 			The x-component of the position of this bullet.
   	 * @param positionY
   	 * 			The y-component of the position of this bullet.
   	 * @param velocityX
   	 * 			The x-component of the velocity of this bullet.
   	 * @param velocityY
   	 * 			The y-component of the velocity of this bullet.
   	 * @param radius
   	 * 			The radius of this bullet.
	 * @effect
	 * 			|this(positionX, positionY, velocityX, velocityY, radius, c)
	 */
	@Raw
	public Bullet(double positionX,double positionY,double velocityX, double velocityY,double radius){
		this(positionX,positionY,velocityX,velocityY,radius,c);
	}
	
	/**
	 * Initialize a bullet with it's default parameters
	 * 
	 * @effect
	 * 			|this(0, 0, 0, 0, getMinimumRadius(), c)
	 */
	@Raw
	public Bullet(){
		this(0, 0, 0, 0, getMinimumRadius(), c);
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
	 * Checks whether the given radius is a valid radius.
	 * 
	 * @param radius
	 * 			The radius that has to be verified.
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
	 * Return the minimal radius of every bullet.
	 */
	@Basic @Immutable
	public static double getMinimumRadius(){
		return Bullet.minimumRadius;
	}
	
	/**
	 * Variable registering the minimal radius of every bullet.
	 */
	private static double minimumRadius = 1;
	
	
	// Mass [TOTAL]
	/**
	 * Return the mass of this bullet.
	 * 
	 * @return The total mass of this bullet, which is equal to the
	 * 		   volume of this bullet (a sphere) times the density of this 
	 * 		   bullet.
	 * 		   | result == (4/3)*PI*getDensity()*getRadius()^3
	 */
	@Basic
	public double getTotalMass(){
		return (4.0/3)* Math.PI* Math.pow(this.getRadius(), 3.0)*getDensity();
	}
	
	/**
	 * Return the density of the bullet.
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
	 * Variable registering how many times a bullet has bounced with a world's border.
	 */
	private int nbTimesBounced=0;
	

	
	/**
	 * Return the maximum amount of times a bullet can bounce with a world's boundaries.
	 * 
	 * @see implementation
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
	 * @return Null if no ship is carrying this bullet.
	 */
	@Basic
	public Ship getShip(){
		return this.ship;
	}
	
	/**
	 * Set up the association between the given ship and this bullet.
	 * 
	 * @param ship
	 * 			The ship that will carry this bullet.
	 * @post
	 * 			| if (canHaveAsShip())
	 * 			| then new.getShip() == ship
	 */
	@Basic
	public void setShip(Ship ship){
		if (canHaveAsShip(ship)){
			this.ship = ship;
		}
	}
	
	/**
	 * Check whether this bullet can have the given ship as it's ship.
	 * 
	 * @param ship
	 * 		The ship that has to be verified.
	 * @return 
	 * 		| if (ship == null)
	 * 		|  then result ==  true
	 * @effect 
	 * 		| if (ship != null)
	 * 		|  then result == ship.canHaveAsBullet(this)
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
	 * Get the ship that fired this bullet (the source ship).
	 * 
	 * @return 	Null if this bullet has not been fired yet.
	 *  
	 * @see implementation 
	 */
	@Basic
	public Ship getSourceShip(){
		return this.sourceShip;
	}
	
	/**
	 * Set the ship where this bullet came from as its source ship.
	 * 
	 * @param ship
	 * 			The ship that fired this bullet.
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
	 * Let this bullet die by removing it from its world (if any)
	 * 
	 * @post	If this bullet was located in a world, it is removed
	 * 			from this world.
	 * 			| if (getWorld() != null)
	 * 			|  then new.getWorld().isInWorld() == false
	 * 			|  		new.getWorld() == null
	 * @post	If this bullet was loaded in a ship, it will be removed from
	 * 			this ship.
	 *			| if (getShip() != null)
	 *			|  then !new.getShip().getBullets().contains(this)
	 *			|		 new.getShip() == null
	 * @post	The sourceShip of this bullet will be set to null
	 * 			| new.getSourceShip() == null
	 * @post	This bullet will be terminated.
	 * 			| new.isDead()  	 
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
