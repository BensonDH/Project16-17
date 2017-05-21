package asteroids.programs.statements;

import asteroids.programs.expressions.*;
import asteroids.part3.programs.SourceLocation;
import asteroids.programs.*;

public class IfThenElseStatement extends Statement {

	/**
	 * Initialize a new If-then-else statement with the given
	 * parameters.
	 * 
	 * This if-then-else statement is represented as:
	 * if (expression) then {ifBody} else {elseBody}
	 * 
	 * @param expression
	 * 			The expression that comes after the if keyword
	 * @param ifBody
	 * 			The body that belongs to the if statement.
	 * @param elseBody
	 * 			The body that belongs to the else statement.
	 * @throws NullPointerException
	 * 			If the given Expression or ifBody is null
	 * 			| if (E == null || ifBody == null)
	 */
	public IfThenElseStatement(Expression<Boolean> expression, Statement ifBody, Statement elseBody, 
							   SourceLocation sourceLocation) throws NullPointerException{
		super(sourceLocation);
		if (expression == null || ifBody == null)
			throw new NullPointerException();
		
		this.expression = expression;
		this.ifBody = ifBody;
		this.elseBody= elseBody;
	}
	
	/**
	 * Initialize a new If-then statement with the given parameters
	 * 
	 * This if if-then statement is represented as:
	 * if (expression) then {ifBody}
	 * 
	 * @param expression
	 * 			The expression that comes after the if keyword.
	 * @param ifBody
	 * 			The body that belongs to the if statement.
	 * @throws NullPointerException
	 * 			If the given parentProgram, printedValues, expression or ifBody is null
	 * 			| if (expression == null || ifBody == null)
	 */
	public IfThenElseStatement(Expression<Boolean> expression, Statement ifBody,
								SourceLocation sourceLocation) throws NullPointerException{
		super(sourceLocation);
		if (expression == null || ifBody == null)
			throw new NullPointerException();
		
		this.expression = expression;
		this.ifBody = ifBody;
		this.elseBody = null;
	}
	
	/**
	 * This constructor should not be used.
	 * 
	 * @throws IllegalStateException	Always
	 * 									| true
	 */
	public IfThenElseStatement(){
		super(null);
		throw new IllegalStateException("Cannot create an IfThenElseStatement withouth an expression and ifBody!");
	}
	
	/**
	 * Get the condition of this IfThenElseStatement.
	 */
	public Expression<Boolean> getExpression(){
		return this.expression;
	}
	
	/**
	 * Variable registering the expression that comes after the if statement
	 */
	private final Expression<Boolean> expression;	

	/**
	 * Get the body that belongs to the if statement of
	 * this IfThenElseStatement.
	 */
	public Statement getIfBody(){
		return this.ifBody;
	}
	
	/**
	 * Variable registering the body that belongs to the if statement.
	 */
	private final Statement ifBody;

	/**
	 * Get the body that belongs to the else statement.
	 * 
	 * Returns null if this IfThenElse Statement does not have
	 * an else statement.
	 */
	public Statement getElseBody(){
		return this.elseBody;
	}
	/**
	 * Variable registering the body that belongs to the else statement.
	 */
	private final Statement elseBody;
	
	@Override
	public void execute(Program parentProgram){
		// If this IfThenElseStatement has already been executed, we don't have to do anything.
		if (isFinished())
			return;
		
		if (getExpression().eval(parentProgram).getValue(parentProgram))
			getIfBody().execute(parentProgram);
		
		else if (getElseBody() != null)
			getElseBody().execute(parentProgram);
		
		// If at this stage the parentProgram isn't paused, then this IfThenElseStatement was executed successfully.
		if (!(parentProgram.isPaused()))
			setFinished(true);
	}
	
	@Override
	public void reset(){
		// Reset this IfThenElseStatement itself
		super.reset();
		
		// Reset the ifBody and elseBody (if any)
		getIfBody().reset();
		
		if (!(getElseBody() == null))
			getElseBody().reset();
	}
}
