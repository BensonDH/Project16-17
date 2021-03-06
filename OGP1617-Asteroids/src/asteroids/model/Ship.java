package asteroids.model;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import be.kuleuven.cs.som.annotate.*;

/**
 *  GitHub repository : https://github.com/BensonDH/Project16-17
 */

/**
 * A class representing (space)ships
 
 * - Ships are represented as circles with radius r [km].
 * - They have a position [km] and velocity [km/s] expressed in a Cartesian coordinate 
 * 	 system with x- and y-coordinates.
 * - They also have an orientation angle, expressed in radians. An orientation angle of 
 *   0 means that the Ship is orientated along the positive X-axis.
 * - Ships have a speed limit, meaning that the ships' total velocity will
 * 	 never exceed this limit.
 * - Ships can load, carry and fire bullets, ships can carry multiple bullets. 
 * - Ships can be located in a world. Each ship can only be in one game world at the same time.
 * 
 * @invar	Both X- and Y-components of each ship must be valid.
 * 			| isValidPositionComponent(positionX)
 * 			| isValidPositionComponent(positionY)
 * @invar	The orientation angle of each ship must be a valid angle.
 *  		| isValidAngle(angle)
 *  @invar	The speed limit of each ship must be valid
 *  		| isValidSpeedLimit(speedLimit)
 * 
 * @version	2.0
 * @author 	De Heel Benson (burgerlijk ingenieur computerwetenschappen - elektrotechniek, 
 * 			De Jaegere Xander burgerlijk ingenieur computerwetenschappen - elektrotechniek)  
 * 
 */
public class Ship extends Entity {

	/**
   	 * Initialize this new (space)ship with the given parameters.
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
   	 * 			The density of the spaceship
   	 * @param speedLimit
   	 * 			The maximum velocity of the spaceship.
   	 * @param thrustMode
   	 * 			The mode of the thrusters when initialized, whether they are on or off
   	 * @effect 	This constructor will initialize the ship as an entity with given position, velocity and speedLimit
   	 * 			| super(positionX, positionY, velocityX, velocityY, speedLimit);
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
   	 * 			The given radius is infinite, NaN or smaller than the minimal radius of this spaceship.
   	 * 			| radius == INFINITE || radius == Double.isNaN() || radius < getMinimalRadius()
   	 */
   	public Ship(double positionX, double positionY, double velocityX, 
   			double velocityY, double radius, double angle,double mass, boolean thrustMode, double speedLimit) throws IllegalArgumentException {
   		super(positionX,positionY,velocityX,velocityY,speedLimit);

   		if (!isValidRadius(radius))
			throw new IllegalArgumentException("The given radius is not valid.");
   		this.radius = radius;
   		this.thrust = thrustMode;
   		setBaseMass(mass);
   		setAngle(angle);
   	}
	
   	/**
   	 * Initialize this new (space)ship with the given parameters and the thrustMode set to its default
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
   	 * Initialize this new (space)ship with the given parameters and the thrustMode set to its default 
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
   	 * Initialize this new (space)ship by putting it in the origin with no velocity, thrusters on false (off),
	 * a speed limit equal to the speed of light, a radius equal to the minimal radius,
	 * an orientation angle of 0 radians and a density equal to the minimal density.
     *
   	 * @effect 	The ship will be initialized with its default values.
   	 * 			| this(0, 0, 0, 0, Ship.getMinimalRadius(), 0, minDensity, c, false)
   	*/
   	public Ship(){
   		this(0, 0, 0, 0, getMinimumRadius(), 0, 0, false, c);
   	}

   	
   	// Position DEFENSIVE

