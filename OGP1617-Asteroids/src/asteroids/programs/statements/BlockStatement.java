package asteroids.programs.statements;

import java.util.*;

import asteroids.part3.programs.SourceLocation;
import asteroids.programs.*;

public class BlockStatement extends Statement {

	/**
	 * Create a blockStatement with the given statements.
	 */
	public BlockStatement(List<Statement> statements, SourceLocation sourceLocation){
		super(sourceLocation);
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
		super(null);
	}
	
	/**
	 * A list representing the statements that this BlockStatement holds.
	 */
	private List<Statement> statements;
	
	@Override
	public void execute(Executable parentExecutor) {
		// If this BlockStatement had already been executed, we can skip this.
		if (isFinished())
			return;
		
		for (Statement statement: statements){
			statement.execute(parentExecutor);
			
			// If the parentProgram was paused by this statement, we don't have to do anything anymore.
			if (parentExecutor.isPaused())
				return;
		}
		
		// If we executed all the statements within this BlockStatement and the
		// parentProgram was not paused, then this BlockStatement is executed successfully.
		if (!(parentExecutor.isPaused()))
			super.terminate();
	}	
	
	@Override
	public void reset(){
		// Reset this blockStatement itself
		super.reset();
		
		// Reset all the statements within this blockStatement
		for (Statement statement: statements){
			statement.reset();
		}
	}
	
	@Override
	public void terminate(){
		// Terminate the block statement itself
		super.terminate();
		
		// terminate all the statements within this blockStatement
		for (Statement statement: statements) {
			statement.terminate();
		}
	}
	
	@Override
	public Statement clone(){
		List<Statement> newStatements = new ArrayList<Statement>();
		for (Statement statement: statements){
			newStatements.add(statement.clone());
		}
		
		return new BlockStatement(newStatements, getSourceLocation());
	}
}
