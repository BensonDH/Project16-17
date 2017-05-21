package asteroids.programs.expressions;

import asteroids.part3.programs.SourceLocation;
import asteroids.programs.Program;

public class NullExpression extends Literal<Object> implements NullType{

	public NullExpression(SourceLocation sourceLocation) {
		super(null, sourceLocation);
	}

	@Override
	public Object getValue(Program parentProgram) {
		return null;
	}

	@Override
	public Literal<Object> eval(Program parentProgram) {
		return this;
	}

}
