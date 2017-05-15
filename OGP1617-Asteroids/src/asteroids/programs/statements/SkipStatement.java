package asteroids.programs.statements;

import asteroids.part3.programs.SourceLocation;
import asteroids.programs.Program;

public class SkipStatement extends ActionStatement {

	/**
	 * Create a new SkipStatement with the given sourceLocation.
	 */
	public SkipStatement(SourceLocation sourceLocation) {
		super(sourceLocation);
	}
	
	/**
	 * This constructor should not be used.
	 * 
	 * @throws IllegalStateException
	 * 			Always
	 * 			| true
	 */
	public SkipStatement() {
		super(null);
		throw new IllegalStateException("Cannot create a new SkipStatement without a sourceLocation");
	}

	@Override
	public void execute(Program parentProgram) {
		if (parentProgram == null || parentProgram.getAssociatedShip() == null)
			throw new NullPointerException("This SkipStatement has to be associated with a program that is loaded on a ship.");
		
		handleExecutionTime(parentProgram);
	}

}