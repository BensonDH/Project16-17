package asteroids.model;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;

public class Planetoid extends MinorPlanet {
	
	/**
	 * TODO Documentation
	 * @param positionX
	 * @param positionY
	 * @param velocityX
	 * @param velocityY
	 * @param radius
	 * @param distanceTravelled
	 * @param speedLimit
	 */
	public Planetoid(double positionX, double positionY, double velocityX, double velocityY,
					 double radius, double distanceTravelled, double speedLimit) {
		super(positionX,positionY,velocityX,velocityY,radius,speedLimit);
		setDistanceTravelled(distanceTravelled);
	}
	
	/**
	 * TODO Documentation
	 * @param positionX
	 * @param positionY
	 * @param velocityX
	 * @param velocityY
	 * @param radius
	 * @param distaneTravelled
	 */
	public Planetoid(double positionX, double positionY, double velocityX, double velocityY, 
					 double radius, double distaneTravelled) {
		this(positionX,positionY,velocityX,velocityY,radius,distaneTravelled,c);
	}
	
	/**
	 * TODO Documentation
	 */
	public Planetoid(){
		this(0,0,0,0,5,0,c);
	}
	
	
	// move [TOTAL]
	/**
	 * TODO Documentation
	 * @param duration
	 * @throws IllegalArgumentException
	 */
	@Override
	public void move(double duration)throws IllegalArgumentException{
		if (Double.isNaN(duration))
			throw new IllegalArgumentException("Duration cannot be NaN");
		Vector current_pos = getPosition();
		Vector current_vel = getVelocity();
		setPosition(current_pos.getX()+current_vel.getX()*duration, current_pos.getY()+current_vel.getY()*duration);
		Vector new_pos = getPosition();
		double distanceTravelled = new_pos.subtract(current_pos).norm();
		setDistanceTravelled(getDistanceTravelled() + distanceTravelled);
		
		// This planetoid dissolves when its radius becomes smaller than the minimal radius possible
		if (getRadius() < MinorPlanet.getMinimalRadius())
			this.die();
	}
	
	
	// Radius [DEFENSIVE]
	/**
	 * TODO Documentation
	 * @return
	 */
	public double getRadius(){
		return super.getRadius() - getDistanceTravelled()*0.000001;
	}

	//DistanceTravelled [we decided to program this in a TOTAL way]
	/**
	 * TODO Documentation
	 * @return
	 */
	public double getDistanceTravelled() {
		return this.distanceTravelled;
	}
	
	/**
	 * TODO Documentation
	 * @param distanceTravelled
	 */
	public void setDistanceTravelled(double distanceTravelled) {
		if (Double.isNaN(distanceTravelled))
			this.distanceTravelled = 0;
		else if (Double.isInfinite(distanceTravelled))
			this.distanceTravelled = Double.MAX_VALUE;
		else
			this.distanceTravelled = distanceTravelled;
	}	
	
	/**
	 * A variable registering the distance that this Planetoid has traveled.
	 */
	private double distanceTravelled;



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
		return Planetoid.density;
	}
	
	/**
	 * Variable registering the density of planetoids.
	 */
	private static double density = 0.917E12;
	
	
}
