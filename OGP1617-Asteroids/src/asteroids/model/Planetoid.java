package asteroids.model;

public class Planetoid extends MinorPlanet {
	
	public Planetoid(double positionX,double positionY,double velocityX,double velocityY,double radius, double speedLimit){
		super(positionX,positionY,velocityX,velocityY,radius,speedLimit);
	}
	
	@Override
	public double getTotalMass() {
		// TODO Auto-generated method stub
		return 0;
	} 
}
