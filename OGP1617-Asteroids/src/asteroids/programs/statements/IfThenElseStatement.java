package asteroids.programs.statements;

import asteroids.programs.expressions.Expression;

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
	public IfThenElseStatement(Expression expression, Statement ifBody, Statement elseBody)
					throws NullPointerException{
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
	 * 			If the given expression or ifBody is null
	 * 			| if (expression == null || ifBody == null)
	 */
	public IfThenElseStatement(Expression expression, Statement ifBody) throws NullPointerException{
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
		throw new IllegalStateException("Cannot create an IfThenElseStatement withouth an expression and ifBody!");
	}
	/**
	 * Get the expression that comes after the
	 * if statement
	 */
	public Expression getExpression(){
		return this.expression;
	}
	
	/**
	 * Variable registering the expression that comes after the if statement
	 */
	private final Expression expression;	

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
	
	public Object execute(){
		if ((boolean)getExpression().eval())
			getIfBody().execute();
		
		if (getElseBody() != null)
			getElseBody().execute();
		
		return null;
	}
}
