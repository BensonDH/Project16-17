package asteroids.model;

public abstract class MinorPlanet extends Entity {
	
	public MinorPlanet(double positionX,double positionY,double velocityX, double velocityY,double radius, double speedLimit)throws IllegalArgumentException{
		super(positionX,positionY,velocityX,velocityY,speedLimit);
		if (!isValidRadius(radius))
			throw new IllegalArgumentException("the given radius is not valid for this minor planet");
		this.radius = radius;
	}
	
	public MinorPlanet(){
		this(0,0,0,0, rMin,c);
	}
	
	public boolean isValidRadius(double radius){
		if (Double.isNaN(radius)|| Double.isInfinite(radius))
			return false;
		else if ( radius < rMin)
			return false;
		return true;
	}
	
	@Override
	public double getRadius(){
		return this.radius;
	}

	@Override
	public abstract double getTotalMass();
	
	
	
	private double radius;

	
	/**
	 * Let this Minorplanet die by removing it from its world
	 * 
	 * @post
	 * 			| if (getWorld() != null)
	 * 			|  then new.getWorld().isInWorld() == false
	 * 			|  		new.getWorld() == null
	 * @post	This bullet will be terminated.
	 * 			| new.isDead()  	 
	 */
	@Override
	public void die(){
		if (getWorld() != null) {
			getWorld().removeEntity(this);
			removeWorld();
		
		}
		this.isTerminated = true;
	}
	
	/**
	 * private variable registering the minimum radius
	 */
	private static double rMin = 5;
	
	
	
}
