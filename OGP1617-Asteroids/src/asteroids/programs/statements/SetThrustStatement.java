package asteroids.programs.statements;

import asteroids.part3.programs.SourceLocation;
import asteroids.programs.Program;

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
	public void execute(Program parentProgram) {
		if (parentProgram == null || parentProgram.getAssociatedShip() == null)
			throw new NullPointerException("This DisableThrustStatement has to be associated with a program that is loaded on a ship.");
		
		handleExecutionTime(parentProgram);
		parentProgram.getAssociatedShip().setThrust(getNewThrustMode());
		
	}

}