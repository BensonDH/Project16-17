package asteroids.programs.statements;

import asteroids.part3.programs.SourceLocation;
import asteroids.programs.Program;

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
	public void execute(Program parentProgram) {
		if (parentProgram == null || parentProgram.getAssociatedShip() == null)
			throw new NullPointerException("This FireBulletStatement has to be associated with a program that is loaded on a ship.");
		
		handleExecutionTime(parentProgram);
		parentProgram.getAssociatedShip().fireBullet();
	}

}
