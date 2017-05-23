package asteroids.programs.statements;

import asteroids.part3.programs.SourceLocation;
import asteroids.programs.*;
import asteroids.programs.exceptions.IllegalTypeException;

public class FireBulletStatement extends ActionStatement {
	
	/**
	 * Create a new FireBulletStatement with the given sourceLocation.
	 */
	public FireBulletStatement(SourceLocation sourceLocation) {
		super(sourceLocation);
	}
	
	/**
	 * This constructor should not be used.
	 * 
	 * @throws IllegalStateException
	 * 			Always
	 * 			| true
	 */
	public FireBulletStatement(){
		super(null);
		throw new IllegalStateException("A FireBulletStatement cannot be created without a sourceLocation");
	}

	@Override
	public void execute(Executable parentExecutor) {
		// If this FireBulletStatement has already been executed, we don't have to do anything
		if (isFinished())
			return;
		
		if (!(parentExecutor instanceof Program))
			throw new IllegalTypeException(Program.class, parentExecutor.getClass());
		
		Program parentProgram = (Program)parentExecutor;
		if (parentProgram == null || parentProgram.getAssociatedShip() == null)
			throw new NullPointerException("This FireBulletStatement has to be associated with a program that is loaded on a ship.");
		
		handleExecutionTime(parentProgram);
		
		// If at this stage the parentProgram is not paused, we can execute this statement successfully.
		if (!(parentProgram.isPaused())){
			parentProgram.getAssociatedShip().fireBullet();
			
			terminate();
		}
	}
	
	@Override
	public Statement clone(){
		return new FireBulletStatement(getSourceLocation());
	}
}
