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
		Literal<Double> twee = new Literal<Double>(Double.class, Double.valueOf(5));
		Literal<Double> een = new Literal<Double>(Double.class, Double.valueOf(1));
		
		ChangeSignExpression minEen = new ChangeSignExpression(een);
		AdditionExpression tweeMinEen = new AdditionExpression(twee, minEen);
		
		System.out.println(tweeMinEen.eval(null).getValue(null));
	}
}