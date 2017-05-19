package asteroids.programs.statements;


import asteroids.part3.programs.SourceLocation;
import asteroids.programs.*;
import asteroids.programs.exceptions.SyntaxException;


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
	public BreakStatement(SourceLocation sourceLocation) throws NullPointerException{
		super(sourceLocation);
	}
	
	/**
	 * This constructor should not be used
	 * @throws IllegalStateException
	 * 			Always
	 * 			| true
	 */
	public BreakStatement(){
		super(null);
		throw new IllegalStateException();
	}
	
	/**
	 * Execute this BreakStatement by terminating its rootWhileStatement.
	 */
	public void execute(Program parentProgram){
		// if this BreakStatement has already been executed, we don't have to do anything.
		if (isFinished())
			return;
		
		// All we have to do is terminate the last loop in the activeLoop list of the given program.
		WhileStatement lastActiveLoop = parentProgram.getLastActiveLoop();
		if (lastActiveLoop == null){
			assert (parentProgram.getActiveLoops().size() == 0);
			throw new SyntaxException("Syntax error: break statement not in a while statement.");
		}
		lastActiveLoop.terminate();
		
		// This BreakStatement is finished
		setFinished(true);
	}
}