	// Velocity TOTAL
	/**
	 * Cancels the ship's velocity setting it to 0
	 * 
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
	 * @effect 	The velocity of this ship will be changed based on the current velocity, orientation and timeAmount
	 * 			| new.getVelocity() == setVelocity(getVelocity().getX()+getShipAcceleration()*cos(getAngle())*timeAmount,
	 * 			|					    		   getVelocity().getY()+getShipAcceleration()*sin(getAngle())*timeAmount)
	 */
	public void thrust(double timeAmount){
		Vector newVelocity = getVelocity().add(getShipAcceleration().multiply(timeAmount));

		setVelocity(newVelocity.getX(), newVelocity.getY());
	}
	
	/**
	 * Return the accelerationVector of a ship caused by it's thrusters.
	 * 
	 * @See implementation
	 */
	@Basic
	public Vector getShipAcceleration(){
		double a = getTotalAcceleration(); 
		
		return new Vector(a*Math.cos(getAngle()), a*Math.sin(getAngle()));
	}
	
	/**
	 * Return the total acceleration of this spaceship, this result is derived
	 * from the ship's thruster force and the Newton's second law of motion (F = m.a). 
	 *
	 * @return If the ship's thruster is not active, the ship does not accelerate
	 * 		   | if (!isShipThrusterActive()
	 * 		   | 	then result == 0
	 * @return If the ship's thruster is active, the returned acceleration is derived
	 * 		   from the ship's thruster force and Newtons's second law of motion (F = m.a).
	 * 		   | if (isShipThrusterActive()
	 * 		   | 	then result == getThrusterForce()/getTotalMass()
	 */
	@Basic
	public double getTotalAcceleration(){
		if (!isShipThrusterActive())
			return 0;
		
		return getThrusterForce()/getTotalMass();
	}
	
	/**
	 * Return the force generated by this ship's thruster.
	 */
	@Basic
	public double getThrusterForce(){
		return this.force;
	}
	
	/**
	 * variable registering the force of this ship's thruster.
	 */
	private double force = 1.1 * Math.pow(10,21);
	
	
	// Thrusters total
	/**
	 * Check whether the thruster of this ship is active.
	 * 
	 * @return 
	 * 			| getThrust()
	 */
	@Basic
	public boolean isShipThrusterActive(){
		return getThrust();
	}
	
	/**
	 * Return the mode of the thruster, false meaning off and true meaning on.
	 */
	@Basic
	private boolean getThrust() {
		return this.thrust;
	}
	
	/**
	 * Sets the thrust mode to the given value.
	 * 
	 * @param thrust
	 * 			A boolean to set the thruster on or off.
	 * @post 	The thruster will be set on or off, according to the given value.
	 * 			| new.getThrust() == thrust 
	 */
	@Basic @Raw
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
	 * Set the orientation angle of this ship, expressed in radians.
	 * 
	 * @param angle
	 * 		  The new orientation angle of this ship
	 * @pre	  The given angle must be between 0 and 2*PI
	 * 		  | 0 < angle < 2*PI
	 * @post  The orientation angle is equal to the given angle.
	 * 		  | new.getAngle() == angle
	 */
	@Basic @Raw
	public void setAngle(double angle){
		assert isValidAngle(angle);
		this.angle = angle;
	}
	
	/**
	 * Checks whether the given angle is a valid angle.
	 * 
	 * @param Angle		
	 * 				The angle that has to be verified.
	 * @return		True if and only if the given angle is between 0 and 2*PI
	 * 				| result == (0 <= angle <= 2*PI)
	 */
	@Basic
	public boolean isValidAngle(double angle){
		return (0 <= angle) && (angle <= 2*Math.PI);
	}
	
	/**
	 * Turn the ship with the given angle.
	 * 
	 * @param angle
	 * 			The size of the change in rotation angle.
	 * 			A negative angle will result in an anti-clockwise rotation.
	 *  		A positive angle will result in a clockwise rotation.
	 * @effect 	The angle of the ship is incremented by the given angle.
	 * 			|setAngle(getAngle()+angle)
	 */
	public void turn(double angle){
		setAngle(getAngle()+angle);
	}

