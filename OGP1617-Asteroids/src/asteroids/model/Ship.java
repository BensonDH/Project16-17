package asteroids.model;
import be.kuleuven.cs.som.annotate.*;

/**
 * TODO
 * A class of spaceships. Spaceships are represented as circles with radius r. 
 * @author Benson De Heel, Xander De Jaegere 
 *  
 */
public class Ship {
	/**
	 * Variable registering the constant speed of light.
	 */
   	static double c = 300000.0;
   	
   	/**
   	 * TODO: kan het zijn dat er @posts gebruikt moeten worden?
   	 * 
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
   	 * @effect 	The position of this ship will be set to the given positionX and positionY.
   	 * 			| setPosition(positionX, positionY)
   	 * @effect	The velocity of this ship will be set to the given velocityX and velocityY.
   	 * 			| setVelocity(velocityX, velocityY)
   	 * @effect	The radius of this ship will be set to the given radius.
   	 * 			| setRadius(radius)
   	 * @effect	The orientation angle of this ship will be set to the given angle.
   	 * 			| setAngle(angle)
   	 */
   	public Ship(double positionX, double positionY, double velocityX, 
   			double velocityY, double radius, double angle) {
   		setPosition(positionX, positionY);
   		setVelocity(velocityX, velocityY);
   		setRadius(radius);
   		setAngle(angle);
   	}
	
   	/**
   	 * Initialize this new spaceship with its default values.
   	 * @post all the parameters of the class ship will be set to their default value.
   	 * 			| new.getPosition() = [0, 0] 
   	 * 			| new.getVelocity() = [0, 0]
   	 *			| new.getAngle() = 0 
   	 *			| new.getRadius() = 10
   	 */
   	public Ship(){
   		setPosition(0, 0);
   		setVelocity(0, 0);
   		setAngle(0);
   		setRadius(10);
   	}

   	
   	// Distance DEFENSIVE
	/**
	 * Return the position of the spaceship.
	 * @return
	 * 		The position of the spaceship represented by a list as [x, y]
	 */
	@Basic
	public double[] getPosition() {
		return this.position.clone();
	}

	/**
	 * Set the position of this spaceship to the given x and y value.
	 * @param x
	 * 			The x-component of the position of this spaceship
	 * @param y
	 * 			The y-component of the position of this spaceship
	 * @post 	if x and y are non-infinite numbers the position of this spaceship will
	 * 			be set to [x, y]
	 * 		   | new.position = [x, y]
	 * @throws IllegalArgumentException
	 * 				The given parameter x or y is infinite or equal to NaN
	 * 				| x == INFINITE || y == INFINITE
	 * 				| x == NaN || y == NaN
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
	 * Variable registering the position of this spaceship.
	 */
	private double[] position = {0, 0};
	
	/**
	 * TODO
	 * @param duration
	 */
	public void move(double duration){
		double[] current_pos = getPosition();
		double[] current_vel = getVelocity();
		setPosition(current_pos[0]+current_vel[0]*duration, current_pos[1]+current_vel[1]*duration);
	}
	
	
	// Velocity TOTAL
	/**
	 * Return the velocity of this ship.
	 * @return
	 * 		A list representing the velocity of this spaceship as [velocityX, velocityY]
	 */	
	@Basic
	public double[] getVelocity() {
		return this.velocity.clone();
	}
	
	/**
	 * Set the velocity of this spaceship to the given velocityX and velocityY values.
	 * 
	 * @param velocityX
	 * 			The x-component of the velocity of this spaceship.
	 * @param velocityY
	 * 			The y-component of the velocity of this spaceship.
	 * @post	If the euclidean norm between velocityX and velocityY is smaller than c,
	 * 			the position of this ship will be set to [velocityX, velocityY].
	 * 		   | if (isValidVelocity(velocityX, velocityY)
	 * 		   | 	then new.velocity == [velocityX, velocityY]
	 * @post	If the euclidean norm between velocityX and velocityY is greater than c,
	 * 			the vector [velocityX, velocityY] will be rescaled so that the orientation
	 * 			remains the same and length of the vector is equal to c.
	 * 		   | if (sqrt(velocityX^2+velocityY^2) > c)
	 * 		   | 	then new.velocity == [c*cos(theta), c*sin(theta)]
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
	 * Variable registering the velocity of this spaceship.
	 */
	private double[] velocity = {0, 0};
	
