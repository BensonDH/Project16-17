package asteroids.programs.statements;

import asteroids.programs.expressions.Expression;


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
	public WhileStatement(Expression expression, Statement body) throws NullPointerException{
		if (expression == null || body == null)
			throw new NullPointerException();
		this.expression = expression;
		this.body = body;
	}
	
	/**
	 * This constructor should not be used.
	 * @throws IllegalStateException	Always
	 * 									| true
	 */
	public WhileStatement() throws IllegalStateException{
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
	 * The expression that has to be evaluated in every cycle of
	 * this while statement.
	 */
	private final Expression expression;
	
	/**
	 * Get the body of this while statement represented
	 * by a statement
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
	public Object execute(){
		while ((boolean)getExpression().eval() && isTerminated()){
			getBody().execute();
		}
		return null;
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
}
