package asteroids.tests;

import static org.junit.Assert.assertEquals;

import org.junit.*;
import org.junit.Test;
import asteroids.model.Ship;


public class ShipTests {
	
	private static final double EPSILON = 0.0001;
	
	Ship glob_ship1;
	@Before
	public void SetUp(){
		glob_ship1 = new Ship(-5, 10, -5, 10, 15, 0);
	}
	
	
	// CONSTRUCTOR TESTS
	@Test
	public void constructorNormalCase(){
		Ship ship = new Ship(10, -10, 500, -500, 20, Math.PI);
		
		double[] pos = ship.getPosition();
		assert pos[0] == 10;
		assert pos[1] == -10;
		
		double[] vel = ship.getVelocity();
		assert vel[0] == 500;
		assert vel[1] == -500;
		
		assert ship.getRadius() == 20;
		assert ship.getAngle() == Math.PI;
	}
	
	@Test
	public void defaultConstructor(){
		Ship ship = new Ship();
		
		double[] pos = ship.getPosition();
		assert pos[0] == 0;
		assert pos[1] == 0;
		
		double[] vel = ship.getVelocity();
		assert vel[0] == 0;
		assert vel[1] == 0;
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

		assert ship1.getVelocity()[0] == 0;
	}
	
	@Test
	public void invalidVelocityYConstructor() {
		Ship ship1 = new Ship(10, 10, 10, Double.NaN, 10, Math.PI);
		assert ship1.getVelocity()[1] == 0;
	}
	
	@Test
	public void maxValueVelocityConstructor(){
		Ship ship1 = new Ship(10, 10, Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY, 10, Math.PI);
		
		double[] vel = ship1.getVelocity();
		assert vel[0] == 0;
		assert vel[1] == 0;
		
		ship1.setVelocity(300000, 300000);
		vel = ship1.getVelocity();
		assertEquals(300000*Math.cos(Math.PI/4), vel[0], EPSILON);
		assertEquals(300000*Math.sin(Math.PI/4), vel[1], EPSILON);
		assert ship1.getTotalVelocity() == 300000;
		
		ship1.setVelocity(-300000, 300000);
		vel = ship1.getVelocity();
		assertEquals(-300000*Math.cos(Math.PI/4), vel[0], EPSILON);
		assertEquals(300000*Math.sin(Math.PI/4), vel[1], EPSILON);
		assert ship1.getTotalVelocity() == 300000;
	}
	
	@Test (expected = AssertionError.class)
	public void invalidHighAngleConstructor(){
		new Ship(10, 10, 10, 10, 10, 10);
	}
	
	@Test (expected = AssertionError.class)
	public void invalidLowAngleConstructor(){
		new Ship(10, 10, 10, 10, 10, -10);
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void invalidRadiusConstructor(){
		new Ship(10, 10, 10, 10, -100, 0);
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void infiniteRadiusConstructor(){
		new Ship(10, 10, 10, 10, Double.POSITIVE_INFINITY, 0);
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void NaNRadiusConstructor(){
		new Ship(10, 10, 10, 10, Double.NaN, 0);
	}

	
	// GETTERS AND SETTERS VALID VALUES TESTS
	@Test
	public void testPosition(){
		double[] position = glob_ship1.getPosition();
		
		assert position[0] == -5;
		assert position[1] == 10;
		glob_ship1.setPosition(25, -50);
		
		position = glob_ship1.getPosition();
		
		assert position[0] == 25;
		assert position[1] == -50;
		
		// Back to original state
		glob_ship1.setPosition(-5, 10);

	}

	public void testVelocity(){
		double[] velocity = glob_ship1.getVelocity();
		
		assert velocity[0] == -5;
		assert velocity[1] == 10;
		glob_ship1.setVelocity(30, -50);
		
		velocity = glob_ship1.getVelocity();
		assert velocity[0] == 30;
		assert velocity[1] == -50;
		
		//Back to original state
		glob_ship1.setVelocity(-5, 10);
	}
	
	@Test
	public void testAngle(){
		
		assert glob_ship1.getAngle() == 0;
		glob_ship1.setAngle(2*Math.PI);
		assert glob_ship1.getAngle() == 2*Math.PI;
		
		// Back to original state
		glob_ship1.setAngle(0);
	}


	// GETTERS AND SETTERS INVALID VALUES TESTS
	@Test (expected = IllegalArgumentException.class)
	public void testPositionXInvalidValue(){
		glob_ship1.setPosition(Double.NaN, 0);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testPositionYInvalidValue(){
		glob_ship1.setPosition(0, Double.NaN);
	}

	@Test
	public void testVelocityInvalidValue(){
		
		glob_ship1.setVelocity(Double.NaN, 0);
		double[] velocity = glob_ship1.getVelocity();
		assert velocity[0] == -5;
		assert velocity[1] == 10;
		
		glob_ship1.setVelocity(0, Double.NaN);
		velocity = glob_ship1.getVelocity();
		assert velocity[0] == -5;
		assert velocity[1] == 10;
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
	
	@Test
	public void DistanceBetweenTests() {	
	}
	
}
