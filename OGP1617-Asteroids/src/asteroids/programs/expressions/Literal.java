package asteroids.programs.expressions;

import asteroids.part3.programs.SourceLocation;
import asteroids.programs.Program;

public class Literal<T> extends Expression<T>{

	public Literal(Class<T> typeClassName, T value, SourceLocation sourceLocation) {
		super(sourceLocation);
		this.typeClassName = typeClassName;
		this.value = value;
	}

	public Literal(Class<T> typeClassName, T value){
		this(typeClassName, value, null);
	}
	
	/**
	 * Get the value of this Literal.
	 */
	public T getValue(Program parentProgram){
		return this.value;
	}
	
	/**
	 * Variable registering the value that this Literal represents.
	 */
	private final T value;
	
	/**
	 * Get the className of this Literal's Type T.
	 */
	public Class<T> getLiteralType(){
		return this.typeClassName;
	}

	/**
	 * Variable registering the className of T.
	 */
	private final Class<T> typeClassName;
	
	@Override
	public Literal<T> eval(Program parentProgram) {
		return this;
	}

}
