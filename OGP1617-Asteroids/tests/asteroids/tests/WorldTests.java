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
	
	// Constructor tests
	
}