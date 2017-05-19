package asteroids.programs;

import java.util.List;

import asteroids.model.Asteroid;
import asteroids.model.Bullet;
import asteroids.model.MinorPlanet;
import asteroids.model.Planetoid;
import asteroids.model.Ship;
import asteroids.part3.programs.IProgramFactory;
import asteroids.part3.programs.SourceLocation;
import asteroids.programs.expressions.*;
import asteroids.programs.statements.*;

public class ProgramFactory implements IProgramFactory<Expression, Statement, Function, Program> {

	@Override
	public Program createProgram(List<Function> functions, Statement main) {
		return new Program(functions, main);
	}

	@Override
	public Function createFunctionDefinition(String functionName, Statement body, SourceLocation sourceLocation) {
		return new Function(functionName, body, sourceLocation);
	}

	@Override
	public Statement createAssignmentStatement(String variableName, Expression value, SourceLocation sourceLocation) {
		return new AssignmentStatement(variableName, value, sourceLocation);
	}

	@Override
	public Statement createWhileStatement(Expression condition, Statement body, SourceLocation sourceLocation) {
		return new WhileStatement(condition, body, sourceLocation);
	}

	@Override
	public Statement createBreakStatement(SourceLocation sourceLocation) {
		return new BreakStatement(sourceLocation);
	}

	@Override
	public Statement createReturnStatement(Expression value, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Statement createIfStatement(Expression condition, Statement ifBody, Statement elseBody, SourceLocation sourceLocation) {
		return new IfThenElseStatement(condition, ifBody, elseBody, sourceLocation);
	}

	@Override
	public Statement createPrintStatement(Expression value, SourceLocation sourceLocation) {
		return new PrintStatement(value, sourceLocation);
	}

	@Override
	public Statement createSequenceStatement(List<Statement> statements, SourceLocation sourceLocation) {
		return new BlockStatement(statements, sourceLocation);
	}

	@Override
	public Expression createReadVariableExpression(String variableName, SourceLocation sourceLocation) {
		return new ReadVariableExpression(variableName, sourceLocation);
	}

	@Override
	public Expression createReadParameterExpression(String parameterName, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createFunctionCallExpression(String functionName, List<Expression> actualArgs, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createChangeSignExpression(Expression expression, SourceLocation sourceLocation) {
		return new ChangeSignExpression(expression, sourceLocation);
	}

	@Override
	public Expression createNotExpression(Expression expression, SourceLocation sourceLocation) {
		return new NotExpression(expression, sourceLocation);
	}

	@Override
	public Expression createDoubleLiteralExpression(double value, SourceLocation location) {
		return new DoubleLiteralExpression(Double.valueOf(value), location);
	}

	@Override
	public Expression createNullExpression(SourceLocation location) {
		return new NullExpression(location);
	}

	@Override
	public Expression createSelfExpression(SourceLocation location) {
		return new SelfExpression(location);
	}

	@Override
	public Expression createShipExpression(SourceLocation location) {
		return new EntityLiteralExpression<Ship>(Ship.class, location);
	}

	@Override
	public Expression createAsteroidExpression(SourceLocation location) {
		return new EntityLiteralExpression<Asteroid>(Asteroid.class, location);
	}

	@Override
	public Expression createPlanetoidExpression(SourceLocation location) {
		return new EntityLiteralExpression<Planetoid>(Planetoid.class, location);
	}

	@Override
	public Expression createBulletExpression(SourceLocation location) {
		return new EntityLiteralExpression<Bullet>(Bullet.class, location);
	}

	@Override
	public Expression createPlanetExpression(SourceLocation location) {
		return new EntityLiteralExpression<MinorPlanet>(MinorPlanet.class, location);
	}

	@Override
	public Expression createAnyExpression(SourceLocation location) {
		return new AnyExpression(location);
	}

	@Override
	public Expression createGetXExpression(Expression e, SourceLocation location) {
		return new GetXExpression(e, location);
	}

	@Override
	public Expression createGetYExpression(Expression e, SourceLocation location) {
		return new GetYExpression(e, location);
	}

	@Override
	public Expression createGetVXExpression(Expression e, SourceLocation location) {
		return new GetVXExpression(e, location);
	}

	@Override
	public Expression createGetVYExpression(Expression e, SourceLocation location) {
		return new GetVYExpression(e, location);
	}

	@Override
	public Expression createGetRadiusExpression(Expression e, SourceLocation location) {
		return new GetRadiusExpression(e, location);
	}

	@Override
	public Expression createLessThanExpression(Expression e1, Expression e2, SourceLocation location) {
		return new LessThanExpression(e1, e2, location);
	}

	@Override
	public Expression createEqualityExpression(Expression e1, Expression e2, SourceLocation location) {
		return new EqualityExpression(e1, e2, location);
	}

	@Override
	public Expression createAdditionExpression(Expression e1, Expression e2, SourceLocation location) {
		return new AdditionExpression(e1, e2, location);
	}

	@Override
	public Expression createMultiplicationExpression(Expression e1, Expression e2, SourceLocation location) {
		return new MultiplicationExpression(e1, e2, location);
	}

	@Override
	public Expression createSqrtExpression(Expression e, SourceLocation location) {
		return new SqrtExpression(e, location);
	}

	@Override
	public Expression createGetDirectionExpression(SourceLocation location) {
		return new GetDirectionExpression(location);
	}

	@Override
	public Statement createThrustOnStatement(SourceLocation location) {
		return new SetThrustStatement(true, location);
	}

	@Override
	public Statement createThrustOffStatement(SourceLocation location) {
		return new SetThrustStatement(false, location);
	}

	@Override
	public Statement createFireStatement(SourceLocation location) {
		return new FireBulletStatement(location);
	}

	@Override
	public Statement createTurnStatement(Expression angle, SourceLocation location) {
		return new TurnStatement(angle, location);
	}

	@Override
	public Statement createSkipStatement(SourceLocation location) {
		return new SkipStatement(location);
	}

}
