package asteroids.programs.expressions;

import asteroids.part3.programs.SourceLocation;
import asteroids.programs.*;
import asteroids.programs.exceptions.SyntaxException;

public class ReadParameterExpression extends Expression<Object> {

	public ReadParameterExpression(String paremeterName, SourceLocation sourceLocation) {
		super(Object.class, sourceLocation);
		this.parameterName = paremeterName;
	}

	public String getParameterName(){
		return this.parameterName;
	}
	
	private final String parameterName;
	
	@Override
	public Literal<Object> eval(Executable parentExecutor) {
		if (!(parentExecutor instanceof Function))
			throw new SyntaxException("Syntax exception: Parameter invocation ouside of function body.");
		
		Literal<?> temp = ((Function)parentExecutor).findFunctionParameter(getParameterName());
		
		return (Literal<Object>) temp;
	}

}
