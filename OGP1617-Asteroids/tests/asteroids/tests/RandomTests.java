package asteroids.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.*;
import org.junit.Test;

import asteroids.model.Bullet;
import asteroids.model.Entity;
import asteroids.model.Ship;
import asteroids.model.Vector;



public class RandomTests {
	private static final double EPSILON = 0.0001;
	
	@Test
	public void randTest(){
		Map<Vector, Entity> testMap = new HashMap<Vector, Entity>();
		Entity entity1 = new Ship(10, 10, 0, 0, 10, 0);
		Entity entity2 = new Ship(30, 30, 0, 0, 10, 0);
		testMap.put(entity1.getPosition(), entity1);
		testMap.put(entity2.getPosition(), entity2);
		Vector testVector = new Vector(30,30);
		System.out.println(testMap.containsKey(testVector));
	}
}