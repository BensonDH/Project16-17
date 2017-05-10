package asteroids.programs.statements;

import java.util.*;

import asteroids.programs.*;

public class BlockStatement extends Statement {

	/**
	 * Create a blockStatement with the given statements.
	 */
	public BlockStatement(List<Statement> statements){
		this.statements = statements;
	}
	
	/**
	 * This constructor should not be used.
	 * 
	 * @throws IllegalStateException
	 * 			Always
	 * 			| true
	 */
	public BlockStatement(){
		throw new IllegalStateException("Cannot initialize an empty block statement");
	}
	
	/**
	 * A list representing the statements that this BlockStatement holds.
	 */
	private List<Statement> statements;
	
	@Override
	public void execute(Program parentProgram) {
		for (Statement statement: statements){
			statement.execute(parentProgram);
		}
	}

}
