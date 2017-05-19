package asteroids.programs.statements;

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
	
	/**
	 * Set the isFinished status of this statement on the given status.
	 * - true meaning this statement was executed.
	 * - false meaning this statement hasn't been executed yet. 
	 */
	public void setFinished(boolean status){
		this.isFinished = status;
	}
	
	/**
	 * Variable registering whether this statement has been executed.
	 */
	private boolean isFinished = false;
	
	
	public void reset(){
		setFinished(false);
	}
	
	public abstract void execute(Program parentProgram);

}
