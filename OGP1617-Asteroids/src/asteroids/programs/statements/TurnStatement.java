package asteroids.programs.statements;

import javax.sql.rowset.spi.SyncFactoryException;

import asteroids.part3.programs.SourceLocation;
import asteroids.programs.Program;
import asteroids.programs.exceptions.IllegalTypeException;
import asteroids.programs.expressions.*;
import jdk.nashorn.internal.runtime.regexp.joni.exception.SyntaxException;

public class TurnStatement extends ActionStatement {

	/**
	 * Create a new TurnStatement with the given angle and sourceLocation
	 */
	public TurnStatement(Expression angle, SourceLocation sourceLocation) {
		super(sourceLocation);
		
		if (!(angle instanceof ReturnTypeDouble))
			throw new IllegalTypeException(ReturnTypeDouble.class, angle.getClass());
		
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
		// If this TurnStatement has already been executed, we don't have to do anything
		if (isFinished())
			return;
		
		if (parentProgram == null || parentProgram.getAssociatedShip() == null)
			throw new NullPointerException("This TurnStatement has to be associated with a program that is loaded on a ship.");
		
		handleExecutionTime(parentProgram);
		
		// If at this stage the parentProgram is not paused, then this statement can be executed successfully
		if (!(parentProgram.isPaused())) {
			Literal angle = getAngle().eval(parentProgram);
			if (!(angle instanceof DoubleLiteralExpression))
				throw new IllegalTypeException(DoubleLiteralExpression.class, angle.getClass());
			
			double newAngle = parentProgram.getAssociatedShip().getAngle() + ((DoubleLiteralExpression)angle).getValue(parentProgram);
			
			if (newAngle >= 0 && newAngle <= (Math.PI*2))
				parentProgram.getAssociatedShip().setAngle(newAngle);
			// If there is an illegal angle, we do nothing.
			
			setFinished(true);
		}
	}
}
