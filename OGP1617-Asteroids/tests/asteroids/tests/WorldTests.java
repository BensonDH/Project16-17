package asteroids.tests;

import static org.junit.Assert.*;

import java.util.Set;
import org.junit.*;

import asteroids.model.World;
import asteroids.model.Bullet;
import asteroids.model.Entity;
import asteroids.model.Ship;


public class WorldTests {
	private static final double EPSILON = 0.0001;
	
	
	World globWorld;
	@Before
	public void SetUp(){
		globWorld = new World(100, 100);
	}
	
	// -*-*- Constructor tests (+ Basic getters and setters) -*-*-
	@Test
	public void validConstructorTest(){
		World world = new World(50, 70);
		
		assertEquals(50, world.getWidth(), EPSILON);
		assertEquals(70, world.getHeight(), EPSILON);
	}
	
	@Test
	public void invalidConstructorTest(){
		World testWorld = new World(Double.POSITIVE_INFINITY, -20);
		
		assertEquals(World.getDefaultWidth(), testWorld.getWidth(), EPSILON);
		assertEquals(World.getDefaultHeight(), testWorld.getHeight(), EPSILON);
	}
	
	@Test
	public void defaultconstructorTest(){
		World testWorld = new World();
		
		assertEquals(World.getDefaultWidth(),testWorld.getWidth(), EPSILON);
		assertEquals(World.getDefaultHeight(), testWorld.getHeight(), EPSILON);
	}
	
	// -*-*- addEntity, queryEntities, removeEntity tests -*-*-
	// Valid addEntity, queryEntities, removeEntity cases
	@Test
	public void addAndRemoveEntityValidCases(){
		World testWorld = new World(100, 100);
		// Adding a ship
		Ship testShip = new Ship(50, 50, 0, 0, 10, 0);
		
		testWorld.addEntity(testShip);
		
		Set<Entity> entities = testWorld.queryEntities();
		World linkedWorld = testShip.getWorld();
		
		assertTrue(entities.contains(testShip));
		assertTrue(entities.size() == 1);
		assertTrue(linkedWorld == testWorld);
		
		// Adding a Bullet
		Bullet testBullet = new Bullet(25, 25, 0, 0, 10);
		
		testWorld.addEntity(testBullet);
		
		entities = testWorld.queryEntities();
		linkedWorld = testBullet.getWorld();
		
		assertTrue(entities.contains(testBullet));
		assertTrue(entities.size() == 2);
		assertTrue(linkedWorld == testWorld);
		
		// Removing a Ship
		testWorld.removeEntity(testShip);
		
		entities = testWorld.queryEntities();
		linkedWorld = testShip.getWorld();
		
		assertFalse(entities.contains(testShip));
		assertTrue(entities.size() == 1);
		assertTrue(linkedWorld == null);
		
		// Removing a bullet
		testWorld.removeEntity(testBullet);
		
		entities = testWorld.queryEntities();
		linkedWorld = testBullet.getWorld();
		
		assertFalse(entities.contains(testBullet));
		assertTrue(entities.size() == 0);
		assertTrue(linkedWorld == null);
	}
	
	// Invalid addEntity cases
	@Test (expected=NullPointerException.class)
	public void addEntityWithNull() {
		globWorld.addEntity(null);
	}

	@Test (expected=IllegalArgumentException.class)
	public void addEntityThatAlreadyHasAWorld(){
		World testWorld = new World(150, 150);
		Entity testEntity = new Bullet(25, 25, 0, 0, 10);
		testWorld.addEntity(testEntity);
		
		World testWorld2 = new World(150, 150);
		
		testWorld2.addEntity(testEntity);
	}

	@Test (expected=IllegalArgumentException.class)
	public void addEntityThatAlreadyLiesInThisWorld(){
		World testWorld = new World(150, 150);
		Entity testEntity = new Ship(25, 25, 0, 0, 10, 0);
		testWorld.addEntity(testEntity);
		
		testWorld.addEntity(testEntity);
	}

