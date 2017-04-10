package asteroids.model;

import be.kuleuven.cs.som.annotate.*;

/**
 * Class representing a value in a 2 dimensional space.	Values returned have to be doubles
 */
@Value
public class Vector {
	public Vector(double x, double y){
		setX(x);
		setY(y);
		
	}
	
	/**
	 * Return the X-component of this vector.
	 */
	public double getX(){
		return this.x;
	}
	
	/**
	 * Set the X_component of this vector to the given x.
	 * 
	 * @param x		The value that will be given to the X-component of this vector.
	 * @post		The X-component of this vector will be equal to x.
	 * 				| new.getX() == x
	 */
	public void setX(double x){
		this.x = x;
	}
	
	/**
	 * Variable registering the X-component of this vector.
	 */
	private double x;
	
	/**
	 * Return the Y-component of this vector.
	 */
	public double getY(){
		return this.y;
	}
	
	/**
	 * Set the Y-component of this vector to the given y.
	 * 
	 * @param y		The value that will be given to the Y-component of this vector
	 * @post		The Y-component of this vector will be equal to y.
	 * 				| new.getY() == y
	 */
	public void setY(double y){
		this.y = y;
	}
	
	/**
	 * Variable registering the Y-component of this vector
	 */
	private double y;
	
	/**
	 * Convert this vector into an array represented as [X-component, Y-component]
	 * 
	 * @return An array representing this vector.
	 */
	public double[] toDoubleArray(){
		return new double[]{getX(),getY()};
	}
	
	/**
	 * Return the Euclidean norm (also known as the 2-norm) of this 2-Dimensional vector.
	 *  
	 * @return	The Euclidean norm of this vector
	 * 			| sqrt(getX()^2 + getY()^2)
	 */
	public double norm(){
		return Math.sqrt(Math.pow(getX(),2)+Math.pow(getY(), 2));
	}
	
	/**
	 * Return a new vector obtained by subtracting this vector with the given other vector.
	 * 
	 * @param other		The right-hand side of this subtraction.
	 * @return			The X-component of the result of this subtraction is equal to 
	 * 					the subtraction between the X-components of this vector and 
	 * 					the other given vector.
	 * 					| result.getX() == getX() - other.getX()
	 * @return			The Y-component of the result of this subtraction is equal to
	 * 					the subtraction between the Y-components of this vector and
	 * 					the other given vector.
	 * 					| result.getY() == getY() - other.getY()
	 * @throws NullPointerException
	 * 					if the other vector is null
	 * 					| other == null
	 */
	public Vector subtract(Vector other) throws NullPointerException {
		if (other == null)
			throw new NullPointerException("Other is null");
		
		return new Vector(getX() - other.getX(), getY() -other.getY());
	}
	
	/**
	 * Return a new vector obtained by adding the given other vector to this vector.
	 * 
	 * @param other		The right-hand side of this addition.
	 * @return			The X-component of the resulting vector is equal to the addition
	 * 					of the X-components of this vector and the other given vector.
	 * 					| result.getX() == getX() + other.getX()
	 * @return			The Y-component of the resulting vector is equal to the addition
	 * 					of the Y-components of this vector and the other given vector.
	 * 					| result.getY() == getY() + other.getY()
	 * @throws NullPointerException
	 * 					if the other vector is null
	 * 					| other == null
	 */
	public Vector add(Vector other) throws NullPointerException {
		if (other == null)
			throw new NullPointerException("Other is null");
		
		return new Vector(getX() + other.getX(), getY() + other.getY());
	}
	
	/**
	 * Return a new vector obtained by multiplying this vector with the given scalar.
	 * 
	 * @param scalar
	 * 				The integer number to multiply this vector with.
	 * @return		Each component of the resulting vector will be equal to the
	 * 				old value of the component times the given scalar.
	 * 				| result.getX() == getX()*scalar
	 * 				| result.getY() == getY()*scalar
	 */
	public Vector multiply(double scalar){	
		return new Vector(getX()*scalar, getY()*scalar);
		
	}
	
	/**
	 * Return the dot product between this vector and the given other vector.
	 * 
	 * @param other
	 * 				The right-hand side of the dot product.
	 * @return		The resulting value will be equal to the sum of the 
	 * 				multiplication between each component of this vector
	 * 				and the given other vector.
	 * 				| result == getX()*other.getX() + getY()*other.getY() 
	 * @throws NullPointerException
	 * 				if the other vector is null
	 * 				| other == null
	 */
	public double dot(Vector other) throws NullPointerException {
		if (other == null)
			throw new NullPointerException("Other is null");
		
		return getX()*other.getX() + getY()*other.getY();
	}
	
	/**
	 * Return a boolean reflecting whether this vector is identical to the
	 * given other vector.
	 * 
	 * @param other	
	 * 			The Object to compare with.
	 * @return	True if and only if:
	 * 				-The given object has a size of 2
	 * 				-The X-component of this vector is equal to the first
	 * 				 component of the given object
	 * 				-The Y-component of this vector is equal to the second
	 * 				 component of the given object
	 */
	@Override
	public boolean equals(Object other){
		if (other == null)
			return false;
		if(other == this)
			return true;
		// TODO: instanceof gebruiken?
		if (this.getClass() != other.getClass())
			return false;
		Vector o = (Vector)other;
		if (getX() != o.getX())
			return false;
		if (getY() != o.getY())
			return false;
		return true;
	}
	
	/**
	 * Return a copy of this vector.
	 * 
	 * @return		A copy of this vector.
	 * 				| result.getX() == getX()
	 * 				| result.getY() == getY()
	 */
	@Override
	protected Vector clone(){
		return new Vector(getX(),getY());
	}
	
	/**
	 * Return the hashCode of this vector
	 * 
	 * @effect Object.hashCode()
	 */
	@Override
	public int hashCode() {
		return super.hashCode();
	}	

	/**
	 * Return a textual representation of this vector.
	 * 
	 * @return	A textual representation of this vector in the form
	 * 			Vector [x=X-component, y=Y-component]
	 * 			| result.equals("Vector [x=" + getX() + ", y=" + getY() + "]")
	 */
	@Override
	public String toString() {
		return "Vector [x=" + getX() + ", y=" + getY() + "]";
	}
}
