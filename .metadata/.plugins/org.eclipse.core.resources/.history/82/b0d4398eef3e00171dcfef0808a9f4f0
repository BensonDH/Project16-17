package asteroids.programs.expressions;

import asteroids.part3.programs.SourceLocation;
import asteroids.programs.*;

/**
 * A class representing an expression
 */
public abstract class Expression<T> {
	
	public Expression(SourceLocation sourceLocation) {
		//this.classType = classType;
		this.sourceLocation = sourceLocation;
	}

	//public Class<T> getType(){
	//	return this.classType;
	//}
	
	//private final Class<T> classType;
	
	public SourceLocation getSourceLocation(){
		return this.sourceLocation;
	}
	
	private final SourceLocation sourceLocation;
	
	public abstract Literal<T> eval(Program parentProgram); 
}
