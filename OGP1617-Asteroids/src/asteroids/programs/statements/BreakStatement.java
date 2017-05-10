package asteroids.programs.statements;

import java.util.List;

import asteroids.programs.Function;
import asteroids.programs.Program;

public class BreakStatement extends Statement {

	/**
	 * Initialize this BreakStatement.
	 * A BreakStatement can only occur in a WhileStatement's body.
	 * 
	 * @param rootWhileStatement
	 * 			The WhileStatement where this BreakStatement belong to.
	 * @throws NullPointerException
	 *			If the given rootWhileStatement is null
	 *			| if (rootWhileStatement == null)
	 */
	public BreakStatement(WhileStatement rootWhileStatement) throws NullPointerException{
		if (rootWhileStatement == null)
			throw new NullPointerException("rootWhileStatement cannot be null.");
		this.rootWhileStatement = rootWhileStatement;
	}
	
	
	/**
	 * Get the root while statement where this BreakStatement
	 * belongs to.
	 */
	public WhileStatement getRootWhileStatement(){
		return this.rootWhileStatement;
	}
	
	/**
	 * Variable registering the WhileStatement where this 
	 * BreakStatement occurs.
	 */
	private final WhileStatement rootWhileStatement;
	
	/**
	 * Execute this BreakStatement by terminating its rootWhileStatement.
	 */
	public void execute(Program parentProgram){
		getRootWhileStatement().terminate();
	}
}
