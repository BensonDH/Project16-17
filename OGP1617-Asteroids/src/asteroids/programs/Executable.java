package asteroids.programs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import asteroids.model.Ship;
import asteroids.programs.exceptions.IllegalTypeException;
import asteroids.programs.exceptions.VariableException;
import asteroids.programs.expressions.Literal;
import asteroids.programs.statements.Statement;
import asteroids.programs.statements.WhileStatement;

public abstract class Executable {
	
	public Executable(Statement body){
		this.body = body;
	}
	
	/**
	 * Get the body of this Program.
	 */
	public Statement getBody(){
		return this.body;
	}
	
	/**
	 * Variable representing the body of this Executable object.
	 */
	private final Statement body;
	
	public abstract Ship getAssociatedShip();
	
	public abstract Function findDefinedFunction(String functionName);
	/**
	 * Add a variable that exist during the runtime of this program.
	 */
	public void addVariable(String varName, Literal<?> variable) throws IllegalArgumentException {		
		if (!isValidVariableName(varName))
			throw new VariableException(varName);
		// If the variable is already present in this map, we have to check that the new variable's type
		// is compatible with the old variable's type.
		if (runTimeVariables.containsKey(varName))
			if (!(runTimeVariables.get(varName).getReturnType() == variable.getReturnType()))
					throw new IllegalTypeException(runTimeVariables.get(varName).getReturnType(), variable.getReturnType());
		
		runTimeVariables.put(varName, variable);

	}
	
	/**
	 * Return the variable within this program with the given variableName.
	 * 
	 * Returns null if no such variable exists.
	 */
	public Literal<?> findVariable(String variableName){
		return this.runTimeVariables.get(variableName);
	}
	
	/**
	 * Get variables that are present in this
	 * program at this time.
	 */
	public Map<String, Literal<?>> getAllVariables(){
		return this.runTimeVariables;
	}
	/**
	 * List containing all the variables that are present during
	 * execution of this Program.
	 */
	private Map<String, Literal<?>> runTimeVariables= new HashMap<String, Literal<?>>();

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
	 * 
	 * Returns null if there is no active loop.
	 */
	public WhileStatement getLastActiveLoop(){
		if (this.activeLoops.size() == 0)
			return null;
		
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
	
	public abstract boolean isValidVariableName(String varName);
	
	public static List<String> getInvalidVariableNames(){
		return Executable.invalidVariableNames;
	}
	
	private static List<String> invalidVariableNames = Arrays.asList("while","break","return","if","else","print","turn","fire","thrust_on","thrust_off","skip",
													"def","sqrt","null", "self", "getx", "gety", "getvx", "getvy","getradius","getdir",
													"ship","asteroid","planetoid","bullet","planet","any", "true", "false");

}
