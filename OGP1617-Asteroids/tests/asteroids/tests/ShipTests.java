package asteroids.tests;

import static org.junit.Assert.*;


import org.junit.*;


import org.junit.Test;
import asteroids.model.Ship;
import asteroids.util.ModelException;
public class ShipTests {
	
	@Before
	public void SetUp(){
	Ship Shipzq = new Ship();
	Ship Shipzqd = new Ship();
	}
	
	// Normal case
	@Test
	public void constructorNormalCase(){
		Ship ship = new Ship(10, -10, 500, -500, 20, Math.PI);
		
		assert ship.getPositionX() == 10;
		assert ship.getPositionY() == -10;
		assert ship.getVelocityX() == 500;
		assert ship.getVelocityY() == -500;
		assert ship.getRadius() == 20;
		assert ship.getAngle() == Math.PI;
	}
	
	// Default constructor
	@Test
	public void defaultConstructor(){
		Ship ship = new Ship();
		
		assert ship.getPositionX() == 0;
		assert ship.getPositionY() == 0;
		assert ship.getVelocityX() == 0;
		assert ship.getVelocityY() == 0;
		assert ship.getRadius() == 10;
		assert ship.getAngle() == 0;
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void invalidPositionXConstructor()throws IllegalArgumentException{
		Ship ship1 = new Ship(Double.NaN,10,10,10,10,Math.PI);
	}
	@Test(expected = IllegalArgumentException.class)
	public void invalidPositionYConstructor()throws IllegalArgumentException{
		Ship ship1 = new Ship(10, Double.NaN, 10, 10, 10, Math.PI);		
	}
	
	@Test
	public void invalidVelocityXConstructor() {
		Ship ship1 = new Ship(10, 10, Double.NaN, 10, 10, Math.PI);
		assert ship1.getVelocityX() == 0;
	}
	
	@Test
	public void invalidVelocityYConstructor() {
		Ship ship1 = new Ship(10, 10, 10, Double.NaN, 10, Math.PI);
		assert ship1.getVelocityY() == 0;
	}
	// POSITION
	
	@Test
	public void DistanceBetweenTests() {
		
	}

}
