package asteroids.programs.statements;


import asteroids.part3.programs.SourceLocation;
import asteroids.programs.Program;
import asteroids.programs.expressions.*;

public class TurnStatement extends ActionStatement {

	/**
	 * Create a new TurnStatement with the given angle and sourceLocation
	 */
	public TurnStatement(Expression<Double> angle, SourceLocation sourceLocation) {
		super(sourceLocation);
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
	public Expression<Double> getAngle(){
		return this.angle;
	}
	
	/**
	 * Variable registering the angle of this TurnStatement.
	 */
	private final Expression<Double> angle;
	
	@Override
	public void execute(Program parentProgram) {
		// If this TurnStatement has already been executed, we don't have to do anything
		if (isFinished())
			return;
		
		if (parentProgram == null || parentProgram.getAssociatedShip() == null)
			throw new NullPointerException("This TurnStatement has to be associated with a program that is loaded on a ship.");
		
		handleExecutionTime(parentProgram);
		
		// If at this stage the parentProgram is not paused, then this statement can be executed successfully
		if (!(parentProgram.isPaused())) {
			Literal<Double> angle = getAngle().eval(parentProgram);
			
			double newAngle = parentProgram.getAssociatedShip().getAngle() + angle.getValue(parentProgram);
			
			if (newAngle >= 0 && newAngle <= (Math.PI*2))
				parentProgram.getAssociatedShip().setAngle(newAngle);
			// If there is an illegal angle, we do nothing.
			
			setFinished(true);
		}
	}
}