	/**
	 * Variable registering the orientation angle of the ship, expressed in radians.
	 */
	private double angle;
	
	
	// Radius DEFENSIVE
	/**
	 * Return the radius of this ship, expressed in kilometers.
	 */
	@Basic @Immutable @Override
	public double getRadius(){
		return this.radius;
	}	
	
	/**
	 * Check whether the given radius is a valid radius.
	 * 
	 * @param radius
	 * 			The radius that has to be verified.
	 * @return	True if the given radius is higher than the minimum radius given
	 * 			| result == (radius >= getMinimumRadius())
	 * @return	False if the given radius is NaN, infinite or smaller than the minimum radius.
	 * 			| result == !(isInfinite(radius) || radius.isNaN() || radius < getMinimumRadius())
	 */
	@Basic
	public boolean isValidRadius(double radius){
		if (Double.isNaN(radius))
			return false;
		else if (Double.isInfinite(radius))
			return false;
		else if (radius >= rMin)
			return true;
		return false;
	}
	
	/**
	 * Variable registering the radius of the spaceship, expressed in kilometers.
	 */
	private final double radius;
	
	/**
	 * Return the minimal radius that a ship can have.
	 * 
	 * @see implementation
	 */
	@Basic @Immutable
	public static double getMinimumRadius(){
		return Ship.rMin;
	}
	
	/**
   	 * Variable registering the minimal radius of a spaceship
   	 */
   	static double rMin = 10;
   	
   	
   	// Mass [TOTAL]
    /**
     * Return the total Mass of this ship.
     * 
     * @return 	The total mass is equal to the mass of the ship plus
     * 		   	the sum in masses of all the loaded bullets.
     * 			| result == getBaseMass()
     * 			|			+ (for each bullet in ship.getBullets():
     * 			|					+ bullet.getMass())
     * 
     */
   	@Basic
    public double getTotalMass(){
    	if (loadedBullets.isEmpty())
    		return getBaseMass();
    	else{
    		double massOfAllBullets = loadedBullets.size()*loadedBullets.iterator().next().getTotalMass();
    		return getBaseMass() + massOfAllBullets;
    	}
    }
    
    /**
     * Sets the base mass of the ship to the given value.
     * 
     * @param mass
     * 			The new base mass of this ship.
     * @post 	If the given base mass is valid, the base mass of this ship will be 
     * 			set to the given mass.
     * 			| if (isValidBaseMass(mass)
     * 			|	then new.getBaseMass() = mass
     * @effect 	If the given mass is not valid, the minimal mass of a ship will be used.
     * 			| if (!isValidBaseMass(mass)
     * 			| 	then new.getBaseMass() = getMinimalMass()
     */
   	@Basic @Raw
    public void setBaseMass(double mass){
    	if (isValidBaseMass(mass))
    		this.mass = mass;
    	else
    		this.mass = getMinimalMass();
    }
    
    /**
     * Returns the minimal base mass of this ship.
     * 
     * @return	The minimal base mass of this ship, which
     * 			is equal to the volume of this ship (a sphere)
     * 			times the density of this ship.
     * 			| result == (4/3)*PI*getDensity()*getRadius()^3
     */
   	@Basic
    public double getMinimalMass(){
    	return (4/3)*Math.PI*Math.pow(getRadius(), 3.0)*getDensity();
    }
    
    /**
     * Returns the base mass of this ship, this is the mass of this ship
     * without any bullets.
     */
   	@Basic
    public double getBaseMass(){
    	return this.mass;
    }
    
    /**
     * Check whether a given value is a valid base mass for this ship.
     * 
     * @param	mass
     * 			The mass that has to be verified.
     * @return 	True if and only if the given mass is larger than the
     * 		   	minimal base mass, false otherwise.
     * 			| result == (mass >= getMinimalMass())
     */
   	@Basic
    public boolean isValidBaseMass(double mass){
    	return mass >= getMinimalMass();
    }
    
