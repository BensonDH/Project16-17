package asteroids.programs.expressions;

import asteroids.part3.programs.SourceLocation;
import asteroids.programs.Program;

public class NullExpression extends Literal {

	public NullExpression(SourceLocation sourceLocation) {
		super(sourceLocation);
	}

	@Override
	public Object getValue(Program parentProgram) {
		return null;
	}

	@Override
	public Class<?> getLiteralType() {
		return null;
	}

	@Override
	public Literal eval(Program parentProgram) {
		return this;
	}

}
