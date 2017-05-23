package asteroids.programs.statements;

import asteroids.part3.programs.SourceLocation;
import asteroids.programs.*;
import asteroids.programs.exceptions.SyntaxException;
import asteroids.programs.expressions.*;

public class ReturnStatement extends Statement {
	
	
	public ReturnStatement(Expression<?> returnExpression, SourceLocation sourceLocation) {
		super(sourceLocation);
		this.returnExpression = returnExpression;
	}
	
	public Expression<?> getReturnExpression(){
		return this.returnExpression;
	}
	
	private final Expression<?> returnExpression;
	
	@Override
	public void execute(Executable parentExecutable) {
		if (isFinished())
			return;
		
		if (!(parentExecutable instanceof Function))
			throw new SyntaxException("Syntax exception: Return statement outside a function body.");
		
		Literal<?> evaluatedReturnExpression = getReturnExpression().eval(parentExecutable);
		
		((Function)parentExecutable).setFunctionReturnValue((Literal<Object>) evaluatedReturnExpression);
		// Terminate the function.
		((Function)parentExecutable).terminate();
	}
	
	@Override
	public Statement clone(){
		return new ReturnStatement(getReturnExpression(), getSourceLocation());
	}
}