    /**
     * Variable registering the base mass of this ship
     * this is the mass of this ship without the bullets.
     */
    private double mass;
    
    /**
     * Get the density of this ship.
     */
    @Basic
    public double getDensity(){
    	return this.density;
    }
    	
    
    /**
     * Variable registering the density of this ship.
     */
    private final double density = 1.42 * Math.pow(10,12);	
    	
   	
  	//-----------ASSOCIATIONS-------------
   	//--------Bullets-------- [DEFENSIVE] except for method fire()
    /**
     * Fire the last bullet that was loaded on the ship. 
     * 
     * @post 	The ship will have one less bullet loaded
     * 			| new.getNbLoadedBullets() = getNbLoadeBullets - 1
     * let 
     * 	bullet be the bullet that is fired when this method is used
     * in 
     * @post	The bullet will be given velocity in the direction that the ship is facing.
     * 			| bullet.getVelcoity.getOrientationAngle() == this.getAngle()
     * @post 	The bullet will be placed next to the ship, in extension of the ship's orientation
     * 			| overlapSignificantly(bullet) == false
     * @post			If the bullet overlaps with another entity it and the other entity will die
     * 			| if for one entity in bullet.getWorld() bullet.overlap(entity) == true
     * 			| then bullet.die() and entity.Die()
     * @post	If the bullet isn't contained by the world then the bullet will be terminated
     * 			| if (!world.contains(bullet)
     * 			| then bullet.die  
     */
    public void fireBullet(){
    	if (loadedBullets.size() == 0 || getWorld() == null)
    		return;
    	
    	Bullet bullet = loadedBullets.remove(loadedBullets.size()-1);
    	bullet.setShip(null);
    	bullet.setSourceShip(this);
    		
    	// Update the bullets's velocity
    	bullet.setVelocity(getFireSpeed()*Math.cos(getAngle()),
    					   getFireSpeed()*Math.sin(getAngle()));
    	// Place the bullet right next to the ship
    	double totalRadius = getRadius() + bullet.getRadius();
    	Vector curPos = getPosition();
    	bullet.setPosition(curPos.getX()+totalRadius*Math.cos(getAngle()),
    					   curPos.getY()+totalRadius*Math.sin(getAngle()));
    	// Check whether the bullet collides with something
    	if (!getWorld().contains(bullet))
    		bullet.die();
    	else {
    		Set<Entity> worldEntities = getWorld().queryEntities();
    		for (Entity entity: worldEntities){
    			if (entity != this && bullet.overlapSignificantly(entity)) {
    				bullet.die();
    				entity.die();
    				return;
    			}
    		} // end for
    		getWorld().addEntity(bullet);
    	} 
    }
    
    /**
     * Check whether the given bullet can be loaded onto this ship.
     * 
     * @param bullet
     * 		The bullet that has to be verified.
     * @return	False if and only if the bullet is null, the bullet is already loaded into the ship,
     * 			if the bullet is already loaded on another ship or if the radius of the bullet is 
     * 			bigger than the radius of the ship, true otherwise.
     * 			|  @see implementation
     */		
    @Basic
   	public boolean canHaveAsBullet(Bullet bullet){
   		return !(bullet == null || loadedBullets.contains(bullet) || bullet.getShip() != null ||
   				 bullet.getRadius() >= getRadius());	
   	}
   	
