package asteroids.programs.statements;

import asteroids.part3.programs.SourceLocation;
import asteroids.programs.*;
import asteroids.programs.exceptions.IllegalTypeException;

public class SetThrustStatement extends ActionStatement {

	/**
	 * Create a new DisableThrustStatement with the given sourceLocation.
	 */
	public SetThrustStatement(boolean mode, SourceLocation sourceLocation) {
		super(sourceLocation);
		this.newThrustMode = mode;
	}
	
	/**
	 * This constructor should not be used.
	 * 
	 * @throws IllegalStateException
	 * 			Always
	 * 			| true
	 */
	public SetThrustStatement(){
		super(null);
		throw new IllegalStateException("Cannot create a DisableThrustStatement without a sourceLocation.");
	}
	
	public boolean getNewThrustMode(){
		return this.newThrustMode;
	}
	
	private final boolean newThrustMode;
	
	@Override
	public void execute(Executable parentExecutor) {
		// If this SetThrustStatement has already been executed, we don't have to do anything
		if (isFinished())
			return;
		
		if (!(parentExecutor instanceof Program))
			throw new IllegalTypeException(Program.class, parentExecutor.getClass());
		
		Program parentProgram = (Program)parentExecutor;
		if (parentProgram == null || parentProgram.getAssociatedShip() == null)
			throw new NullPointerException("This SetThrustStatement has to be associated with a program that is loaded on a ship.");
		
		handleExecutionTime(parentProgram);
		
		// If at this stage the parentProgram is not paused, we can execute this statement successfully
		if (!(parentProgram.isPaused())) {
			parentProgram.getAssociatedShip().setThrust(getNewThrustMode());
			
			terminate();
		}
	}
	
	@Override
	public Statement clone(){
		return new SetThrustStatement(getNewThrustMode(), getSourceLocation());
	}
}
