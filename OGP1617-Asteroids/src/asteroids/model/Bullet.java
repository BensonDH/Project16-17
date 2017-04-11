package asteroids.model;

import org.hamcrest.core.IsNull;
import be.kuleuven.cs.som.annotate.*;

public class Bullet extends Entity {
	/**
	 * TODO: Documentation
	 * @param positionX
	 * @param positionY
	 * @param velocityX
	 * @param velocityY
	 * @param radius
	 * @param speedLimit
	 * @throws IllegalArgumentException
	 */
	public Bullet(double positionX, double positionY, double velocityX, double velocityY,
				  double radius, double speedLimit) throws IllegalArgumentException{
		super(positionX, positionY, velocityX, velocityY, speedLimit);
		setRadius(radius);
	}
	
	/**
	 * TODO: Documentation
	 * @param positionX
	 * @param positionY
	 * @param velocityX
	 * @param velocityY
	 * @param radius
	 */
	public Bullet(double positionX,double positionY,double velocityX, double velocityY,double radius){
		this(positionX,positionY,velocityX,velocityY,radius,c);
	}
	
	/**
	 * TODO: Documentation
	 */
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
	 * TODO: documentation
	 * @param radius
	 * @throws IllegalArgumentException
	 */
	private void setRadius(double radius)throws IllegalArgumentException {
		if (isValidRadius(radius))
			this.radius = radius;
		else
			throw new IllegalArgumentException("The given radius is not valid for this Bullet");
		
	}
	
	/**
	 * TODO: Documentation
	 * @param radius
	 * @return
	 */
	@Basic
	private boolean isValidRadius(double radius) {
		return getRadiusMinimum() <= radius;
	}
	
	/**
	 * Return the minimal radius of this bullet.
	 */
	public double getRadiusMinimum(){
		return this.radiusMinimum;
	}
	
	/**
	 * variable registering the radius of this bullet
	 */
	private double radius;
	
	/**
	 * Variable registering the minimal radius of this bullet.
	 */
	private static double radiusMinimum = 1;
	
	
	// Mass [TOTAL]
	/**
	 * returns the mass of a bullet.
	 * @see implementation 
	 *
	 */
	public double getMass(){
		return (4.0/3)* Math.PI* Math.pow(this.getRadius(), 3.0)* getDensity();
	}
	
	/**
	 * TODO: documentation + implementation of total programming
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
	 * Set the amount of times a bullet has bounced off a world boundary to
	 * the given times.
     * 
     * @see implementation
	 */
	public void setNbTimesBounced(int times){
		if (times <= getMaxTimesBounced())
			nbTimesBounced = times;
	}
	/**
	 * Get the number of times a bullet has bounced off one of the boundaries of
	 * a world.
	 * 
	 * @see implementation	
	 */
	public int getNbTimesBounced(){
		return nbTimesBounced;
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
	

	
	/**
	 * Variable registering how many times a bullet has bounced with a world's border.
	 */
	private int nbTimesBounced=0;
	
	
	//----------------ASSOCIATIONS--------------
	//---------SHIP---------
	private Ship ship;
	
	/**
	 * Returns the ship it belongs to. 
	 * 
	 * @return The ship carrying this bullet.
	 * @return Null if no ship is carrying this bullet
	 */
	public Ship getShip(){
		return this.ship;
	}
	
	/**
	 * TODO: Documentation
	 * @param ship
	 * @return
	 */
	public boolean canHaveAsShip(Ship ship){
		if (ship == null)
			return false;
		return ship.canHaveAsBullet(this);
	}
	
	/**
	 * Variable registering the Speed of light.
	 */
   	static double c = 300000.0;	 
}
