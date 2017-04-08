package asteroids.model;

import org.hamcrest.core.IsNull;
import be.kuleuven.cs.som.annotate.*;

public class Bullet extends Entity {
	// TODO: constructor
	public Bullet(double positionX, double positionY, double velocityX, 
   			double velocityY, double radius) throws IllegalArgumentException{
		super(positionX, positionY, velocityX, velocityY, radius);
	}
	
	// Radius [DEFENSIVE]
	
	/**
	 * Get the minimal radius of this bullet.
	 *  @return The minimal radius of this bullet.
	 */
	@Override
	public double getRadiusLimit(){
		return this.rLimit;
	}
	
	/*
	 * Variable registering the minimal radius of this bullet.
	 */
	private double rLimit = 1;
	
	// Mass [TOTAL]
	public double getMass(){
		return (4.0/3)* Math.PI* Math.pow(this.getRadius(), 3.0)* Bullet.density;
	}
	
	/**
	 * Variable registering the density of bullets.
	 */
	private static double density = 7.12*Math.pow(10.0, 12.0);
	
}