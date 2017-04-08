package asteroids.tests;

import static org.junit.Assert.assertEquals;

import org.junit.*;

import asteroids.model.World;
import asteroids.model.Entity;
import asteroids.model.Ship;


public class WorldTests {
	private static final double EPSILON = 0.0001;
	
	
	World globWorld;
	@Before
	public void SetUp(){
		globWorld = new World(100, 100);
	}
	@Test
	public void boundariesTest(){
		Entity ent = new Ship(50, 50, 5, 5, 10, 0);
		globWorld.addEntity(ent);
		assertEquals(8, globWorld.getTimeToCollisionWithBoundaries(ent), EPSILON);
	}
}
