package asteroids.model;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;

public class Asteroid extends MinorPlanet {
	public Asteroid(double positionX,double positionY,double velocityX,double velocityY,double radius, double speedLimit){
		super(positionX,positionY,velocityX,velocityY,radius,speedLimit);
	}
	
	public Asteroid(double positionX,double positionY,double velocityX,double velocityY,double radius){
		this(positionX,positionY,velocityX,velocityY,radius,c);
	}
	
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
