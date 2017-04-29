package asteroids.model;

public abstract class MinorPlanet extends Entity {
	
	/**
	 * TODO Documentation
	 * @param positionX
	 * @param positionY
	 * @param velocityX
	 * @param velocityY
	 * @param radius
	 * @param speedLimit
	 * @throws IllegalArgumentException
	 */
	public MinorPlanet(double positionX,double positionY,double velocityX, double velocityY,double radius, double speedLimit)throws IllegalArgumentException{
		super(positionX,positionY,velocityX,velocityY,speedLimit);
		if (!isValidRadius(radius))
			throw new IllegalArgumentException("the given radius is not valid for this minor planet");
		this.radius = radius;
	}
	
	/**
	 * TODO Documentation
	 */
	public MinorPlanet(){
		this(0,0,0,0, getMinimalRadius(),c);
	}
	
	public boolean isValidRadius(double radius){
		if (Double.isNaN(radius)|| Double.isInfinite(radius))
			return false;
		else if ( radius < getMinimalRadius())
			return false;
		return true;
	}
	
	/**
	 * TODO Documentation
	 */
	@Override
	public double getRadius(){
		return this.radius;
	}

	/**
	 * TODO Documentation
	 */
	@Override
	public abstract double getTotalMass();
	
	
	/**
	 * Variable registering the radius of this MinorPlanet
	 */
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
	 * TODO Documentation
	 * @return
	 */
	public static double getMinimalRadius(){
		return MinorPlanet.rMin;
	}
	
	/**
	 * private variable registering the minimum radius
	 */
	private static double rMin = 5;
	
	
	
}
