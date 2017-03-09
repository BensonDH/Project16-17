package asteroids.tests;

import static org.junit.Assert.*;


import org.junit.*;


import org.junit.Test;
import asteroids.model.Ship;
import asteroids.util.ModelException;
public class ShipTests {
	
	Ship glob_ship1;

	
	@Before
	public void SetUp(){
		glob_ship1 = new Ship(-5, 10, -5, 10, 15, 0);
	}
	
	// Normal cases
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
	public void invalidPositionXConstructor(){
		new Ship(Double.NaN,10,10,10,10,Math.PI);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void invalidPositionYConstructor(){
		new Ship(10, Double.NaN, 10, 10, 10, Math.PI);		
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
	
	@Test
	public void maxValueVelocityConstructor(){
		Ship ship1 = new Ship(10, 10, Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY, 10, Math.PI);
		Ship ship2 = new Ship(10, 10, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, 10, Math.PI);
		
		assert ship1.getVelocityX() == -300000;
		assert ship1.getVelocityY() == 0;
		assert ship1.getTotalVelocity() == 300000;
		
		assert ship2.getVelocityX() == 300000;
		assert ship2.getVelocityY() == 0;
		assert ship2.getTotalVelocity() == 300000;
	}
	
	@Test (expected = AssertionError.class)
	public void invalidHighAngleConstructor(){
		new Ship(10, 10, 10, 10, 10, 10);
	}
	
	@Test (expected = AssertionError.class)
	public void invalidLowAngleConstructor(){
		new Ship(10, 10, 10, 10, 10, -10);
	}
	
	// GETTERS AND SETTERS VALID VALUES TESTS
	@Test
	public void testPosition(){
		
		assert glob_ship1.getPositionX() == -5;
		glob_ship1.setPositionX(25);
		assert glob_ship1.getPositionX() == 25;
		
		assert glob_ship1.getPositionY() == 10;
		glob_ship1.setPositionY(-50);
		assert glob_ship1.getPositionY() == -50;
		
		// Back to original state
		glob_ship1.setPositionX(-5);
		glob_ship1.setPositionY(10);
	}

	public void testVelocity(){
		
		assert glob_ship1.getVelocityX() == -5;
		glob_ship1.setVelocityX(30); 
		assert glob_ship1.getVelocityX() == 30;
		
		assert glob_ship1.getVelocityY() == 10;
		glob_ship1.setVelocityY(-50);
		assert glob_ship1.getVelocityY() == -50;
		
		//Back to original state
		glob_ship1.setVelocityX(-5);
		glob_ship1.setVelocityY(10);
	}
	
	@Test
	public void testAngle(){
		
		assert glob_ship1.getAngle() == 0;
		glob_ship1.setAngle(2*Math.PI);
		assert glob_ship1.getAngle() == 2*Math.PI;
		
		// Back to original state
		glob_ship1.setAngle(0);
	}
	
	public void testRadius(){
		
		assert glob_ship1.getRadius() == 15;
		glob_ship1.setRadius(100);
		assert glob_ship1.getRadius() == 100;
		
		// Back to original state
		glob_ship1.setRadius(15);
	}

	// GETTERS AND SETTERS INVALID VALUES TESTS
	@Test (expected = IllegalArgumentException.class)
	public void testPositionXInvalidValue(){
		glob_ship1.setPositionX(Double.NaN);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testPositionYInvalidValue(){
		glob_ship1.setPositionY(Double.NaN);
	}

	@Test
	public void testVelocityInvalidValue(){
		
		glob_ship1.setVelocityX(Double.NaN);
		assert glob_ship1.getVelocityX() == -5;
		
		glob_ship1.setVelocityY(Double.NaN);
		assert glob_ship1.getVelocityY() == 10;

	}

	@Test (expected = AssertionError.class)
	public void testAngleNegativeInvalidValue(){
		glob_ship1.setAngle(-10);
	}
	
	@Test (expected = AssertionError.class)
	public void testAnglePositiveInvalidValue(){
		glob_ship1.setAngle(Double.MAX_VALUE);
	}
	
	@Test (expected = AssertionError.class)
	public void testAngleNaNValue(){
		glob_ship1.setAngle(Double.NaN);
	}

	@Test (expected = IllegalArgumentException.class)
	public void testRadiusLowInvalidValue(){
		glob_ship1.setRadius(-100);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testRadiusInfInvalidValue(){
		glob_ship1.setRadius(Double.POSITIVE_INFINITY);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testRadiusNaNValue(){
		glob_ship1.setRadius(Double.NaN);
	}
	
	@Test
	public void DistanceBetweenTests() {
		
	}

}
