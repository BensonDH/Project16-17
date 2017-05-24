package asteroids.programs.expressions;

import asteroids.model.Ship;
import asteroids.part3.programs.SourceLocation;
import asteroids.programs.*;

public class GetDirectionExpression extends Expression<Double>{

	public GetDirectionExpression(SourceLocation sourceLocation) {
		super(Double.class, sourceLocation);		
	}

	public GetDirectionExpression() {
		super(Double.class, null);		
	}
	
	@Override
	public Literal<Double> eval(Executable parentExecutor) {
		if (parentExecutor == null)
			return null;
		
		Ship executingShip= parentExecutor.getAssociatedShip();
		if (executingShip == null)
			throw new IllegalStateException("The program is not loaded in any ship.");
		
		return new Literal<Double>(Double.class, executingShip.getAngle());
	}

}