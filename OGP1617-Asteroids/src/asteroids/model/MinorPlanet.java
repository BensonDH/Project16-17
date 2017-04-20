package asteroids.model;

public abstract class MinorPlanet extends Entity {
	
	public MinorPlanet(double positionX,double positionY,double velocityX, double velocityY,double radius, double speedLimit){
		super(positionX,positionY,velocityX,velocityY,speedLimit);
		if (isValidRadius(radius));
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
	 * private variable registering the minimum radius
	 */
	private static double rMin = 5;
	
	/**
	 * private variable registering the speed of light
	 */
	private static double c = 300000000;
}
