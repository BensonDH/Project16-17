package asteroids.tests;

import static org.junit.Assert.*;

import org.junit.*;

import asteroids.model.Ship;
import asteroids.model.Vector;
import asteroids.model.World;



public class VectorTests {
	
	private static final double EPSILON = 0.0001;
	
	Vector vector1;
	Vector vector2;
	
	@Before
	public void SetUp(){
		vector1 = new Vector(3, 4);
		vector2 = new Vector(2,1);
	}
	
	@Test
	public void norm(){
		assertEquals(5, vector1.norm(),EPSILON);
		assertEquals(Math.sqrt(5),vector2.norm(),EPSILON);
	}
	
	@Test
	public void dot(){
		double testValue = vector1.dot(vector2);
		assertEquals(10,testValue,EPSILON);
	}
	
	@Test
	public void multiply(){
		Vector testVector = vector1.multiply(5);
		assertEquals(15,testVector.getX(),EPSILON);
		assertEquals(20,testVector.getY(),EPSILON);
	}
	
	
	@Test
	public void subtract(){
		Vector testVector = vector1.subtract(vector2);
		assertEquals(1,testVector.getX(),EPSILON);
		assertEquals(3, testVector.getY(),EPSILON);
	}
	
	@Test
	public void add(){
		Vector testVector = vector1.add(vector2);
		assertEquals(5,testVector.getX(),EPSILON);
		assertEquals(5, testVector.getY(),EPSILON);
	}
	
	@Test
	public void getOrientationAngle(){
		Vector testVector = new Vector(0,5);
		assertEquals(Math.PI/2,testVector.getOrientationAngle(),EPSILON);
	}
	
	@Test
	public void equals(){
		Vector vector1 = new Vector(3,3);
		Vector vector2 = new Vector(3,3);
		Vector vector3 = new Vector(2, 2);
		assertTrue(vector1.equals(vector2));
		assertFalse(vector2.equals(vector3));
		
	}
}