   	/**
   	 * Return length of the velocity vector of this spaceship.
   	 * 
   	 * @return	The total velocity of this spaceship which is the length of its velocity vector.
   	 * 			| sqrt(getVelocityX()^2+getVelocityY()^2)
   	 */
	public double getTotalVelocity(){
		double[] vel = getVelocity();
		return Math.sqrt(Math.pow(vel[0], 2.0)+Math.pow(vel[1], 2.0));
	}
	
	/**
	 * Checks whether the total velocity is smaller than or equal to the speed limit of this spaceship.
	 * 
	 * @param velocityX 
	 * 		  	The x-component of the velocity vector.
	 * @param velocityY
	 * 		  	The y-component of the velocity vector.
	 * @return 	True if and only if the velocity is smaller than or equal to the speed limit of this ship.
	 * 		   | result == sqrt(velocityY^2 + velocityX^2) <= c
	 */
	private boolean isValidVelocity(double velocityX, double velocityY){
		return Math.sqrt(Math.pow(velocityY, 2.0)+Math.pow(velocityX, 2.0))<=c;
	}
	
	/**
	 * Cancels the ship's velocity setting it to 0
	 * @effect velocity will be set to 0
	 * 			| setVelocityX(0, 0)
	 */
	public void killVelocity(){
		setVelocity(0, 0);
	}
	
	/**
	 * TODO
	 * @param amount
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
	 * 							| result == (0<= angle) && (angle<=2*PI)
	 */
	public static boolean isValidAngle(double angle){
		return (0 <= angle) && (angle <= 2*Math.PI);
	}
	
	/**
	 * Turn the spaceship with the given angle.
	 * 	A negative angle will result in an anti-clockwise rotation.
	 *  A positive angle will result in a clockwise rotation.
	 * @param angle
	 * 		  The size of change of rotation the angle.
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
	@Basic
	public double getRadius(){
		return this.radius;
	}
	
	@Basic
	public void setRadius(double radius) throws IllegalArgumentException {
		if (Double.isNaN(radius) || Double.isInfinite(radius))
			throw new IllegalArgumentException("The given value is not valid.");
		else if (radius < 10)
			throw new IllegalArgumentException("The given value must be larger than 10 km.");
		this.radius = radius;
	}
	
	/**
	 * Variable registering the radius of the spaceship, expressed in kilometers.
	 */
	private double radius;
	
	/**
	 * TODO
	 * @param otherShip
	 * @return
	 */
	public double getDistanceBetween(Ship otherShip){
		if (this == otherShip)
			return 0;
		
		double[] thisPos = getPosition();
		double[] otherPos = otherShip.getPosition();
				
		double[] deltaPos = {thisPos[0]- otherPos[1], thisPos[1]- otherPos[1]};
		double distanceBetweenMid = Math.sqrt(Math.pow(deltaPos[0], 2.0)+Math.pow(deltaPos[1], 2.0)); 
		double minRadius = Math.min(getRadius(), otherShip.getRadius());
		
		// The center of a ship is in the others radius.
		if (distanceBetweenMid < minRadius){
			return distanceBetweenMid-minRadius;
		}
			
		return distanceBetweenMid-(getRadius()+otherShip.getRadius());
	}
	
	/**
	 * TODO
	 * @param otherShip
	 * @return
	 */
	public boolean overlap(Ship otherShip){
		return getDistanceBetween(otherShip) < 0;
	}
	
	/**
	 * TODO
	 * @param otherShip
	 * @return
	 */
	public double getTimeToCollision(Ship otherShip){
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
	 * TODO
	 * @param otherShip
	 * @return
	 */
	public double[] getCollisionPosition(Ship otherShip){
		double deltaT = getTimeToCollision(otherShip);
		double[] pos = getPosition();
		double[] vel = getVelocity();
		
		return new double[]{pos[0]+vel[0]*deltaT, pos[1]+vel[1]*deltaT};
	}
}

