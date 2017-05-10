package asteroids.programs;

import java.util.*;

import asteroids.programs.expressions.*;
import asteroids.programs.statements.*;

public class Program {
	
	/**
	 * Create a new program with the given functions and body.
	 */
	public Program(List<Function> functions, Statement body){
		this.body = body;
		this.functions = functions;
	}
	
	/**
	 * Execute this program.
	 * 
	 * @param executionTime
	 * 			The time that this program has to execute.
	 * @return
	 * 			All the values that were printed during execution of this program.
	 */
	public List<Object> execute(double executionTime){
		setExecutionTimeLeft(executionTime);
		
		getBody().execute(this);
		
		return getPrintedValues();
	}
	
	/**
	 * Add global variables that exist during the runtime of this program.
	 */
	public void addGlobalVariables(Expression... variables){
		for (Expression variable: variables){
			runTimeVariables.add(variable);
		}
	}
	
	/**
	 * Get the global variables that are present in this
	 * program at this time.
	 */
	public List<Expression> getGlobalVariables(){
		return this.runTimeVariables;
	}
	/**
	 * List containing all the global variables that are present during
	 * execution of this Program.
	 */
	private List<Expression> runTimeVariables= new ArrayList<Expression>();
	
	/**
	 * Get the time that this program has left to run.
	 */
	public double getExecutionTimeLeft(){
		return this.executionTimeLeft;
	}
	
	/**
	 * Set the ExecutionTimeLeft of this program to the given value.
	 */
	public void setExecutionTimeLeft(double time){
		if ( 0 <= time)
			this.executionTimeLeft = time;
		else
			this.executionTimeLeft = 0;
	}
	
	/**
	 * Variable registering the time this program has left to run.
	 */
	private double executionTimeLeft = Double.POSITIVE_INFINITY;
	
	/**
	 * Get all the functions that are defined within this Program.
	 */
	public List<Function> getFunctions(){
		return this.functions;
	}
	
	/**
	 * A List containing all the functions defined in this Program
	 */
	private List<Function> functions;
	
	/**
	 * Get the body of this Program.
	 */
	public Statement getBody(){
		return this.body;
	}
	
	/**
	 * Variable representing the body of this Program.
	 */
	private Statement body;

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
	
	/**
	 * Resume this program.
	 */
	public void resume(){
		this.paused = false;
	}
	
	/**
	 * Pause this program
	 */
	public void pause(){
		this.paused = true;
	}
	
	/**
	 * Check whether this program is paused.
	 */
	public boolean isPaused() {
		return this.paused;
	}
	
	/**
	 * Variable registering whether this programs is paused.
	 */
	private boolean paused = false;
}
