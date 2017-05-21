package asteroids.programs.statements;

import asteroids.part3.programs.SourceLocation;
import asteroids.programs.*;
import asteroids.programs.expressions.*;


public class AssignmentStatement extends Statement {
	
	
	/**
	 * Create a new AssignmentStatement with the given variable name, value and sourceLocation.
	 * An assignment statement is represented as:
	 * 	variableName := value
	 * 
	 * @param variableName
	 * 			The left hand side of the assignment, representing the name of the variable.
	 * @param value
	 * 			The right hand side of the assignment, representing the value of the variable.
	 * @param sourceLocation
	 * 			The location of this assignmentStatement in the Program.
	 */
	public AssignmentStatement(String variableName, Expression<?> value, SourceLocation sourceLocation){
		super(sourceLocation);
		this.variableName = variableName;
		this.assignmentValue = value;
	}
	
	/**
	 * This constructor should not be used.
	 * 
	 * @throws IllegalStateException
	 * 			Always
	 * 			| true
	 */
	public AssignmentStatement(){
		super(null);
		throw new IllegalStateException("Cannot create an AssignmentStatement without any parameters.");
	}
	
	/**
	 * Get the variable name of this assignment, also known as
	 * the left hand side of this assignment statement.	
	 */
	public String getVariableName(){
		return this.variableName;
	}
	
	/**
	 * Variable registering the name of the variable of this assignment,
	 * also known as the left hand side of this assignment.
	 */
	private final String variableName;
	
	/**
	 * Get the value, also known as the right hand side of this assignment.
	 */
	public Expression<?> getValue(){
		return this.assignmentValue;
	}
	
	/**
	 * Variable registering the value of the variable of this assignment,
	 * also known as the right hand side of this assignment.
	 */
	private final Expression<?> assignmentValue;

	@Override
	public void execute(Program parentProgram) {
		// If this AssignmentStatement had already been executed, we can skip this.
		if (isFinished())
			return;
		
		Literal<?> evaluatedExpression = getValue().eval(parentProgram);
		Variable<Literal<?>> newVariable = new Variable<Literal<?>>(getVariableName(), evaluatedExpression);
		
		parentProgram.addGlobalVariable(newVariable);
		
		// If we made it this far, this Statement is finished.
		setFinished(true);
	}
}