   	/**
   	 * Load the given bullets into the ship.
   	 * 
   	 * @param bullets
   	 * 			The bullet(s) that have to be loaded on this ship.
   	 * @Post 	The given bullet(s) will be loaded into the ship if possible.
   	 * 			| for (all bullets in bullets):
   	 * 			|		new.bullet.getShip() == this
   	 * @Post 	The given bullet(s) have the same centers and velocities as their ship.
   	 * 			| for (all bullets in bullets): 
   	 * 			|		bullet.get(velocity) == this.getVelocity()
   	 * 			| for (all bullets in bullets): 
   	 * 			|		bullet.getPosition() == this.getPosition()
   	 * @throws NullPointerException
   	 * 			If any of the given bullets are Null.
   	 * 			| if for one bullet in bullets: bullet == null
   	 * @throws IllegalArgumentException
   	 * 			If any of the given bullets can't be loaded into the ship.
   	 * 			| for (a bullet in bullets): bullet.canHaveAsShip() == false
   	 * @throws	IllegalStateException
   	 * 			If the bullet is terminated.
   	 * 			| bullet.isDead()
   	 */
   	public void loadBullets(Bullet...bullets)throws NullPointerException,IllegalArgumentException{
   		for(Bullet bullet:bullets){
   			if(bullet == null)
   				throw new NullPointerException();
   			if (bullet.isDead())
   				throw new IllegalStateException("can't add dead bullet to Ship");
   	   		if (bullet.isDead())
   	   			throw new IllegalStateException("bullet that is dead can't be added to a Ship");
   			if (!(loadedBullets.contains(bullet)))
   				throw new IllegalArgumentException("the given bullet is not loaded in the Ship");
   			if (!bullet.canHaveAsShip(this))
   				throw new IllegalArgumentException();
   			else {
   				if (bullet.getWorld() != null) {
   					// Remove the bullet from its current world
   					bullet.getWorld().removeEntity(bullet);
   				}
   				// Update the bullets's position so that it's center coincides with the ship's center
   				bullet.setPosition(getPosition().getX(), getPosition().getY());
   				bullet.setSourceShip(null);
   				bullet.setShip(this);
   				loadedBullets.add(bullet);
   			}
   		}
   	}
   	
	
   	/**
   	 * Return the bullets loaded on this ship.
   	 * 
   	 * @returns A HashSet containing all bullets loaded on this ship.
   	 */
   	@Basic
   	public Set<Bullet> getBullets(){
   		return new HashSet<Bullet>(loadedBullets);
   	}
   	
   	/**
   	 * Remove a bullet from this ship.
   	 * 
   	 * @param bullet
   	 * 			The bullet that has to be removed.
   	 * @post	The bullet is removed from this ship.
   	 * 			| new.getBullets().contains(bullet) == false
   	 * 			| new.bullet.getShip() == null
   	 * @throws IllegalArgumentException
   	 * 			If the bullet is not present in the ship.
   	 * 			| !getBullets().contains(bullet)
   	 * @throws NullPointerException
   	 * 			When the bullet is null.
   	 * 			| bullet == null
   	 * @throws	NullPointerException
   	 * 			If the bullet is terminated.
   	 * 			| bullet.isDead()
   	 */
   	public void removeBullet(Bullet bullet)throws IllegalArgumentException, NullPointerException {
		if (bullet == null || bullet.isDead())
			throw new NullPointerException("bullet that is terminated can't be in loaded in a ship");
		if (!(loadedBullets.contains(bullet)))
			throw new IllegalArgumentException("the given bullet is not loaded in the Ship");
		this.loadedBullets.remove(bullet);
		bullet.setShip(null);
	}	
   	
   	/**
   	 * A set registering all the bullet that are loaded in this ship.
   	 */
   	private List<Bullet> loadedBullets = new ArrayList<Bullet>();
	
   	/**
   	 * Get the initial speed of a bullet when fired from this ship.
   	 * 
   	 * @see implementation
   	 */
   	@Basic
   	public double getFireSpeed(){
   		return this.fireSpeed;
   	}
   	
   	/**
   	 * Get the number of bullets in a ship.
   	 * 
   	 * @see implementation
   	 */
   	@Basic
   	public double getNbBulletsLoaded(){
   		return loadedBullets.size();
   	}
   	
   	/**
   	 * A variable registering the initial speed of a bullet when fired from this ship.
   	 */
   	private double fireSpeed = 250;
   	
   	
	/**
	 * Variable registering the Speed of light.
	 */
   	static double c = 300000.0;


	 
}

