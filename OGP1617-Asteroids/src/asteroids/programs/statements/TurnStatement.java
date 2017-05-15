package asteroids.programs.statements;

import asteroids.part3.programs.SourceLocation;
import asteroids.programs.Program;
import asteroids.programs.expressions.*;

public class TurnStatement extends ActionStatement {

	/**
	 * Create a new TurnStatement with the given angle and sourceLocation
	 */
	public TurnStatement(Expression angle, SourceLocation sourceLocation) {
		super(sourceLocation);
		if (!(angle.eval() instanceof Double))
			throw new IllegalArgumentException("Evaluation of the given expression does not result in a number");
		this.angle = angle;
	}

	/**
	 * This constructor should not be used.
	 * 
	 * @throws IllegalStateException
	 * 			Always
	 * 			| true
	 */
	public TurnStatement(){
		super(null);
		throw new IllegalStateException("Cannot create a new TurnStatement without parameters.");
	}
	
	/**
	 * Return the angle of this TurnStatement.
	 */
	public Expression getAngle(){
		return this.angle;
	}
	
	/**
	 * Variable registering the angle of this TurnStatement.
	 */
	private final Expression angle;
	
	@Override
	public void execute(Program parentProgram) {
		if (parentProgram == null || parentProgram.getAssociatedShip() == null)
			throw new NullPointerException("This TurnStatement has to be associated with a program that is loaded on a ship.");
		
		handleExecutionTime(parentProgram);
		double newAngle = parentProgram.getAssociatedShip().getAngle() + (double)getAngle().eval();
		parentProgram.getAssociatedShip().setAngle(newAngle%(2*Math.PI));
	}

}