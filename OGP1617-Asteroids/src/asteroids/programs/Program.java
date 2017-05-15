package asteroids.programs;

import java.util.*;

import asteroids.model.Ship;
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
		getBody().execute(this);
		
		return getPrintedValues();
	}
	
	/**
	 * Add global variables that exist during the runtime of this program.
	 */
	public void addGlobalVariables(Expression... variables) throws IllegalArgumentException {
		for (Expression variable: variables){
			if (!(variable instanceof Variable))
				throw new IllegalArgumentException("The given expression is not a variable.");
			runTimeVariables.put(((Variable)variable).getName(), variable);
		}
	}
	
	/**
	 * Get the global variables that are present in this
	 * program at this time.
	 */
	public Map<String, Expression> getGlobalVariables(){
		return this.runTimeVariables;
	}
	/**
	 * List containing all the global variables that are present during
	 * execution of this Program.
	 */
	private Map<String, Expression> runTimeVariables= new HashMap<String, Expression>();
	
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
	 * Remove the loop positioned at the end of the activeLoop array.
	 */
	public void deleteActiveLoop(WhileStatement loop){
		activeLoops.remove(loop);
	}
	
	/**
	 * Add a new active loop to this program.
	 * The given newLoop will be added at the end of the ActiveLoops list.
	 */
	public void addActiveLoop(WhileStatement newLoop){
		activeLoops.add(newLoop);
	}
	
	/**
	 * Returns the last active loop in the activeLoops list.
	 */
	public WhileStatement getLastActiveLoop(){
		return this.activeLoops.get(this.activeLoops.size()-1);
	}
	
	/**
	 * Get the loops that are currently active within this program
	 */
	public List<WhileStatement> getActiveLoops(){
		return this.activeLoops;
	}
	
	/**
	 * A list keeping up the loops that are present right now.
	 * This is used so that the break statements can find their parent
	 * WhileStatement efficiently.
	 */
	private List<WhileStatement> activeLoops = new ArrayList<WhileStatement>();

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
