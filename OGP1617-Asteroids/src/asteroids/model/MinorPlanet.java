package asteroids.model;

public abstract class MinorPlanet extends Entity {
	
	/**
	 * Most extended constructor of MinorPlanet class. initializes a minor planet with the given arguments
	 * @param positionX
	 * @param positionY
	 * @param velocityX
	 * @param velocityY
	 * @param radius
	 * @param speedLimit
	 * @effect
	 * 		|super(positionX,positionY,velocityX,velocityY,speedLimit)
	 * @post
	 * 		|if isValidRadius(radius)
	 * 		| then new.getRadius = radius
	 * @throws IllegalArgumentException
	 *      | !isValidRadius
	 */
	public MinorPlanet(double positionX,double positionY,double velocityX, double velocityY,double radius, double speedLimit)throws IllegalArgumentException{
		super(positionX,positionY,velocityX,velocityY,speedLimit);
		if (!isValidRadius(radius))
			throw new IllegalArgumentException("the given radius is not valid for this minor planet");
		this.radius = radius;
	}
	
	/**
	 * default constructor of the MinorPlanet class. initializes this minorplanet with default settings
	 * @effect
	 * 			| this(0,0,0,0, getMinimalRadius(),c)
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
	 * gets the radius of this minorplanet
	 */
	@Override
	public double getRadius(){
		return this.radius;
	}

	/**
	 * get the total mass of this minorplanet
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
	 * get the minimalRadius of this minorplanet
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
