package asteroids.programs.statements;

import asteroids.part3.programs.SourceLocation;
import asteroids.programs.*;

public abstract class ActionStatement extends Statement {
	
	public ActionStatement(SourceLocation sourceLocation){
		super(sourceLocation);
	}
	
	/**
	 * Get the time it takes to execute this ActionStatement.
	 */
	public static double getExecutionTime(){
		return ActionStatement.executionTime;
	}
	
	/**
	 * Variable registering the execution time of an ActionStatement
	 */
	private static final double executionTime = 0.2;
	
	/**
	 * Handle everything to do with the executionTime of a program.
	 */
	public void handleExecutionTime(Program parentProgram){
		if (parentProgram == null)
			throw new NullPointerException("The parentProgram cannot be null.");
		
		// Normal case, there is enough time left to execute this ActionStatement
		if (parentProgram.getExecutionTime() >= ActionStatement.getExecutionTime())
			parentProgram.addExecutionTime(-ActionStatement.getExecutionTime());
		// Other case, there not enough time left to execute this ActionStatement	
		else {
			parentProgram.pause();
		}
			
	}
}
