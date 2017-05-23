package asteroids.programs.expressions;

import asteroids.part3.programs.SourceLocation;
import asteroids.programs.*;

/**
 * A class representing an expression
 */
public abstract class Expression<T> {
	
	public Expression(Class<T> className, SourceLocation sourceLocation) {
		//this.classType = classType;
		this.sourceLocation = sourceLocation;
		this.className = className;
	}
	
	public SourceLocation getSourceLocation(){
		return this.sourceLocation;
	}
	
	private final SourceLocation sourceLocation;
	
	public Class<T> getReturnType(){
		return this.className;
	}
	
	private final Class<T> className;
	
	public abstract Literal<T> eval(Executable parentExecutable); 
}
