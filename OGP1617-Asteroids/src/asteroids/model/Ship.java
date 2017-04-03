package asteroids.model;
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
   	 * @param speedLimit
   	 * 			The maximum velocity of the spaceship.
   	 * 
   	 * @pre		the angle must be between 0 and 2*PI
   	 * 			The orientation angle of the spaceship.
   	 * @post 	The position of this ship will be set to the given positionX and positionY.
   	 * 			| new.getPosition() == (positionX, positionY)
   	 * @post 	the speedLimit will be set to the given speedLimit
   	 * 			if the speedLimit is not valid the speedLimit will be set to the speed of light
   	 * 			| if (isValidSpeedLimit(speedLimit)
   	 * 			|	then new.getSpeedLimit() == speedLimit
   	 * 			| else
   	 * 			| 	then new.getSpeedlimit() == c 
   	 * @effect	The velocity of this ship will be set to the given velocityX and velocityY
   	 * 			if the total velocity is smaller than the Speed limit of this ship. if it's greater than the speed limit
   	 * 			it will be set to the speed limit
   	 * 			| setVelocity(velocityX,velocityY)
   	 * @post	The radius of this ship will be set to the given radius 
   	 * 			if the given radius is greater than the minimum radius.
   	 * 			| if (isValidRadius(radius))
   	 * 			| 	then new.getRadius() == radius
   	 * @post	the angle will be set to the given angle
   	 * 			| new.getAngle() == angle
   	 * @throws 	IllegalArgumentEception
   	 * 			the given position is infinite or has Double.Nan as a value 
   	 * 			| isInfinite(positionX) || isNaN(positionX)
   	 * 			| isInfinite(positionY) || isNan(positionY)
   	 * @throws	IllegalArgumentException
   	 * 			The given radius is infinite or smaller than the minimal radius of this spaceship.
   	 * 			| radius == INFINITE OR radius < minimalRadius
   	 */
   	public Ship(double positionX, double positionY, double velocityX, 
   			double velocityY, double radius, double angle, double speedLimit) throws IllegalArgumentException {
   		super(positionX, positionY, velocityX, velocityY, radius, speedLimit);
   		setAngle(angle);
   	}
	
   	/**
   	 * initialize this Ship with the given parameters and the speed limit on its maximum value
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
   	 * @effect  this Ship is initialized with it's given parameters
   	 * 			 and with the Speed limit set to the speed of light
   	 * 			| this(positionX,positionY,velocityX,velocityY,radius,angle,c)
   	 */
   	public Ship(double positionX, double positionY, double velocityX, 
   			double velocityY, double radius, double angle)throws IllegalArgumentException{
   		super(positionX,positionY,velocityX,velocityY,radius);
   		setAngle(angle);
   	}
   	
   	
   	/**
   	 * Initialize this new spaceship with its default values, 
   	 * the default for the speed limit being the speed of light.
   	 * @effect 	The spaceship will be set in the origin.
   	 * 			| this(0,0,0,0,10,0,c)
   	*/
   	public Ship(){
   		super();
   		setAngle(0);
   	}

   	
   	// Position DEFENSIVE
	/**
	 * Change the position of this spaceship based on the current position, velocity 
	 * and given duration.
	 * @param duration
	 * 			The considered duration of the movement.
	 * @post	The position of this spaceship will be set to the position it has after the
	 * 			considered amount of time.
	 * 			| setPosition(getXPosition()+duration*getXVelocity(), 
	 * 						  getYPosition()+duration*getYVelocity())
	 * @throws	IllegalArgumentException
	 * 			The given duration is equal to NaN.
	 * 			| duration == Double.NaN
	 */
	public void move(double duration) throws IllegalArgumentException{
		if (Double.isNaN(duration))
			throw new IllegalArgumentException("Duration cannot be NaN");
		double[] current_pos = getPosition();
		double[] current_vel = getVelocity();
		setPosition(current_pos[0]+current_vel[0]*duration, current_pos[1]+current_vel[1]*duration);
	}
	

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
	 * Change the ship's velocity based on the current velocity, orientation and given amount.
	 * @param amount
	 * 			The length of the vector which will be incremented to the ship's velocity vector.
	 * 			This vector will be parallel to the ship's velocity vector.
	 * @effect	
	 * 			| new.getVelocity() == setVelocity(this.getVelocity()[0]+amount*cos(getAngle()),
	 * 			|					    this.getVelocity()[1]+amount*sin(getAngle()))
	 */
	public void thrust(double amount){
		if (amount < 0){
			return;
		}
		double[] velocity = getVelocity();
		velocity[0] = velocity[0] +amount*Math.cos(getAngle());
		velocity[1] = velocity[1] +amount*Math.sin(getAngle());

		setVelocity(velocity[0], velocity[1]);
	}
	
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

   	
   	// Mass [TOTAL]
   	// TODO: dit uitwerken
   	public double getMass(){
   		return 1.0;
   	}
   	
	// otherShip related methods
   	
   	
	/**
	 * Calculate the distance in between this ship and a given other ship.
	 * @param otherShip
	 * 			The other ship involved in the calculation of the distance in between
	 * 			both ships.
	 * @return	The distance between this ship and otherShip. The distance may be
	 * 			negative if both ships overlap. if the this == otherShip the distance will be zero
	 * 			| if(this== otherShip)
	 * 			| 	then this.getDistanceBetween(otherShip) == 0
	 * 			| if(this.overlap(otherShip))
	 * 			|	then this.getDistanceBetween(otherShip) < 0
	 * 			| else
	 * 			| 	this.getDistanceBetween(otherShip) > 0
	 * 
	 * @throws	NullPointerException
	 * 			The given otherShip is null.
	 * 			| otherShip == null
	 */
	public double getDistanceBetween(Ship otherShip)throws NullPointerException{
		if (otherShip == null)
			throw new NullPointerException();
		if (this == otherShip)
			return 0;
		
		double[] thisPos = getPosition();
		double[] otherPos = otherShip.getPosition();
				
		double[] deltaPos = {thisPos[0]- otherPos[0], thisPos[1]- otherPos[1]};
		double distanceBetweenMid = Math.sqrt(Math.pow(deltaPos[0], 2.0)+Math.pow(deltaPos[1], 2.0)); 
		double minRadius = Math.min(getRadius(), otherShip.getRadius());
		double maxRadius = Math.max(getRadius(), otherShip.getRadius());
		// The center of a ship is in the others radius.
		if ((distanceBetweenMid < minRadius) && (2*minRadius <= maxRadius)){
			return -(maxRadius -distanceBetweenMid-minRadius);
		}
			
		return distanceBetweenMid-(getRadius()+otherShip.getRadius());
	}
	
	/**
	 * Checks whether two ships overlap.
	 * @param otherShip
	 * 			The other ship involved in the calculations.
	 * @return	True if and only if this ship and otherShip overlap.
	 * 			| this.getDistanceBetween(otherShip) <= 0
	 */
	public boolean overlap(Ship otherShip) throws NullPointerException{
		if (otherShip == null)
			throw new NullPointerException("otherShip is null");
		return getDistanceBetween(otherShip) <= 0;
	}
	
	/**
	 * @param otherShip
	 * 			The other ship that collides with this ship.
	 * @return The resulting double will be the time in seconds that it takes for the two ships to collide.
	 * 			| let
	 * 			| 	new = this.move(result)
	 * 			|   newOtherShip = otherShip.move(result)�
	 * 			| in
	 * 			|	new.getDistanceBetween(newOtherShip) == 0
	 */
	public double getTimeToCollision(Ship otherShip) throws NullPointerException,IllegalArgumentException{
		if (otherShip == null)
			throw new NullPointerException("otherShip is null");
		if(overlap(otherShip))
			throw new IllegalArgumentException("The ships overlap");
			
		double totalRadius = getRadius()+otherShip.getRadius();
		
		double[] thisPos = getPosition();
		double[] otherPos = otherShip.getPosition();
		double[] deltaPos = {thisPos[0]-otherPos[0], thisPos[1]-otherPos[1]};
		
		double[] thisVel = getVelocity();
		double[] otherVel = otherShip.getVelocity();
		double[] deltaVel = {thisVel[0]-otherVel[0], thisVel[1]-otherVel[1]};
		
		double VTimesR = deltaVel[0]*deltaPos[0]+deltaVel[1]*deltaPos[1];
		double Vquad = Math.pow(deltaVel[0], 2.0)+Math.pow(deltaVel[1], 2.0); 
		double Rquad = Math.pow(deltaPos[0], 2.0)+Math.pow(deltaPos[1], 2.0);
		
		double d = Math.pow(VTimesR, 2.0)- Vquad*(Rquad-Math.pow(totalRadius, 2.0));
		
		if (VTimesR >= 0 || d <= 0)
			return Double.POSITIVE_INFINITY;
		
		return -(VTimesR+Math.sqrt(d))/Vquad;
	}
	
	/** 
	 * Return the position of this ship when this ship and the given other ship collide.
	 * 	This method returns null if this ship and the given other ship never collide.
	 * @param otherShip
	 * 			The other ship that collides with this ship.
	 * @return 	An array with the x- and y-components of the position where this ship and the given otherShip collide.
	 * @throws	NullPointerException
	 * 			The given otherShip is null
	 * 			| otherShip == null
	 * @throws IllegalArgumentException
	 * 			the ship overlaps with the other ship
	 * 			| this.overlap(otherShip)
	 */
	public double[] getCollisionPosition(Ship otherShip) throws NullPointerException,IllegalArgumentException{
		if (otherShip == null)
			throw new NullPointerException("otherShip is null");
		if (overlap(otherShip))
			throw new IllegalArgumentException("ship overlaps with othership");
		
		double deltaT = getTimeToCollision(otherShip);
		
		if (Double.isInfinite(deltaT))
			return null;
		
		double[] pos = getPosition();
		double[] vel = getVelocity();
		double[] pos1 = otherShip.getPosition();
		double[] vel1 = otherShip.getVelocity();
		
		double theta = Math.acos(( (pos1[0]+vel1[0]*deltaT) - (pos[0]+vel[0]*deltaT))/(getRadius()+otherShip.getRadius()));
		if (pos[1] +vel[1]*deltaT < pos1[1] + vel1[1]*deltaT)
			return new double[]{pos[0]+vel[0]*deltaT+getRadius()*Math.cos(theta), pos[1]+vel[1]*deltaT+getRadius()*Math.sin(theta)};
		else
			return new double[]{pos[0]+vel[0]*deltaT+ getRadius()*Math.cos(-theta), pos[1]+vel[1]*deltaT+getRadius()*Math.sin(-theta)};
	}
	
	
	/**
	 * Variable registering the Speed of light.
	 */
   	static double c = 300000.0;
   	
   
}

