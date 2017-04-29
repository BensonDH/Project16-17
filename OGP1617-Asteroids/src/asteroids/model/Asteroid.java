package asteroids.model;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;

public class Asteroid extends MinorPlanet {
	/**
	 * TODO Documentation
	 * @param positionX
	 * @param positionY
	 * @param velocityX
	 * @param velocityY
	 * @param radius
	 * @param speedLimit
	 */
	public Asteroid(double positionX,double positionY,double velocityX,double velocityY,double radius, double speedLimit){
		super(positionX,positionY,velocityX,velocityY,radius,speedLimit);
	}
	
	/**
	 * TODO Documentation
	 * @param positionX
	 * @param positionY
	 * @param velocityX
	 * @param velocityY
	 * @param radius
	 */
	public Asteroid(double positionX,double positionY,double velocityX,double velocityY,double radius){
		this(positionX,positionY,velocityX,velocityY,radius,c);
	}
	
	/**
	 * TODO Documentation
	 */
	public Asteroid(){
		this(0,0,0,0,5,c);
	}
	
	
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
		return Asteroid.density;
	}
	
	/**
	 * Variable registering the density of bullets.
	 */
	private static double density = 2.65E12;
	

}
