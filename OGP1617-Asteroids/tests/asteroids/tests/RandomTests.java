package asteroids.tests;

import static org.junit.Assert.*;

import java.util.*;
import java.util.stream.Collectors;

import org.junit.*;
import asteroids.model.*;
import asteroids.programs.expressions.*;

public class RandomTests {
	@Test
	public void test(){
		Planetoid planetoid = new Planetoid(0,0,1000,0,50, 0);
		planetoid.move(50);
		System.out.println(planetoid.getDistanceTravelled());
		System.out.println(planetoid.getRadius());
	}
}