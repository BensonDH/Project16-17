package asteroids.programs.expressions;

import asteroids.model.Ship;
import asteroids.part3.programs.SourceLocation;
import asteroids.programs.Program;

public class GetDirectionExpression extends Expression<Double>{

	public GetDirectionExpression(SourceLocation sourceLocation) {
		super(sourceLocation);		
	}

	public GetDirectionExpression() {
		super(null);		
	}
	
	@Override
	public Literal<Double> eval(Program parentProgram) {
		if (parentProgram == null)
			return null;
		
		Ship executingShip = parentProgram.getAssociatedShip();
		if (executingShip == null)
			throw new IllegalStateException("The program is not loaded in any ship.");
		
		return new Literal<Double>(Double.class, executingShip.getAngle());
	}

}