	@Test (expected=IllegalArgumentException.class)
	public void addEntityThatLiesOutsideBoundaries(){
		World testWorld = new World(150, 150);
		Entity testEntity = new Bullet(-100, 100, 0, 0, 10);
		
		testWorld.addEntity(testEntity);
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void addEntityThatOverlaps(){
		World testWorld = new World(150, 150);
		Entity testEntity = new Ship(25, 25, 0, 0, 10, 0);
		testWorld.addEntity(testEntity);
		
		Entity testEntity2 = new Ship(20, 20, 0, 0, 10, 0);
		
		testWorld.addEntity(testEntity2);
	}

	// Invalid removeEntity case
	@Test (expected=NullPointerException.class)
	public void RemoveNullEntity(){
		globWorld.removeEntity(null);
	}

	@Test (expected=IllegalArgumentException.class)
	public void RemoveEntityThatLiesInAnotherWorld(){
		World testWorld1 = new World(150, 150);
		Entity testEntity = new Bullet(50, 50, 0, 0, 10);
		testWorld1.addEntity(testEntity);
		
		World testWorld2 = new World(150, 150);
		
		testWorld2.removeEntity(testEntity);
	}

	// -*-*- queryShips, queryBullets, queryEntities tests -*-*-
	@Test
	public void queryShipsAndBullets(){
		World testWorld = new World(150,150);
		// Add 3 Bullets and 3 Ships to the testWorld in a random order
		Entity testEntity1 = new Bullet(10, 10, 0, 0, 10);
		testWorld.addEntity(testEntity1);
		Entity testEntity2 = new Ship(20, 10, 0, 0, 10, 0);
		testWorld.addEntity(testEntity2);
		Entity testEntity3 = new Ship(30, 10, 0, 0, 10, 0);
		testWorld.addEntity(testEntity3);
		Entity testEntity4 = new Ship(40, 10, 0, 0, 10, 0);
		testWorld.addEntity(testEntity4);
		Entity testEntity5 = new Bullet(50, 10, 0, 0, 10);
		testWorld.addEntity(testEntity5);
		Entity testEntity6 = new Bullet(60, 10, 0, 0, 10);
		testWorld.addEntity(testEntity6);
		
		// queryEntities
		Set<Entity> entities = testWorld.queryEntities();
		
		assertTrue(entities.size() == 6);
		assertTrue(entities.contains(testEntity1));
		assertTrue(entities.contains(testEntity2));
		assertTrue(entities.contains(testEntity3));
		assertTrue(entities.contains(testEntity4));
		assertTrue(entities.contains(testEntity5));
		assertTrue(entities.contains(testEntity6));
		
		// queryBullets
		Set<Entity> bullets = testWorld.queryBullets();
		
		assertTrue(bullets.size() == 3);
		assertTrue(bullets.contains(testEntity1));
		assertTrue(bullets.contains(testEntity5));
		assertTrue(bullets.contains(testEntity6));
		
		// queryShips
		Set<Entity> ships = testWorld.queryShips();
		
		assertTrue(ships.size() == 3);
		assertTrue(bullets.contains(testEntity2));
		assertTrue(bullets.contains(testEntity3));
		assertTrue(bullets.contains(testEntity4));
	}

	// -*-*- getEntityAtPosition tests -*-*-
	@Test
	public void getEntityAtPositionTests(){
		World testWorld = new World(150, 150);
		Entity testEntity = new Ship(50, 50, 0, 0, 10, 0);
		testWorld.addEntity(testEntity);
		
		double[] position = {50, 50};
		Entity resultEntity = testWorld.getEntityAtPosition(position);
		assertTrue(resultEntity == testEntity);
		
		position[0] = 100;
		resultEntity = testWorld.getEntityAtPosition(position);
		assertTrue(resultEntity == null);
		
	}

	// -*-*- contains tests -*-*-
	@Test
	public void containsTest(){
		World testWorld = new World(150, 150);
		
		// Entity that lies between the boundaries
		Entity testEntity1 = new Ship(50, 50, 0, 0, 10, 0);
		assertTrue(testWorld.contains(testEntity1));
		
		// Entity that lies right against a boundary
		Entity testEntity2 = new Ship(10, 10, 0, 0, 10, 0);
		assertTrue(testWorld.contains(testEntity2));
		
		// Entity that lies outside the boundaries
		Entity testEntity3 = new Ship(250, 250, 0, 0, 10, 0);
		assertFalse(testWorld.contains(testEntity3));
		
		// Entity that lies just outside the boundaries
		Entity testEntity4 = new Ship(9, 9, 0, 0, 10, 0);
		assertFalse(testWorld.contains(testEntity4));
	}

	// -*-*- getTimeToCollisionWithBoundaries + getPositionToCollisionWithBoundaries tests -*-*-
	@Test
	public void getTimeAndPositionToCollisionWithBoundariesTests(){
		World testWorld = new World(150, 50);
		// Test 1: Collision in Upper-Right Quadrant
		Entity ship1 = new Ship(30, 15, 15, 20, 10, 0);
		
		assertEquals(1.25, testWorld.getTimeToCollisionWithBoundaries(ship1), EPSILON);
		
		double[] resultPosition = testWorld.getPositionToCollisionWithBoundaries(ship1);
		assertEquals(48.75, resultPosition[0], EPSILON);
		assertEquals(40, resultPosition[1], EPSILON);
		
		// Test 2: Collision in Upper-Left Quadrant
		Entity ship2 = new Ship(30, 15, -7, 10, 10, 0);
		assertEquals(2.5,testWorld.getTimeToCollisionWithBoundaries(ship2), EPSILON);
		 
		resultPosition = testWorld.getPositionToCollisionWithBoundaries(ship2);
		assertEquals(12.5, resultPosition[0], EPSILON);
		assertEquals(40, resultPosition[1], EPSILON);
		 
		// Test 3: Collision in Lower-Left Quadrant
		Entity bullet1 = new Bullet(70, 30, -12,-5, 10);
		assertEquals(4, testWorld.getTimeToCollisionWithBoundaries(bullet1), EPSILON);
		
		resultPosition = testWorld.getPositionToCollisionWithBoundaries(bullet1);
		assertEquals(22, resultPosition[0], EPSILON);
		assertEquals(10, resultPosition[1], EPSILON);
		
		// Test 4: Collision in the Lower-Right Quadrant
		Entity bullet2 = new Bullet(90, 20, 15, -5, 10);
		assertEquals(2, testWorld.getTimeToCollisionWithBoundaries(bullet2), EPSILON);
		
		resultPosition = testWorld.getPositionToCollisionWithBoundaries(bullet2);
		assertEquals(120, resultPosition[0], EPSILON);
		assertEquals(10, resultPosition[1], EPSILON);
		
	}
	
	// -*-*- evolve tests -*-*-
	@Test
	public void evolveTest(){
		//TODO: implementation
	}
	
	// -*-*- Termination tests -*-*-
	@Test
	public void terminationTests(){
		assertFalse(globWorld.isTerminated());
		
		// Create a testWorld with 3 random entities
		World testWorld = new World(150, 150);
		Entity testEntity1 = new Ship(10,10, 0, 0, 10, 0);
		testWorld.addEntity(testEntity1);
		Entity testEntity2 = new Bullet(20, 10, 0, 0, 10);
		testWorld.addEntity(testEntity2);
		Entity testEntity3 = new Bullet(30, 10, 0, 0, 10);
		testWorld.addEntity(testEntity3);
		
		// terminate the world
		testWorld.destroy();
		
		// Check whether testWorld is well terminated
		Set<Entity> entities = testWorld.queryEntities();
		assertTrue(entities.size() == 0);
		
		assertTrue(testEntity1.getWorld() == null);
		assertTrue(testEntity2.getWorld() == null);
		assertTrue(testEntity3.getWorld() == null);
		
		assertTrue(testWorld.isTerminated());
	}
}