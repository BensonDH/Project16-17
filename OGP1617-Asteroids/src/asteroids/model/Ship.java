package asteroids.model;
import java.util.HashSet;
import java.util.Set;

import org.hamcrest.core.IsNull;
import be.kuleuven.cs.som.annotate.*;

/**
 * 
 *  Github repository : https://github.com/BensonDH/Project16-17
 */

/**
 * A class representing spaceships. 
 * - Spaceships are represented as circles with radius r [km].
 * - They have a position [km] and velocity [km/s] expressed in a Cartesian coordinate 
 * 	 system with x- and y-coordinates.
 * - They also have an orientation angle, expressed in radians. 
 * 
 * @invar	The orientation angle of each spaceship must be a valid angle.
 *  		| isValidAngle(angle)
 * 
 * @version	1.0
 * @author 	De Heel Benson (burgerlijk ingenieur computerwetenschappen - elektrotechniek, 
 * 			De Jaegere Xander burgerlijk ingenieur computerwetenschappen - elektrotechniek)  
 * 
 */
public class Ship extends Entity {

	/**
   	 * Initialize this new spaceship with the given parameters.
   	 * @param positionX
   	 * 			The x-component of the position of the spaceship.
   	 * @param positionY
   	 * 			The y-component of the position of the spaceship.
   	 * @param velocityX
   	 * 			The x-component of the velocity of the spaceship.
   	 * @param velocityY
   	 * 			The y-component of the velocity of the spaceship.
   	 * @param radius
   	 * 			The radius of the spaceship.
   	 * @param angle
   	 * 			The orientation angle of the spaceship.
   	 * @param density
   	 * 			The density of the spaceship
   	 * @param speedLimit
   	 * 			The maximum velocity of the spaceship.
   	 * @param thrustMode
   	 * 			The mode of the thrusters when initialized, whether they are on or off
   	 * @effect 	this constructor will initialize the ship as an entity with given position, velocity and speedLimit
   	 * 			| super(positionX,positionY,velocityX,velocityY,speedLimit);
   	 * @pre		the angle must be between 0 and 2*PI
   	 * 			| 0 <= angle <= 2*PI
   	 * @post	The radius of this ship will be set to the given radius 
   	 * 			if the given radius is greater than the minimum radius.
   	 * 			| if (isValidRadius(radius))
   	 * 			| 	then new.getRadius() == radius
   	 * @post	The mode of the thrusters will be set to the given mode
   	 * 			| new.getThrust() = thrustMode
   	 * @post	the angle will be set to the given angle
   	 * 			| new.getAngle() == angle
   	 * @throws	IllegalArgumentException
   	 * 			The given radius is infinite or smaller than the minimal radius of this spaceship.
   	 * 			| radius == INFINITE OR radius < minimalRadius
   	 */
   	public Ship(double positionX, double positionY, double velocityX, 
   			double velocityY, double radius, double angle,double mass, boolean thrustMode, double speedLimit) throws IllegalArgumentException {
   		super(positionX,positionY,velocityX,velocityY,speedLimit);

   		if (Double.isNaN(radius) || Double.isInfinite(radius))
			throw new IllegalArgumentException("The given value is not valid.");
		else if (radius < rMin)
			throw new IllegalArgumentException("The given value must be larger than "+rMin+" km.");

   		this.radius = radius;
   		this.thrust = thrustMode;
   		setBaseMass(mass);
   		setAngle(angle);
   	}
	
   	/**
   	 * Initialize this new spaceship with the given parameters and the thrustMode set to its default
   	 * parameter, which is false (off), and its speeldLimit set to the speed of light.
   	 * 
   	 * @param positionX
   	 * 			The x-component of the position of the spaceship.
   	 * @param positionY
   	 * 			The y-component of the position of the spaceship.
   	 * @param velocityX
   	 * 			The x-component of the velocity of the spaceship.
   	 * @param velocityY
   	 * 			The y-component of the velocity of the spaceship.
   	 * @param radius
   	 * 			The radius of the spaceship.
   	 * @param angle
   	 * 			The orientation angle of the spaceship.
   	 * @param density
   	 * 			The density of this spaceship.
   	 * @effect 	The Ship is initialized with the given parameters and its default thrustMode
   	 * 			|this(positionX, positionY, velocityX, velocityY, radius, angle, density, c, false)
   	 */
   	public Ship(double positionX, double positionY, double velocityX, 
   			double velocityY, double radius, double angle,double mass) throws IllegalArgumentException {
   		this(positionX, positionY, velocityX, velocityY, radius, angle, mass, false, c);
   	}
   	
