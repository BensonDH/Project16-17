package asteroids.tests;

import static org.junit.Assert.*;

import java.util.*;
import java.util.stream.Collectors;

import org.junit.*;
import asteroids.model.*;

public class RandomTests {
	@Test
	public void test(){
		Bullet testBullet = new Bullet(0,0,0,0,20);
		
		Class<? extends Entity> toBeFiltered = Ship.class; 
	System.out.println(toBeFiltered.isInstance(testBullet));
	}
}