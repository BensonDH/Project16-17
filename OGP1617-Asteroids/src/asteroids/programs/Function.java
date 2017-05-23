package asteroids.programs;

import java.util.*;

import asteroids.programs.statements.*;
import asteroids.model.Ship;
import asteroids.part3.programs.SourceLocation;
import asteroids.programs.expressions.*;


/**
 * A class representing a function definition
 */
public class Function extends Executable implements Cloneable {
	
	/**
	 * Create a new Function with the given functionName and body.
	 * @param functionName
	 * 			The name of this function.
	 * @param body
	 * 			The body of this function.
	 */
	public Function(String functionName, Statement body, SourceLocation sourceLocation){
		super(body);
		this.functionName = functionName;
		this.sourceLocation = sourceLocation;
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
	 * Get the location where this function is located in the program.
	 */
	public SourceLocation getSourceLocation(){
		return this.sourceLocation;
	}
	
	/**
	 * Variable registering where this function is located in the program.
	 */
	private SourceLocation sourceLocation;

	public void initializeFunctionParameters(List<Expression> parameterList, Executable existingRunTime){
		int counter = 1;
		Map<String, Literal<?>> newParameters = new HashMap<String, Literal<?>>();
		
		for (Expression<?> parameter: parameterList){
			// We have to evaluate the parameter with the given existingRunTime, because
			// the evaluation of the parameter might call a variable that was defined in the
			// previous runTime.
			Literal<?> newPar = parameter.eval(existingRunTime);
			newParameters.put("$"+counter, newPar);
			counter +=1;
		}
		this.parameters = newParameters;
	}
	
	/**
	 * Returns the parameter present in this function
	 * 
	 * Returns null if no such parameter is defined.
	 */
	public Literal<?> findFunctionParameter(String parameterName){
		return parameters.get(parameterName);
	}
	
	private Map<String, Literal<?>> parameters = new HashMap<String, Literal<?>>();
	
	/**
	 * Returns a function with the given functionName that is defined within the program that 
	 * executes this function.
	 * Returns null if no such function exists.
	 */
	@Override
	public Function findDefinedFunction(String functionName){
		if (getAssociatedProgram() == null)
			return null;
		
		return getAssociatedProgram().findDefinedFunction(functionName);
	}
	
	public void execute(){
		// Reset this Function before we start
		reset();
		
		// execute the function's body
		getBody().execute(this);
	}
	
	public void setFunctionReturnValue(Literal<Object> value){
		this.returnValue = value;
	}
	
	public Literal<Object> getFunctionReturnValue(){
		return this.returnValue;
	}
	
	private Literal<Object> returnValue;

	@Override
	public Ship getAssociatedShip(){
		return getAssociatedProgram().getAssociatedShip();
	}
	
	public void setAssociatedProgram(Program associatedProgram) {
		this.associatedProgram = associatedProgram;
	}

	public Program getAssociatedProgram(){
		return this.associatedProgram;
	}
	
	private Program associatedProgram;
	
	@Override
	public WhileStatement getLastActiveLoop(){
		if (super.getLastActiveLoop() == null) {
			if (getAssociatedProgram() == null)
				return null;
			else
				return getAssociatedProgram().getLastActiveLoop();
		}
		return super.getLastActiveLoop();
	}
	
	
	/**
	 * Check whether this function has been terminated.
	 */
	public boolean isTerminated(){
		return this.terminated;
	}
	
	public void reset(){
		// Reset the Function itself
		this.terminated = false;
		this.returnValue = null;
		
		// Reset the Function's body
		getBody().reset();
	}
	
	/**
	 * Terminate this Function.
	 */
	public void terminate(){
		// Terminate the Function itself
		this.terminated = true;
		
		// Terminate the Function's body
		getBody().terminate();
	}
	
	/**
	 * Variable registering whether this function has been terminated.
	 */
	private boolean terminated = false;
	
	@Override
	public void addVariable(String varName, Literal<?> variable){
		// The variable is a local variable (it does not exist in the parentProgram)
		if (getAssociatedProgram().findVariable(varName) == null)
			super.addVariable(varName, variable);
		// it's a global variable, overwrite it in the executing program.
		else
			getAssociatedProgram().addVariable(varName, variable);
	}
	
	@Override
	public Literal<?> findVariable(String variableName){
		// First We check if there is a local variable with the given name
		if (!(super.findVariable(variableName) == null))
			return super.findVariable(variableName);
		// The searched variable might be a global one 
		else
			return getAssociatedProgram().findVariable(variableName);
	}
	
	@Override
	public Function clone(){
		Function newFunction = new Function(getFunctionName(), getBody().clone(), getSourceLocation());
		newFunction.setAssociatedProgram(getAssociatedProgram());
		
		return newFunction;
	}
	
	public boolean isValidVariableName(String varName){
		return (!(getInvalidVariableNames().contains(varName) || varName == getFunctionName()));
	}

}
