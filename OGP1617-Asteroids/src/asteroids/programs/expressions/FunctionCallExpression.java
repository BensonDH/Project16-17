package asteroids.programs.expressions;

import java.util.List;

import asteroids.part3.programs.SourceLocation;
import asteroids.programs.*;
import asteroids.programs.exceptions.*;

public class FunctionCallExpression extends Expression<Object> {

	public FunctionCallExpression(String functionName, List<Expression> actualArgs, SourceLocation sourceLocation) {
		super(Object.class, sourceLocation);
		this.invocatedFunctionName=functionName;
		this.actualArgs = actualArgs;
	}
	
	public String getFunctionName(){
		return this.invocatedFunctionName;
	}
	
	private final String invocatedFunctionName; 
	
	public List<Expression> getActualArgs(){
		return this.actualArgs;
	}
	
	private final List<Expression> actualArgs;
	
	@Override
	public Literal<Object> eval(Executable parentExecutor) {
		// We create a copy of the function, so that recursive functions are supported
		Function calledFunction = parentExecutor.findDefinedFunction(getFunctionName()).clone();
		if (calledFunction == null)
			throw new VariableException(getFunctionName());
		
		calledFunction.initializeFunctionParameters(getActualArgs(), parentExecutor);
		calledFunction.execute();
		
		if (calledFunction.getFunctionReturnValue() == null)
			throw new SyntaxException("Syntax exception: Missing return statement at the end of a function.");
		
		return calledFunction.getFunctionReturnValue(); 
		
	}

}
