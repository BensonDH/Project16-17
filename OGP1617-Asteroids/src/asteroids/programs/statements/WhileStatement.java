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
	public WhileStatement(Expression expression, Statement body,
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
	public Expression getExpression(){
		return this.expression;
	}
	
	/**
	 * Change the while statement's expression to the given newExpression.
	 */
	public void changeExpression(Expression newExpression){
		if (!(newExpression instanceof ReturnTypeBoolean))
			throw new IllegalTypeException(ReturnTypeBoolean.class, newExpression.getClass());
		
		this.expression = newExpression;
	}
	
	/**
	 * The expression that has to be evaluated in every cycle of
	 * this while statement.
	 */
	private Expression expression;
	
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
	public void execute(Program parentProgram){
		// If this WhilseStatement has already been executed, we don't have to do anything.
		if (isFinished())
			return;
	
		// First we add this whileLoop as a new activeLoop to the program
		parentProgram.addActiveLoop(this);
		
		Literal evaluatedExpression = getExpression().eval(parentProgram); 
		if (!(evaluatedExpression instanceof BooleanLiteralExpression))
			throw new IllegalTypeException(BooleanLiteralExpression.class, evaluatedExpression.getClass());
		
		// A break statement might terminate this while loop
		while (((BooleanLiteralExpression)evaluatedExpression).getValue(parentProgram) && !isTerminated()){
			getBody().execute(parentProgram);
			
			// we re-evaluate this WhileStatement's expression so it stays up-to-date
			evaluatedExpression = getExpression().eval(parentProgram);
			
			// If the parentProgram was paused by the WhileStatement's body, we don't have to do anything anymore
			if (parentProgram.isPaused()){
				break;
			}
			// If we have to do another loop in this WhileStatement, we have to reset the WhileStatement's body again.
			else if (((BooleanLiteralExpression)evaluatedExpression).getValue(parentProgram) && !isTerminated())
				getBody().reset();
		}
		
		// If the parentProgram is not paused, this WhileStatement was fully executed.
		if (!(parentProgram.isPaused()))
			setFinished(true);
		
		parentProgram.deleteActiveLoop(this);
	}
	
	/**
	 * Terminate this whileStatement.
	 */
	public void terminate(){
		this.isTerminated = true;
	}
	
	/**
	 * Check whether this while statement is terminated.
	 */
	public boolean isTerminated(){
		return this.isTerminated;
	}
	/**
	 * A variable registering whether this while statement is terminated.
	 */
	private boolean isTerminated = false;
	
	@Override
	public void reset(){
		// Reset the WhileStatement itself
		super.reset();
		this.isTerminated = false;
		
		// Reset the WhileStatement's body
		getBody().reset();
	}
}
