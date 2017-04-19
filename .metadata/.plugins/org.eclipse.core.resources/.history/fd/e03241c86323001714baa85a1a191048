package asteroids.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.*;
import org.junit.Test;

import asteroids.model.Bullet;
import asteroids.model.Collision;
import asteroids.model.Entity;
import asteroids.model.Ship;
import asteroids.model.Vector;



public class RandomTests {
	private static final double EPSILON = 0.0001;
	
	@Test
	public void randTest(){
		Ship testShip = new Ship(0,0,0,0,10,0);
		Bullet testBullet = new Bullet(0,0,0,0,10);
		Collision testCollision = new Collision(testShip, testBullet, new Vector(10, 10), 10);
		
		System.out.println(testCollision);
	}
}