package asteroids.tests;

import static org.junit.Assert.*;

import java.util.HashMap;
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
		Bullet bullet1 = new Bullet(0,0,0,0,10);
		Bullet bullet2 = new Bullet(0,0,0,0,10);
		Bullet bullet3 = new Bullet(0,0,0,0,10);
		Bullet bullet4 = new Bullet(0,0,0,0,10);
		Bullet bullet5 = new Bullet(0,0,0,0,10);
		
		Ship testShip = new Ship(0,0,0,0,10,0);
		testShip.addBullet(bullet1, bullet2, bullet3, bullet4, bullet5);
		
		System.out.println("baseMass ship: "+testShip.getBaseMass());
		Bullet[] bullets =new Bullet[]{bullet1, bullet2, bullet3, bullet4, bullet5};
		for (int index=1;index<=5;index++){
			System.out.println("mass bullet"+index+": "+bullets[index-1].getTotalMass());
		}
		
		System.out.println("-------");
		System.out.println("total mass ship: "+ testShip.getTotalMass());
	}
}