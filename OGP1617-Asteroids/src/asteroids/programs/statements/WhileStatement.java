package asteroids.programs.statements;

import asteroids.part3.programs.SourceLocation;
import asteroids.programs.*;
import asteroids.programs.exceptions.IllegalTypeException;
import asteroids.programs.expressions.*;


public class WhileStatement extends Statement{
	
	/**
	 * Initialize a new While statement with the given expression
	 * and body.
	 * 
	 * This while statement will be represented as:
	 * 		 while (expression) {body}
	 * 
	 * Only this constructor should be used.
	 * @param expression
	 * 			The expression that has to be evaluated at the beginning
	 * 			of every new cycle in this while statement.
	 * @param body
	 * 			The body of this while statement.
	 * @throws	NullpointerException
	 * 			If expression or body is null
	 * 			| if (expression == null || body == null)
	 */
	public WhileStatement(Expression<Boolean> expression, Statement body,
						  SourceLocation sourceLocation) throws NullPointerException{
		super(sourceLocation);
		if (expression == null || body == null)
			throw new NullPointerException();
		
		changeExpression(expression);
		this.body = body;
	}
	
	/**
	 * This constructor should not be used.
	 * @throws IllegalStateException	Always
	 * 									| true
	 */
	public WhileStatement() throws IllegalStateException{
		super(null);
		throw new IllegalStateException("Cannot initialize a while statement without an expression and a body!");
	}

	/**
	 * Get the expression that has to be evaluated at the beginning
	 * of every new cycle of this while statement.
	 */
	public Expression<Boolean> getExpression(){
		return this.expression;
	}
	
	/**
	 * Change the while statement's expression to the given newExpression.
	 */
	public void changeExpression(Expression<Boolean> newExpression){
		this.expression = newExpression;
	}
	
	/**
	 * The expression that has to be evaluated in every cycle of
	 * this while statement.
	 */
	private Expression<Boolean> expression;
	
	/**
	 * Get the body of this while statement represented
	 * by a statement.
	 */
	public Statement getBody(){
		return this.body;
	}
	
	/**
	 * A Statement representing the body of this while statement.
	 */
	private final Statement body;
	
	/**
	 * Execute this while statement
	 */
	public void execute(Executable parentExecutor){
		// If this WhilseStatement has already been executed, we don't have to do anything.
		if (isFinished())
			return;
	
		// First we add this whileLoop as a new activeLoop to the program
		parentExecutor.addActiveLoop(this);
		
		// !! This might give an classCastException !!
		Literal<Boolean> evaluatedExpression = getExpression().eval(parentExecutor); 
		
		if (!(evaluatedExpression.getReturnType() == Boolean.class))
			throw new IllegalTypeException(Boolean.class, evaluatedExpression.getReturnType());
		
		while (evaluatedExpression.getValue(parentExecutor) && !isFinished()){
			getBody().execute(parentExecutor);
			
			// we re-evaluate this WhileStatement's expression so it stays up-to-date
			evaluatedExpression = getExpression().eval(parentExecutor);
			
			// If the parentProgram was paused during the execution of the WhileStatement's body, we don't have to do anything anymore
			if (parentExecutor.isPaused()){
				break;
			}
			// If we have to do another loop in this WhileStatement, we have to reset the WhileStatement's body again.
			else if (evaluatedExpression.getValue(parentExecutor) && !isFinished())
				getBody().reset();
		}
		
		// If the parentProgram is not paused, this WhileStatement was fully executed.
		if (!(parentExecutor.isPaused()))
			terminate();
		
		parentExecutor.deleteActiveLoop(this);
	}
	

	
	@Override
	public void reset(){
		// Reset the WhileStatement itself
		super.reset();
		
		// Reset the WhileStatement's body
		getBody().reset();
	}
	
	/**
	 * Terminate this whileStatement.
	 */
	@Override
	public void terminate(){
		// terminate the WhileStatement itself
		super.terminate();
		
		// terminate the WhileStatement's body
		getBody().terminate();
	}
	
	@Override
	public Statement clone(){
		return new WhileStatement(getExpression(), getBody().clone(), getSourceLocation());
	}
}
