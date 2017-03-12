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
	public void constructorNormalCase2(){
		Ship ship = new Ship(10, 10, 50, 100, 20, 0, 200000);
		
		double[] pos = ship.getPosition();
		assert pos[0] == 10;
		assert pos[1] == 10;
		
		double[] vel = ship.getVelocity();
		assert vel[0] == 50;
		assert vel[1] == 100;
		
		assert ship.getRadius() == 20;
		assert ship.getAngle() == 0;
		assert ship.getSpeedLimit() == 200000;
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
		assertEquals(Math.sqrt(Math.pow(5, 2.0)+Math.pow(10, 2.0)), glob_ship1.getTotalVelocity(), EPSILON);
		glob_ship1.setVelocity(30, -50);
		
		velocity = glob_ship1.getVelocity();
		assert velocity[0] == 30;
		assert velocity[1] == -50;
		assertEquals(Math.sqrt(Math.pow(30, 2.0)+Math.pow(50, 2.0)), glob_ship1.getTotalVelocity(), EPSILON);
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
	

	//move, thrust, turn, killSpeed
	public void normalCases(){
		Ship ship = new Ship(0, 0, 10, 10, 20, Math.PI/4);
		// move
		ship.move(10);
		
		double[] newPos = ship.getPosition();
		assertEquals(100, newPos[0], EPSILON);
		assertEquals(100, newPos[1], EPSILON);
		
		// Back to original state
		ship.setPosition(0, 0);
		// thrust
		ship.thrust(100);
		double[] newVel = ship.getVelocity();
		assertEquals(50*Math.cos(Math.PI/4), newVel[0], EPSILON);
		assertEquals(50*Math.sin(Math.PI/4), newVel[0], EPSILON);
		// killVelocity
		ship.killVelocity();
		newVel = ship.getVelocity();
		assert newVel[0] == 0;
		assert newVel[1] == 0;
		// turn
		ship.turn(Math.PI);
		assertEquals(5*Math.PI/4, ship.getAngle(), EPSILON);
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void infiniteDuration(){
		glob_ship1.move(Double.POSITIVE_INFINITY);
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void NaNDuration(){
		glob_ship1.move(Double.NaN);
	}
	
	@Test
	public void largeAmount(){
		Ship ship = new Ship();
		ship.thrust(Double.POSITIVE_INFINITY);
		double[] vel = ship.getVelocity();
		assert vel[0] == 0;
		assert vel[1] == 0;
		
		ship.thrust(450000);
		vel = ship.getVelocity();
		assertEquals(300000, vel[0], EPSILON);
		assertEquals(0, vel[1], EPSILON);
	}
	
	@Test (expected=AssertionError.class)
	public void testInvalidTurn(){
		glob_ship1.turn(100);
	}
	
	@Test
	public void testKillVelocity(){
		Ship ship = new Ship(0, 0, 100, 100, 20, 0);
		
		ship.killVelocity();
		double[] vel = ship.getVelocity();
		assert vel[0] == 0;
		assert vel[1] == 0;
	}

	
	// Checkers
	@Test
	public void testIsValidVelocity(){
		assert glob_ship1.isValidVelocity(10, 10);
		assert !glob_ship1.isValidVelocity(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
	}
	
	@Test
	public void testIsValidSpeedLimit(){
		assert glob_ship1.isValidSpeedLimit(150000);
		assert !glob_ship1.isValidSpeedLimit(300001);
		assert !glob_ship1.isValidSpeedLimit(-150000);
	}
	
	@Test
	public void testIsValidAngle(){
		assert glob_ship1.isValidAngle(Math.PI/2);
		assert !glob_ship1.isValidAngle(-10);
		assert !glob_ship1.isValidAngle(100);
	}
	

	// getDistanceBetween, overlap, getTimeToCollision, getCollisionPosition TESTS
	@Test
	public void DistanceBetweenTests() {	
		// create two test ships only used for this experiment
		// case 1 ships are not overlapping
		Ship ship1 = new Ship();
		Ship ship2 = new Ship(40, 0, 0, 0, 10, 0);
		assert ship1.getDistanceBetween(ship2) == 20;
		//case 2 ships are touching
		ship2.setPosition(20, 0);
		assert ship1.getDistanceBetween(ship2) == 0;
		//case 3 ships are overlapping negative value expected
		ship2.setPosition(15, 0);
		assert ship1.getDistanceBetween(ship2) == -5;
		// Case 4 one ship is completely in the other ship
		ship2.setPosition(0.75, 0);
		Ship ship3 = new Ship(0, 0, 0, 0, 30, 0);
		assertEquals(ship2.getDistanceBetween(ship3), -19.25, EPSILON); 
		// case 5  get distance between ship 1 and ship1, should return 0
		assert ship1.getDistanceBetween(ship1) == 0;
	}	
	
	@Test
	public void simpleCase1(){
		// SCENARIO: ship1 is moving right towards ship2 with a velocity of [10, 0].
		//			 ship2 is standing still at [70, 0].
		//			 ship1 and ship 2 have a radius of 10.
		Ship ship1 = new Ship(0, 0, 10, 0, 10, 0);
		Ship ship2 = new Ship(70, 0, 0, 0, 10, 0);
		
		// distanceBetween
		double distanceBetween = ship1.getDistanceBetween(ship2);
		assertEquals(50, distanceBetween, EPSILON);
		
		// overlap
		assert !ship1.overlap(ship2);
		assert !ship2.overlap(ship1);
		assert ship1.overlap(ship1);
		
		// timeToCollision
		double timeToCollision = ship1.getTimeToCollision(ship2);
		assertEquals(5, timeToCollision, EPSILON);
		
		// collisionPosition
		double[] collisionPosition = ship1.getCollisionPosition(ship2);
		assertEquals(60, collisionPosition[0], EPSILON);
		assertEquals(0, collisionPosition[1], EPSILON);
	} 
	
	@Test
	public void simpleCase2(){
		// SCENARIO: ship1 is moving left towards ship2 with a velocity of [-10, 0].
		//			 ship2 is standing still at [0, 0].
		//			 ship1 and ship 2 have a radius of 10.
		Ship ship1 = new Ship(70, 0, -10, 0, 10, 0);
		Ship ship2 = new Ship(0, 0, 0, 0, 10, 0);
		
		// distanceBetween
		double distanceBetween = ship1.getDistanceBetween(ship2);
		assertEquals(50, distanceBetween, EPSILON);
		
		// overlap
		assert !ship1.overlap(ship2);
		assert !ship2.overlap(ship1);
		assert ship1.overlap(ship1);
		
		// timeToCollision
		double timeToCollision = ship1.getTimeToCollision(ship2);
		assertEquals(5, timeToCollision, EPSILON);
		
		// collisionPosition
		double[] collisionPosition = ship1.getCollisionPosition(ship2);
		assertEquals(10, collisionPosition[0], EPSILON);
		assertEquals(0, collisionPosition[1], EPSILON);
	} 

	@Test
	public void simpleCase3(){
		// SCENARIO: ship1 is standing still in the origin	
		// 		     ship2 is moving right towards ship1 from above
		
		Ship ship1 = new Ship(0, 0, 0, 0,10, 0);
		Ship ship2 = new Ship(0, 60, 0, -10, 10, 0);
		
		// distanceBetween
		double distanceBetween = ship1.getDistanceBetween(ship2);
		assertEquals(40, distanceBetween, EPSILON);
		
		distanceBetween = ship2.getDistanceBetween(ship1);
		assertEquals(40, distanceBetween, EPSILON);
		
		// overlap
		assert !ship1.overlap(ship2);
		assert !ship2.overlap(ship1);
		assert ship1.overlap(ship1);
		
		// timeToCollision
		double timeToCollision = ship1.getTimeToCollision(ship2);
		assertEquals(4, timeToCollision, EPSILON);
		
		timeToCollision = ship2.getTimeToCollision(ship1);
		assertEquals(4, timeToCollision, EPSILON);
		
		// collisionPosition
		double[] collisionPosition = ship1.getCollisionPosition(ship2);
		assertEquals(0, collisionPosition[0], EPSILON);
		assertEquals(10, collisionPosition[1], EPSILON);
		
		collisionPosition = ship2.getCollisionPosition(ship1);
		assertEquals(0, collisionPosition[0], EPSILON);
		assertEquals(10, collisionPosition[1], EPSILON);
	}

	@Test
	public void symetricalCase(){
		// SCENARIO: ship1 is moving with velocity [10cos(PI/3), 10sin(PI/3)]
		//			 ship2 is moving with velocity [-10cos(PI/3), 10sin(PI/3)]
		// 			 Both ships are moving on an equilateral triangle.
		Ship ship1 = new Ship(0, 0, 10*Math.cos(Math.PI/3), 10*Math.sin(Math.PI/3), 10, 0);
		Ship ship2 = new Ship(60, 0, -10*Math.cos(Math.PI/3), 10*Math.sin(Math.PI/3), 10, 0);
		
		// distanceBetween
		double distanceBetween = ship1.getDistanceBetween(ship2);
		assertEquals(40, distanceBetween, EPSILON);
		
		// overlap
		assert !ship1.overlap(ship2);
		assert !ship2.overlap(ship1);
		assert ship1.overlap(ship1);
		
		// timeToCollision
		double timeToCollision = ship1.getTimeToCollision(ship2);
		assertEquals(4, timeToCollision, EPSILON);
		
		// collisionPosition
		double[] collisionPosition = ship1.getCollisionPosition(ship2);
		assertEquals(10+40*Math.cos(Math.PI/3), collisionPosition[0], EPSILON);
		assertEquals(40*Math.sin(Math.PI/3), collisionPosition[1], EPSILON);
	}

}