 	/**
   	 * Initialize this new spaceship with the given parameters and the thrustMode set to its default 
   	 *  parameter which, is off, and the speeldLimit set to the speed of light.
   	 *  
   	 * @param positionX
   	 * 			The x-component of the position of the spaceship.
   	 * @param positionY
   	 * 			The y-component of the position of the spaceship.
   	 * @param velocityX
   	 * 			The x-component of the velocity of the spaceship.
   	 * @param velocityY
   	 * 			The y-component of the velocity of the spaceship.
   	 * @param radius
   	 * 			The radius of the spaceship.
   	 * @param angle
   	 * 			The orientation angle of the spaceship 
   	 * @effect 	The Ship is initialized with the given parameters, thrustMode false (off) and a density equal
   	 * 			to the minimum density.
   	 * 			|this(positionX, positionY, velocityX, velocityY, radius, angle, minDensity, c, false)
   	 */
   	public Ship(double positionX, double positionY, double velocityX, 
   			double velocityY, double radius, double angle) throws IllegalArgumentException {
   		this(positionX, positionY, velocityX, velocityY, radius, angle, 0, false, c);
   	}
   	
   	/**
   	 * Initialize this new spaceship by setting it in the origin with no velocity, thrusters on false (off),
	 * a speed limit equal to the speed of light, a radius equal to the minimal radius,
	 * an orientation angle of 0 radians and a density equal to the minimal density.
     *
   	 * @effect 	The spaceship will be initialized with its default values.
   	 * 			| this(0,0,0,0,rMin,0,minDensity,c,false)
   	*/
   	public Ship(){
   		this(0, 0, 0, 0, rMin, 0, 0, false, c);
   	}

   	
   	// Position DEFENSIVE

	// Velocity TOTAL
	/**
	 * Cancels the ship's velocity setting it to 0
	 * @effect The velocity will be set to 0
	 * 			| setVelocity(0, 0)
	 */
	public void killVelocity(){
		setVelocity(0, 0);
	}
	
	/**
	 * Change the ship's velocity based on the current acceleration, velocity, orientation and given amount of time.
	 * 
	 * @param timeAmount
	 * 			The amount of time that the ship thrusts.
	 * @effect 
	 * 			| new.getVelocity() == setVelocity(getVelocity().getX()+getShipAcceleration()*cos(getAngle())*timeAmount,
	 * 			|					    		   getVelocity().getY()+getShipAcceleration()*sin(getAngle())*timeAmount)
	 */
	public void thrust(double timeAmount){
		Vector velocity = getVelocity();

		setVelocity(velocity.getX()+getShipAcceleration()*Math.cos(getAngle())*timeAmount, 
					velocity.getY()+getShipAcceleration()*Math.sin(getAngle())*timeAmount);
	}
	
	/**
	 * TODO: Documentation
	 * @return
	 */
	public double getShipAcceleration(){
		return getThrusterForce()/getTotalMass();
	}
	
	/**
	 * Return the force generated by this ship's thruster.
	 */
	public double getThrusterForce(){
		return this.force;
	}
	
	/**
	 * variable registering the force of this ship's thruster
	 */
	private double force = 1.1 * Math.pow(10,21);
	
	
	// Thrusters total
	/**
	 * TODO: Documentation
	 * @return
	 */
	public boolean isShipThrusterActive(){
		return getThrust();
	}
	
	/**
	 * TODO: Documentation
	 * @return
	 */
	private boolean getThrust() {
		return this.thrust;
	}
	
	/**
	 * TODO: Documentation
	 * @param thrust
	 */
	public void setThrust(boolean thrust){
		this.thrust = thrust;
	}
	
	/**
	 * Variable registering whether the truster is On (true) or Off (false).
	 */
	private boolean thrust;
	
	
	// Orientation NOMINAL
	
	
	/**
	 * Return the orientation angle of this ship, expressed in radians.
	 */
	@Basic
	public double getAngle(){
		return this.angle;
	}
	
	/**
	 * Set the orientation angle of this spaceship, expressed in radians.
	 * @param angle
	 * 		  The new orientation angle of this spaceship
	 * @pre	  The given angle must be between 0 and 2*PI
	 * 		  | 0 < angle < 2*PI
	 * @post  The orientation angle is equal to the given angle.
	 * 		  | new.getAngle() == angle
	 */
	@Basic
	public void setAngle(double angle){
		assert isValidAngle(angle);
		this.angle = angle;
	}
	
	/**
	 * Checks whether the given angle is a valid angle.
	 * @param currentAngle		The current angle of the spaceship.
	 * @return					True if and only if the given angle is between 0 and 2*PI
	 * 							| result == (0 <= angle) AND (angle <= 2*PI)
	 */
	public boolean isValidAngle(double angle){
		return (0 <= angle) && (angle <= 2*Math.PI);
	}
	
