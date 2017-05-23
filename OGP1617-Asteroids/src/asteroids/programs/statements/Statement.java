package asteroids.programs.statements;

import asteroids.part3.programs.SourceLocation;
import asteroids.programs.Executable;

/**
 * A class representing a statement
 */
public abstract class Statement implements Cloneable {
	
	public Statement(SourceLocation sourceLocation){
		this.sourceLocation = sourceLocation;
	}
	
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
	
	/**
	 * Check whether this statement has been executed or not.
	 */
	public boolean isFinished(){
		return this.isFinished;
	}
	
	public void reset(){
		this.isFinished = false;
	}
	
	public void terminate(){
		this.isFinished = true;
	}
	
	/**
	 * Variable registering whether this statement has been executed.
	 */
	private boolean isFinished = false;
	
	
	public abstract void execute(Executable parentProgram);
	
	@Override
	public abstract Statement clone();

}
