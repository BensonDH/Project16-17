package asteroids.programs;

import java.util.*;

import asteroids.programs.statements.*;
import asteroids.programs.expressions.*;


/**
 * A class representing a function definition
 */
public class Function {
	
	/**
	 * Create a new Function with the given functionName and body.
	 * @param functionName
	 * 			The name of this function.
	 * @param body
	 * 			The body of this function.
	 */
	public Function(String functionName, Statement body){
		this.functionName = functionName;
		this.functionBody = body;
	}

	/**
	 * This constructor should not be used.
	 * 
	 * @throws IllegalStateException
	 * 			Always
	 * 			| true
	 */
	public Function(){
		throw new IllegalStateException("Cannot create an empty function.");
	}
	
	/**
	 * Get the name of this function.
	 */
	public String getFunctionName(){
		return this.functionName;
	}
	
	/**
	 * A String representing the name of this Function.
	 */
	private final String functionName;

	/**
	 * Get the body of this function.
	 */
	public Statement getFunctionBody(){
		return this.functionBody;
	}
	
	/**
	 * A statement representing the body of this function.
	 */
	private Statement functionBody;
	
	/**
	 * Get the local variables that are initialized within this function
	 * at the time of invocation.
	 */
	public List<Expression> getFunctionVariables(){
		return this.variables;
	}
	/**
	 * A list representing the local variables used in this function.
	 */
	private List<Expression> variables = new ArrayList<Expression>();
	
	/**
	 * Check whether this function has been terminated.
	 */
	public boolean isTerminated(){
		return this.terminated;
	}
	
	/**
	 * Terminate this Function.
	 */
	public void terminate(){
		this.terminated = true;
	}
	
	/**
	 * Variable registering whether this function has been terminated.
	 */
	private boolean terminated = false;
}
