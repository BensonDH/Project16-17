package asteroids.programs.expressions;

import asteroids.part3.programs.SourceLocation;
import asteroids.programs.Executable;

public class NullExpression extends Literal<Object> implements NullType{

	public NullExpression(SourceLocation sourceLocation) {
		super(null, sourceLocation);
	}

	@Override
	public Object getValue(Executable parentExecutor) {
		return null;
	}

	@Override
	public Literal<Object> eval(Executable parentExecutable) {
		return this;
	}

}
