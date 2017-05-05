package asteroids.model;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;

public class Asteroid extends MinorPlanet {
	/**
	 * most extended constructor of asteroid class.
	 * @param positionX
	 * @param positionY
	 * @param velocityX
	 * @param velocityY
	 * @param radius
	 * @param speedLimit
	 * @effect
	 * 		|super(positionX,positionY,velocityX,velocityY,radius,speedLimit)
	 */
	public Asteroid(double positionX,double positionY,double velocityX,double velocityY,double radius, double speedLimit){
		super(positionX,positionY,velocityX,velocityY,radius,speedLimit);
	}
	
	/**
	 * constructor with speed limit set to its default limit being the speed of light
	 * @param positionX
	 * @param positionY
	 * @param velocityX
	 * @param velocityY
	 * @param radius
	 * @effect
	 * 		|this(positionX,positionY,velocityX,velocityY,radius,c)
	 */
	public Asteroid(double positionX,double positionY,double velocityX,double velocityY,double radius){
		this(positionX,positionY,velocityX,velocityY,radius,c);
	}
	
	/**
	 * default constructor
	 * @effect
	 * 		| this(0,0,0,0,5,c)
	 */
	public Asteroid(){
		this(0,0,0,0,5,c);
	}
	
	
	// Mass [TOTAL]
	/**
	 * Return the mass of this Asteroid.
	 * 
	 * @return The total mass of this Asteroid, which is equal to the
	 * 		   volume of this Asteroid (a sphere) times the density of this 
	 * 		   Asteroid.
	 * 		   | result == (4/3)*PI*getDensity()*getRadius()^3
	 */
	@Basic
	public double getTotalMass(){
		return (4.0/3)* Math.PI* Math.pow(this.getRadius(), 3.0)*getDensity();
	}
	
	/**
	 * Return the density of the Asteroid.
	 */
	@Basic
	public double getDensity(){
		return Asteroid.density;
	}
	
	/**
	 * Variable registering the density of Asteroid.
	 */
	private static double density = 2.65E12;
	

}
