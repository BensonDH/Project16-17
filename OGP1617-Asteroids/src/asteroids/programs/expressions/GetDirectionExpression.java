package asteroids.programs.expressions;

import asteroids.model.Ship;
import asteroids.part3.programs.SourceLocation;
import asteroids.programs.Program;

public class GetDirectionExpression extends Expression implements ReturnTypeDouble {

	public GetDirectionExpression(SourceLocation sourceLocation) {
		super(sourceLocation);		
	}

	
	@Override
	public Literal eval(Program parentProgram) {
		if (parentProgram == null)
			return null;
		
		Ship executingShip = parentProgram.getAssociatedShip();
		if (executingShip == null)
			throw new IllegalStateException("The program is not loaded in any ship.");
		
		
		return new DoubleLiteralExpression(executingShip.getAngle());
	}

}
