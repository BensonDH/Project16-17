package asteroids.programs.statements;

import java.util.*;

import asteroids.part3.programs.SourceLocation;
import asteroids.programs.*;

/**
 * A class representing a statement
 */
public abstract class Statement {
	
	public Statement(SourceLocation sourceLocation){
		this.sourceLocation = sourceLocation;
	}
	
	/**
	 * This Constructor should not be used
	 * 
	 * @throws IllegalStateException
	 * 			Always
	 * 			| true
	 */
	public Statement(){
		throw new IllegalStateException("Cannot initialize this statement without a SourceLocation");
	};
	
	/**
	 * Get the position (row, column) of this statement in it's program.
	 */
	public SourceLocation getSourceLocation(){
		return this.sourceLocation;
	}
	
	/**
	 * Variable registering location where this statement is positioned
	 * in the program. 
	 */
	private final SourceLocation sourceLocation;
	
	
	public abstract void execute(Program parentProgram);
}
