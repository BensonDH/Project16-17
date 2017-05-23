package asteroids.programs.expressions;

import asteroids.part3.programs.SourceLocation;
import asteroids.programs.Executable;

public class Literal<T> extends Expression<T>{

	public Literal(Class<T> typeClassName, T value, SourceLocation sourceLocation) {
		super(typeClassName, sourceLocation);
		this.value = value;
	}

	public Literal(Class<T> typeClassName, T value){
		this(typeClassName, value, null);
	}
	
	/**
	 * Get the value of this Literal.
	 */
	public T getValue(Executable parentExecutor){
		return this.value;
	}
	
	/**
	 * Variable registering the value that this Literal represents.
	 */
	private final T value;
	
	@Override
	public Literal<T> eval(Executable parentExecutor) {
		return this;
	}

}
