package asteroids.programs.statements;

import java.util.*;

import asteroids.part3.programs.SourceLocation;
import asteroids.programs.*;
import asteroids.programs.expressions.*;

public class PrintStatement extends Statement {
	
	/**
	 * Only this constructor should be used.
	 */
	public PrintStatement(Expression E, SourceLocation sourceLocation){
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
		throw new IllegalStateException("Cannot print nothing!");
	}
	
	/**
	 * Get the expression that will be printed out.
	 */
	public Expression getExpression(){
		return this.expression;
	}
	
	/**
	 * Variable registering the printed expression.
	 */
	private Expression expression;
	
	/**
	 * Print and return the evaluation of this PrintStatement's expression.
	 */
	public void execute(Program parentProgram){
		// If this PrintStatement has already been executed, we don't have to do anything.
		if (isFinished())
			return;
		
		Object result = getExpression().eval();
		System.out.println(result.toString());
		parentProgram.addPrintedValues(result);
		
		// This PrintStatement is finished.
		setFinished(true);
	}
}
