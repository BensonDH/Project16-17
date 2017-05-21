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
		
	Literal<Double> testLit = new Literal<>(Double.class, Double.valueOf(5));
	Literal<Boolean> testLit2 = new Literal<>(Boolean.class, true);
	
	Map<String, Literal<?>> testMap = new HashMap<String, Literal<?>>();
	
	testMap.put("test", testLit);
	testMap.put("test2", testLit2);
	
	
	System.out.println(testMap);
	}
}