package asteroids.programs;

import java.util.*;

import asteroids.model.Ship;
import asteroids.programs.exceptions.IllegalTypeException;
import asteroids.programs.exceptions.VariableException;
import asteroids.programs.expressions.*;
import asteroids.programs.statements.*;

public class Program extends Executable {
	
	/**
	 * Create a new program with the given functions and body.
	 */
	public Program(List<Function> functions, Statement body){
		super(body);
		addFunctions(functions);
	}

	/**
	 * Get all the functions that are defined within this Program.
	 */
	public List<Function> getFunctions(){
		return new ArrayList<Function>(functions.values());
	}
	
	private void addFunctions(List<Function> programFunctions){
		for (Function function: programFunctions){
			function.setAssociatedProgram(this);
			functions.put(function.getFunctionName(), function);
		}
	}
	
	/**
	 * Returns a function with the given functionName that is defined within this program
	 * 
	 * Returns null if no such function exists.
	 */
	@Override
	public Function findDefinedFunction(String functionName){
		return functions.get(functionName);
	}
	
	/**
	 * A List containing all the functions defined in this Program
	 */
	private Map<String, Function> functions= new HashMap<String, Function>();
	
	/**
	 * Set the associated ship of this program to the given ship.
	 */
	public void setAssociatedShip(Ship ship){
		this.associatedShip = ship;
	}
	
	/**
	 * Return the ship that has this program loaded.
	 */
	public Ship getAssociatedShip(){
		return this.associatedShip;
	}
	
	/**
	 * Variable registering the ship that has this program loaded.
	 */
	private Ship associatedShip;
	
	/**
	 * Execute this program.
	 * 
	 * @param executionTime
	 * 			The time that this program has to execute.
	 * @return
	 * 			All the values that were printed during execution of this program.
	 */
	public List<Object> execute(){
		// if this program was paused, resume it again
		if (isPaused())
			resume();
		
		getBody().execute(this);
		
		// If this program isn't paused, then it was executed successfully, meaning we can
		// reset it's body again for further use.
		if (!isPaused()){
			getBody().reset();
			return getPrintedValues();
		}
		// Else the program has been paused so we don't return anything.
		else 
			return null;
	}
	

	
	/**
	 * Get the time that this program has left to run.
	 */
	public double getExecutionTime(){
		return this.executionTimeLeft;
	}
	
	/**
	 * Set the ExecutionTimeLeft of this program to the given value.
	 */
	public void addExecutionTime(double time){
		this.executionTimeLeft += time;
	}
	
	/**
	 * Variable registering the time this program has left to run.
	 */
	private double executionTimeLeft = 0;
	

	/**
	 * Add a value that was printed during this program's execution.
	 */
	public void addPrintedValues(Object... values){
		for (Object value: values){
			printedValues.add(value);
		}
	}
	
	/**
	 * Get all the values that were printed until now during this program's execution. 
	 */
	public List<Object> getPrintedValues(){
		return this.printedValues;
	}
	
	/**
	 * A List containing all the values that were printed.
	 */
	private List<Object> printedValues = new ArrayList<Object>();
	
	public boolean isValidVariableName(String toBeChecked){
		return (toBeChecked.matches("[a-zA-Z0-9]*") && 
				!Executable.getInvalidVariableNames().contains(toBeChecked) &&
				findDefinedFunction(toBeChecked) == null);
	}
}
