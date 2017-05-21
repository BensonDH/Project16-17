package asteroids.programs.expressions;

import asteroids.part3.programs.SourceLocation;
import asteroids.programs.Program;

public abstract class Literal extends Expression {

	public Literal(SourceLocation sourceLocation) {
		super(sourceLocation);
	}

	/**
	 * Get the value of this Literal.
	 */
	public abstract Object getValue(Program parentProgram);
	
	/**
	 * Get the type that this Literal represents.
	 */
	public abstract Class<?> getLiteralType();

}
