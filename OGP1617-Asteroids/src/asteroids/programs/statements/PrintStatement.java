package asteroids.programs.statements;

import asteroids.part3.programs.SourceLocation;
import asteroids.programs.*;
import asteroids.programs.exceptions.SyntaxException;
import asteroids.programs.expressions.*;

public class PrintStatement extends Statement {
	
	/**
	 * Only this constructor should be used.
	 */
	public PrintStatement(Expression<?> E, SourceLocation sourceLocation){
		super(sourceLocation);
		this.expression = E;
	}
	
	/**
	 * The default constructor should not be used because
	 * we cannot print nothing.
	 * 
	 * @throws IllegalStateException 	Always
	 * 									| true
	 */
	public PrintStatement() throws IllegalStateException {
		super(null);
		throw new IllegalStateException("Cannot print nothing!");
	}
	
	/**
	 * Get the expression that will be printed out.
	 */
	public Expression<?> getExpression(){
		return this.expression;
	}
	
	/**
	 * Variable registering the printed expression.
	 */
	private Expression<?> expression;
	
	/**
	 * Print and return the evaluation of this PrintStatement's expression.
	 */
	public void execute(Executable parentExecutable){
		// If this PrintStatement has already been executed, we don't have to do anything.
		if (isFinished())
			return;
		if (!(parentExecutable instanceof Program))
			throw new SyntaxException("Syntax exception: Print statement in a function body.");
		
		Object result = getExpression().eval(parentExecutable).getValue(parentExecutable);
		if (result == null)
			System.out.println("null");
		else
			System.out.println(result.toString());
		
		((Program)parentExecutable).addPrintedValues(result);
		
		// This PrintStatement is finished.
		terminate();
	}
	
	@Override
	public Statement clone(){
		return new PrintStatement(getExpression(), getSourceLocation());
	}
}