	/**
	 * Turn the spaceship with the given angle.
	 * @param angle
	 * 			The size of change of rotation the angle.
	 * 			A negative angle will result in an anti-clockwise rotation.
	 *  		A positive angle will result in a clockwise rotation.
	 * @effect 	The angle of the ship is incremented by the given angle.
	 * 			|setAngle(getAngle()+angle)
	 */
	public void turn(double angle){
		setAngle(getAngle()+angle);
	}

	/**
	 * Variable registering the orientation angle of the spaceship, expressed in radians.
	 */
	private double angle;
	
	
	// Radius DEFENSIVE
	/**
	 * return the radius of this spaceship, expressed in kilometers.
	 */
	@Basic @Immutable @Override
	public double getRadius(){
		return this.radius;
	}	
	
	/**
	 * Variable registering the radius of the spaceship, expressed in kilometers.
	 */
	private final double radius;
	
	
	/**
   	 * Variable registering the minimal radius of a spaceship
   	 */
   	static double rMin = 10;
   	
   	// Mass [TOTAL]
    /**
     * Return the total Mass of this spaceship.
     * 
     * @return 	The total mass is equal to the sum of the mass of the ship
     * 		   	and the mass of all the bullets.
     * 			| result == (4/3)*getRadius()^3*getDensity()
     * 			|			+ (for each bullet in this ship:
     * 			|					+ bullet.getMass)
     * 
     */
    public double getTotalMass(){
    	if (loadedBullets.isEmpty())
    		return getBaseMass();
    	else{
    		double massOfAllBullets = loadedBullets.size()*loadedBullets.iterator().next().getTotalMass();
    		return getBaseMass() + massOfAllBullets;
    	}
    }
    
    /**
     * TODO: Documentation
     * @param mass
     * @return
     */
    public void setBaseMass(double mass){
    	if (isValidBaseMass(mass))
    		this.mass = mass;
    	else
    		this.mass = (4/3)*Math.PI*Math.pow(getRadius(), 3.0)*getDensity();
    }
    
    /**
     * TODO: Documentation
     * @return
     */
    public double getBaseMass(){
    	return this.mass;
    }
    
    /**
     * TODO: Documentation
     * @param mass
     * @return
     */
    public boolean isValidBaseMass(double mass){
    	return mass >= (4/3)*Math.PI*Math.pow(getRadius(), 3.0)*getDensity();
    }
    
    /**
     * Variable registering the base mass of the spaceship
     * this is the mass of the ship without the bullets.
     */
    private double mass;
    
    /**
     * TODO: Documentation
     * @return
     */
    public double getDensity(){
    	return this.density;
    }
    	
    
    /**
     * Variable registering the density of this ship.
     */
    private final double density = 1.42 * Math.pow(10,12);	
    	
   	
  	//-----------ASSOCIATIONS-------------
   	//--------BUllets-------- [DEFENSIVE] except for method fire()
    /**
     * TODO: Documentation
     * @param bullet
     * @return
     */
   	public boolean canHaveAsBullet(Bullet bullet){
   		if (bullet == null)
   				return false;
   		if (loadedBullets.contains(bullet))
   			return false;
   		if (bullet.getShip() != null ||bullet.getWorld() != null)
   			return false;
   		if (getDistanceBetweenCenters(bullet) + bullet.getRadius() > getRadius())
   			return false;
   		else
   			return true;	
   	}
   	
   	/**
   	 * TODO: Documentation
   	 * @param bullets
   	 * @throws NullPointerException
   	 * @throws IllegalArgumentException
   	 */
   	public void addBullet(Bullet...bullets)throws NullPointerException,IllegalArgumentException{
   		for(Bullet bullet:bullets){
   			if(bullet == null)
   				throw new NullPointerException();
   			if (!canHaveAsBullet(bullet) || !bullet.canHaveAsShip(this))
   				throw new IllegalArgumentException();
   			else {
   				if (bullet.getWorld() != null) {
   					// Remove the bullet from its current world
   					bullet.getWorld().removeEntity(bullet);
   				}
   				bullet.setSourceShip(null);
   				bullet.setShip(this);
   				loadedBullets.add(bullet);
   			}
   		}
   	}
	
   	/**
   	 * TODO: Documentation
   	 */
   	public Set<Bullet> getBullets(){
   		return this.loadedBullets;
   	}
   	
   	/**
   	 * A set registering all the bullet that are loaded in this spaceship.
   	 */
   	private Set<Bullet> loadedBullets = new HashSet<Bullet>();
	
   	
	/**
	 * Variable registering the Speed of light.
	 */
   	static double c = 300000.0;	 
}

