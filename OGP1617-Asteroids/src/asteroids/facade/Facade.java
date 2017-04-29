package asteroids.facade;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import asteroids.model.Asteroid;
import asteroids.model.Bullet;
import asteroids.model.Entity;
import asteroids.model.Planetoid;
import asteroids.model.Ship;
import asteroids.model.Vector;
import asteroids.model.World;
import asteroids.filters.*;

import asteroids.part2.CollisionListener;
import asteroids.part3.programs.IProgramFactory;
import asteroids.programs.Program;
import asteroids.util.ModelException;

public class Facade implements asteroids.part3.facade.IFacade{

	/**
	 * Create a new ship with a default position, velocity, radius and
	 * direction.
	 * 
	 * Result is a unit circle centered on <code>(0, 0)</code> facing right. Its
	 * speed is zero.
	 */
	public Ship createShip() throws ModelException {
		try{
		return new Ship();
		
		} catch (IllegalArgumentException E){
			throw new ModelException("IllegalArgumentException");
		} catch (AssertionError E){
			throw new ModelException("AssertionError");
		}	catch (NullPointerException E){
			throw new ModelException("NullPointerException");
		} catch (IllegalStateException E){
			throw new ModelException("IllegalStateException");}
	}

	/**
	 * Create a new ship with the given position, velocity, radius and
	 * orientation (in radians).
	 */
	public Ship createShip(double x, double y, double xVelocity, double yVelocity, double radius, double orientation)
			throws ModelException {
		try {
			return new Ship(x, y, xVelocity, yVelocity, radius, orientation);
		} catch (IllegalArgumentException E) {
			throw new ModelException("IllegalArgumentException");
		} catch (AssertionError E){
			throw new ModelException("AssertionError");
		} catch (NullPointerException E){
			throw new ModelException("NullPointerException");
		} catch (IllegalStateException E){
			throw new ModelException("IllegalStateException");}
	}

	/**
	 * Return the position of ship as an array of length 2, with the
	 * x-coordinate at index 0 and the y-coordinate at index 1.
	 */
	public double[] getShipPosition(Ship ship) throws ModelException {
		try {
		return ship.getPosition().toDoubleArray();
		
		} catch (IllegalArgumentException E){
			throw new ModelException("IllegalArgumentException");
		} catch (AssertionError E){
			throw new ModelException("AssertionError");
		} catch (NullPointerException E){
			throw new ModelException("NullPointerException");
		} catch (IllegalStateException E){
			throw new ModelException("IllegalStateException");}
	}

	/**
	 * Return the velocity of ship as an array of length 2, with the velocity
	 * along the X-axis at index 0 and the velocity along the Y-axis at index 1.
	 */
	public double[] getShipVelocity(Ship ship) throws ModelException {
		try {
		return ship.getVelocity().toDoubleArray();
		} catch (IllegalArgumentException E){
			throw new ModelException("IllegalArgumentException");
		} catch (AssertionError E){
			throw new ModelException("AssertionError");
		} catch (NullPointerException E){
			throw new ModelException("NullPointerException");
		} catch (IllegalStateException E){
			throw new ModelException("IllegalStateException");}
	}

	/**
	 * Return the radius of ship.
	 */
	public double getShipRadius(Ship ship) throws ModelException {
		try {
		return ship.getRadius();
		
		} catch (IllegalArgumentException E){
			throw new ModelException("IllegalArgumentException");
		} catch (AssertionError E){
			throw new ModelException("AssertionError");
		} catch (NullPointerException E){
			throw new ModelException("NullPointerException");
		} catch (IllegalStateException E){
			throw new ModelException("IllegalStateException");}
	}

	/**
	 * Return the orientation of ship (in radians).
	 */
	public double getShipOrientation(Ship ship) throws ModelException {
		try {
		return ship.getAngle();
		
		} catch (IllegalArgumentException E){
			throw new ModelException("IllegalArgumentException");
		} catch (AssertionError E){
			throw new ModelException("AssertionError");
		} catch (NullPointerException E){
			throw new ModelException("NullPointerException");
		} catch (IllegalStateException E){
			throw new ModelException("IllegalStateException");}
	}

	/**
	 * Update ship's position, assuming it moves dt
	 * seconds at its current velocity.
	 */
	public void move(Ship ship, double dt) throws ModelException {
		try {
		ship.move(dt);
		
		} catch (IllegalArgumentException E){
			throw new ModelException("IllegalArgumentException");
		} catch (AssertionError E){
			throw new ModelException("AssertionError");
		} catch (NullPointerException E){
			throw new ModelException("NullPointerException");
		} catch (IllegalStateException E){
			throw new ModelException("IllegalStateException");}
	}

	/**
	 * Update ship's velocity based on its current velocity, its
	 * direction and the given amount.
	 */
	public void thrust(Ship ship, double amount) throws ModelException {
		try {
		ship.thrust(amount);
		
		} catch (IllegalArgumentException E){
			throw new ModelException("IllegalArgumentException");
		} catch (AssertionError E){
			throw new ModelException("AssertionError");
		} catch (NullPointerException E){
			throw new ModelException("NullPointerException");
		} catch (IllegalStateException E){
			throw new ModelException("IllegalStateException");}
	}
	
	/**
	 * update ship's velocity to cancel all movement, by setting the velocity to zero.
	 */
	public void killVelocity(Ship ship) throws ModelException {
		try {
		ship.killVelocity();
		
		} catch (IllegalArgumentException E){
			throw new ModelException("IllegalArgumentException");
		} catch (AssertionError E){
			throw new ModelException("AssertionError");
		} catch (NullPointerException E){
			throw new ModelException("NullPointerException");
		} catch (IllegalStateException E){
			throw new ModelException("IllegalStateException");}
	}
	
	/**
	 * Update the direction of ship by adding angle
	 * (in radians) to its current direction. angle may be
	 * negative.
	 */
	public void turn(Ship ship, double angle) throws ModelException {
		try {
		ship.turn(angle);
		
		} catch (IllegalArgumentException E){
			throw new ModelException("IllegalArgumentException");
		} catch (AssertionError E){
			throw new ModelException("AssertionError");
		} catch (NullPointerException E){
			throw new ModelException("NullPointerException");
		} catch (IllegalStateException E){
			throw new ModelException("IllegalStateException");}
	}

	/**
	 * Return the distance between ship1 and ship2.
	 * 
	 * The absolute value of the result of this method is the minimum distance
	 * either ship should move such that both ships are adjacent. Note that the
	 * result must be negative if the ships overlap. The distance between a ship
	 * and itself is 0.
	 */
	public double getDistanceBetween(Ship ship1, Ship ship2) throws ModelException {
		try {
		return ship1.getDistanceBetween(ship2);
		
		} catch (IllegalArgumentException E){
			throw new ModelException("IllegalArgumentException");
		} catch (AssertionError E){
			throw new ModelException("AssertionError");
		} catch (NullPointerException E){
			throw new ModelException("NullPointerError");
		} catch (IllegalStateException E){
			throw new ModelException("IllegalStateException");}
	}

	/**
	 * Check whether ship1 and ship2 overlap. A ship
	 * always overlaps with itself.
	 */
	public boolean overlap(Ship ship1, Ship ship2) throws ModelException {
		try {
		return ship1.overlap(ship2);
		
		} catch (IllegalArgumentException E){
			throw new ModelException("IllegalArgumentException");
		} catch (AssertionError E){
			throw new ModelException("AssertionError");
		} catch (NullPointerException E){
			throw new ModelException("NullPointerException");
		} catch (IllegalStateException E){
			throw new ModelException("IllegalStateException");}
	}

	/**
	 * Return the number of seconds until the first collision between
	 * ship1 and ship2, or Double.POSITIVE_INFINITY if
	 * they never collide. A ship never collides with itself.
	 */
	public double getTimeToCollision(Ship ship1, Ship ship2) throws ModelException {
		try {
		return ship1.getTimeToCollision(ship2);
		
		} catch (IllegalArgumentException E){
			throw new ModelException("IllegalArgumentException");
		} catch (AssertionError E){
			throw new ModelException("AssertionError");
		} catch (NullPointerException E){
			throw new ModelException("NullPointerException");
		} catch (IllegalStateException E){
			throw new ModelException("IllegalStateException");}
	}

	/**
	 * Return the first position where ship1 and ship2
	 * collide, or null if they never collide. A ship never
	 * collides with itself.
	 * 
	 * The result of this method is either null or an array of length 2, where
	 * the element at index 0 represents the x-coordinate and the element at
	 * index 1 represents the y-coordinate.
	 */
	public double[] getCollisionPosition(Ship ship1, Ship ship2) throws ModelException {
		try {
		return ship1.getCollisionPosition(ship2).toDoubleArray();
		
		} catch (IllegalArgumentException E){
			throw new ModelException("IllegalArgumentException");
		} catch (AssertionError E){
			throw new ModelException("AssertionError");
		} catch (NullPointerException E){
			throw new ModelException("NullPointerException");
		} catch (IllegalStateException E){
			throw new ModelException("IllegalStateException");}
	}
	
	/**
	 * Create a new ship with the given parameters.
	 */
	@Override
	public Ship createShip(double x, double y, double xVelocity, double yVelocity, double radius, double direction,
			double mass) throws ModelException {
		try {
			return new Ship(x, y, xVelocity, yVelocity, radius, direction, mass);
		} catch (IllegalArgumentException E){
			throw new ModelException("IllegalArgumentException");
		} catch (AssertionError E){
			throw new ModelException("AssertionError");
		} catch (NullPointerException E){
			throw new ModelException("NullPointerException");
		} catch (IllegalStateException E){
			throw new ModelException("IllegalStateException");}
	}

	@Override
	public void terminateShip(Ship ship) throws ModelException {
		try {
			ship.die();
		} catch (IllegalArgumentException E){
			throw new ModelException("IllegalArgumentException");
		} catch (AssertionError E){
			throw new ModelException("AssertionError");
		} catch (NullPointerException E){
			throw new ModelException("NullPointerException");
		} catch (IllegalStateException E){
			throw new ModelException("IllegalStateException");}
	}

	@Override
	public boolean isTerminatedShip(Ship ship) throws ModelException {
		try {
			return ship.isDead();
		} catch (IllegalArgumentException E){
			throw new ModelException("IllegalArgumentException");
		} catch (AssertionError E){
			throw new ModelException("AssertionError");
		} catch (NullPointerException E){
			throw new ModelException("NullPointerException");
		} catch (IllegalStateException E){
			throw new ModelException("IllegalStateException");}
	}

	@Override
	public double getShipMass(Ship ship) throws ModelException {
		try {
			return ship.getTotalMass();
		} catch (IllegalArgumentException E){
			throw new ModelException("IllegalArgumentException");
		} catch (AssertionError E){
			throw new ModelException("AssertionError");
		} catch (NullPointerException E){
			throw new ModelException("NullPointerException");
		} catch (IllegalStateException E){
			throw new ModelException("IllegalStateException");}
	}

	@Override
	public World getShipWorld(Ship ship) throws ModelException {
		try {
			return ship.getWorld();
		} catch (IllegalArgumentException E){
			throw new ModelException("IllegalArgumentException");
		} catch (AssertionError E){
			throw new ModelException("AssertionError");
		} catch (NullPointerException E){
			throw new ModelException("NullPointerException");
		} catch (IllegalStateException E){
			throw new ModelException("IllegalStateException");}
	}

	@Override
	public boolean isShipThrusterActive(Ship ship) throws ModelException {
		try {
			return ship.isShipThrusterActive();
		} catch (IllegalArgumentException E){
			throw new ModelException("IllegalArgumentException");
		} catch (AssertionError E){
			throw new ModelException("AssertionError");
		} catch (NullPointerException E){
			throw new ModelException("NullPointerException");
		} catch (IllegalStateException E){
			throw new ModelException("IllegalStateException");}
	}

	@Override
	public void setThrusterActive(Ship ship, boolean active) throws ModelException {
		try {
			ship.setThrust(active);
		} catch (IllegalArgumentException E){
			throw new ModelException("IllegalArgumentException");
		} catch (AssertionError E){
			throw new ModelException("AssertionError");
		} catch (NullPointerException E){
			throw new ModelException("NullPointerException");
		} catch (IllegalStateException E){
			throw new ModelException("IllegalStateException");}
	}

	@Override
	public double getShipAcceleration(Ship ship) throws ModelException {
		try {
			return ship.getTotalAcceleration();
		} catch (IllegalArgumentException E){
			throw new ModelException("IllegalArgumentException");
		} catch (AssertionError E){
			throw new ModelException("AssertionError");
		} catch (NullPointerException E){
			throw new ModelException("NullPointerException");
		} catch (IllegalStateException E){
			throw new ModelException("IllegalStateException");}
	}

	@Override
	public Bullet createBullet(double x, double y, double xVelocity, double yVelocity, double radius)
			throws ModelException {
		try {
			return new Bullet(x, y, xVelocity, yVelocity, radius);
		} catch (IllegalArgumentException E){
			throw new ModelException("IllegalArgumentException");
		} catch (AssertionError E){
			throw new ModelException("AssertionError");
		} catch (NullPointerException E){
			throw new ModelException("NullPointerException");
		} catch (IllegalStateException E){
			throw new ModelException("IllegalStateException");}
	}

	@Override
	public void terminateBullet(Bullet bullet) throws ModelException {
		try {
			bullet.die();
		} catch (IllegalArgumentException E){
			throw new ModelException("IllegalArgumentException");
		} catch (AssertionError E){
			throw new ModelException("AssertionError");
		} catch (NullPointerException E){
			throw new ModelException("NullPointerException");
		} catch (IllegalStateException E){
			throw new ModelException("IllegalStateException");}
		
	}

	@Override
	public boolean isTerminatedBullet(Bullet bullet) throws ModelException {
		try {
			return bullet.isDead();
		} catch (IllegalArgumentException E){
			throw new ModelException("IllegalArgumentException");
		} catch (AssertionError E){
			throw new ModelException("AssertionError");
		} catch (NullPointerException E){
			throw new ModelException("NullPointerException");
		} catch (IllegalStateException E){
			throw new ModelException("IllegalStateException");}
	}

	@Override
	public double[] getBulletPosition(Bullet bullet) throws ModelException {
		try {
			return bullet.getPosition().toDoubleArray();
		} catch (IllegalArgumentException E){
			throw new ModelException("IllegalArgumentException");
		} catch (AssertionError E){
			throw new ModelException("AssertionError");
		} catch (NullPointerException E){
			throw new ModelException("NullPointerException");
		} catch (IllegalStateException E){
			throw new ModelException("IllegalStateException");}
	}

	@Override
	public double[] getBulletVelocity(Bullet bullet) throws ModelException {
		try {
			return bullet.getVelocity().toDoubleArray();
		} catch (IllegalArgumentException E){
			throw new ModelException("IllegalArgumentException");
		} catch (AssertionError E){
			throw new ModelException("AssertionError");
		} catch (NullPointerException E){
			throw new ModelException("NullPointerException");
		} catch (IllegalStateException E){
			throw new ModelException("IllegalStateException");}
	}

	@Override
	public double getBulletRadius(Bullet bullet) throws ModelException {
		try {
			return bullet.getRadius();
		} catch (IllegalArgumentException E){
			throw new ModelException("IllegalArgumentException");
		} catch (AssertionError E){
			throw new ModelException("AssertionError");
		} catch (NullPointerException E){
			throw new ModelException("NullPointerException");
		} catch (IllegalStateException E){
			throw new ModelException("IllegalStateException");}
	}

	@Override
	public double getBulletMass(Bullet bullet) throws ModelException {
		try {
			return bullet.getTotalMass();
		} catch (IllegalArgumentException E){
			throw new ModelException("IllegalArgumentException");
		} catch (AssertionError E){
			throw new ModelException("AssertionError");
		} catch (NullPointerException E){
			throw new ModelException("NullPointerException");
		} catch (IllegalStateException E){
			throw new ModelException("IllegalStateException");}
	}

	@Override
	public World getBulletWorld(Bullet bullet) throws ModelException {
		try {
			return bullet.getWorld();
		} catch (IllegalArgumentException E){
			throw new ModelException("IllegalArgumentException");
		} catch (AssertionError E){
			throw new ModelException("AssertionError");
		} catch (NullPointerException E){
			throw new ModelException("NullPointerException");
		} catch (IllegalStateException E){
			throw new ModelException("IllegalStateException");}
	}

	@Override
	public Ship getBulletShip(Bullet bullet) throws ModelException {
		try {
			return bullet.getShip();
		} catch (IllegalArgumentException E){
			throw new ModelException("IllegalArgumentException");
		} catch (AssertionError E){
			throw new ModelException("AssertionError");
		} catch (NullPointerException E){
			throw new ModelException("NullPointerException");
		} catch (IllegalStateException E){
			throw new ModelException("IllegalStateException");}
	}

	@Override
	public Ship getBulletSource(Bullet bullet) throws ModelException {
		try {
			return bullet.getSourceShip();
		} catch (IllegalArgumentException E){
			throw new ModelException("IllegalArgumentException");
		} catch (AssertionError E){
			throw new ModelException("AssertionError");
		} catch (NullPointerException E){
			throw new ModelException("NullPointerException");
		} catch (IllegalStateException E){
			throw new ModelException("IllegalStateException");}
	}

	@Override
	public World createWorld(double width, double height) throws ModelException {
		try {
			return new World(width, height);
		} catch (IllegalArgumentException E){
			throw new ModelException("IllegalArgumentException");
		} catch (AssertionError E){
			throw new ModelException("AssertionError");
		} catch (NullPointerException E){
			throw new ModelException("NullPointerException");
		} catch (IllegalStateException E){
			throw new ModelException("IllegalStateException");}
	}

	@Override
	public void terminateWorld(World world) throws ModelException {
		try {
			world.destroy();
		} catch (IllegalArgumentException E){
			throw new ModelException("IllegalArgumentException");
		} catch (AssertionError E){
			throw new ModelException("AssertionError");
		} catch (NullPointerException E){
			throw new ModelException("NullPointerException");
		} catch (IllegalStateException E){
			throw new ModelException("IllegalStateException");}
	}

	@Override
	public boolean isTerminatedWorld(World world) throws ModelException {
		try {
			return world.isTerminated();
		} catch (IllegalArgumentException E){
			throw new ModelException("IllegalArgumentException");
		} catch (AssertionError E){
			throw new ModelException("AssertionError");
		} catch (NullPointerException E){
			throw new ModelException("NullPointerException");
		} catch (IllegalStateException E){
			throw new ModelException("IllegalStateException");}
	}

	@Override
	public double[] getWorldSize(World world) throws ModelException {
		try {
			return new double[]{world.getWidth(), world.getHeight()};
		} catch (IllegalArgumentException E){
			throw new ModelException("IllegalArgumentException");
		} catch (AssertionError E){
			throw new ModelException("AssertionError");
		} catch (NullPointerException E){
			throw new ModelException("NullPointerException");
		} catch (IllegalStateException E){
			throw new ModelException("IllegalStateException");}
	}

	@Override
	public Set<? extends Ship> getWorldShips(World world) throws ModelException {
		try {
			//TODO: Implementation when ready
			//return world.query(new ShipExtractor());
			return null;
		} catch (IllegalArgumentException E){
			throw new ModelException("IllegalArgumentException");
		} catch (AssertionError E){
			throw new ModelException("AssertionError");
		} catch (NullPointerException E){
			throw new ModelException("NullPointerException");
		} catch (IllegalStateException E){
			throw new ModelException("IllegalStateException");}
	}

	@Override
	public Set<? extends Bullet> getWorldBullets(World world) throws ModelException {
		try {
			// TODO: Implementation when ready
			//return world.queryBullets();
			return null;
		} catch (IllegalArgumentException E){
			throw new ModelException("IllegalArgumentException");
		} catch (AssertionError E){
			throw new ModelException("AssertionError");
		} catch (NullPointerException E){
			throw new ModelException("NullPointerException");
		} catch (IllegalStateException E){
			throw new ModelException("IllegalStateException");}
	}

	@Override
	public void addShipToWorld(World world, Ship ship) throws ModelException {
		try {
			world.addEntity(ship);
		} catch (IllegalArgumentException E){
			throw new ModelException("IllegalArgumentException");
		} catch (AssertionError E){
			throw new ModelException("AssertionError");
		} catch (NullPointerException E){
			throw new ModelException("NullPointerException");
		} catch (IllegalStateException E){
			throw new ModelException("IllegalStateException");}
	}

	@Override
	public void removeShipFromWorld(World world, Ship ship) throws ModelException {
		try {
			world.removeEntity(ship);
		} catch (IllegalArgumentException E){
			throw new ModelException("IllegalArgumentException");
		} catch (AssertionError E){
			throw new ModelException("AssertionError");
		} catch (NullPointerException E){
			throw new ModelException("NullPointerException");
		} catch (IllegalStateException E){
			throw new ModelException("IllegalStateException");}
	}

	@Override
	public void addBulletToWorld(World world, Bullet bullet) throws ModelException {
		try {
			world.addEntity(bullet);
		} catch (IllegalArgumentException E){
			throw new ModelException("IllegalArgumentException");
		} catch (AssertionError E){
			throw new ModelException("AssertionError");
		} catch (NullPointerException E){
			throw new ModelException("NullPointerException");
		} catch (IllegalStateException E){
			throw new ModelException("IllegalStateException");}
	}

	@Override
	public void removeBulletFromWorld(World world, Bullet bullet) throws ModelException {
		try {
			world.removeEntity(bullet);
		} catch (IllegalArgumentException E){
			throw new ModelException("IllegalArgumentException");
		} catch (AssertionError E){
			throw new ModelException("AssertionError");
		} catch (NullPointerException E){
			throw new ModelException("NullPointerException");
		} catch (IllegalStateException E){
			throw new ModelException("IllegalStateException");}
	}

	@Override
	public Set<? extends Bullet> getBulletsOnShip(Ship ship) throws ModelException {
		try {
			return ship.getBullets();
		} catch (IllegalArgumentException E){
			throw new ModelException("IllegalArgumentException");
		} catch (AssertionError E){
			throw new ModelException("AssertionError");
		} catch (NullPointerException E){
			throw new ModelException("NullPointerException");
		} catch (IllegalStateException E){
			throw new ModelException("IllegalStateException");}
	}

	@Override
	public int getNbBulletsOnShip(Ship ship) throws ModelException {
		try {
			return ship.getBullets().size();
		} catch (IllegalArgumentException E){
			throw new ModelException("IllegalArgumentException");
		} catch (AssertionError E){
			throw new ModelException("AssertionError");
		} catch (NullPointerException E){
			throw new ModelException("NullPointerException");
		} catch (IllegalStateException E){
			throw new ModelException("IllegalStateException");}
	}

	@Override
	public void loadBulletOnShip(Ship ship, Bullet bullet) throws ModelException {
		try {
			ship.loadBullets(bullet);
		} catch (IllegalArgumentException E){
			throw new ModelException("IllegalArgumentException");
		} catch (AssertionError E){
			throw new ModelException("AssertionError");
		} catch (NullPointerException E){
			throw new ModelException("NullPointerException");
		} catch (IllegalStateException E){
			throw new ModelException("IllegalStateException");}
	}

	@Override
	public void loadBulletsOnShip(Ship ship, Collection<Bullet> bullets) throws ModelException {
		try {
			ship.loadBullets((Bullet[])bullets.toArray());
		} catch (IllegalArgumentException E){
			throw new ModelException("IllegalArgumentException");
		} catch (AssertionError E){
			throw new ModelException("AssertionError");
		} catch (NullPointerException E){
			throw new ModelException("NullPointerException");
		} catch (IllegalStateException E){
			throw new ModelException("IllegalStateException");}
	}

	@Override
	public void removeBulletFromShip(Ship ship, Bullet bullet) throws ModelException {
		try {
			ship.removeBullet(bullet);
		} catch (IllegalArgumentException E){
			throw new ModelException("IllegalArgumentException");
		} catch (AssertionError E){
			throw new ModelException("AssertionError");
		} catch (NullPointerException E){
			throw new ModelException("NullPointerException");
		} catch (IllegalStateException E){
			throw new ModelException("IllegalStateException");}
	}

	@Override
	public void fireBullet(Ship ship) throws ModelException {
		try {
			ship.fireBullet();
		} catch (IllegalArgumentException E){
			throw new ModelException("IllegalArgumentException");
		} catch (AssertionError E){
			throw new ModelException("AssertionError");
		} catch (NullPointerException E){
			throw new ModelException("NullPointerException");
		} catch (IllegalStateException E){
			throw new ModelException("IllegalStateException");}
	}

	@Override
	public double getTimeCollisionBoundary(Object object) throws ModelException {
		try {
			return ((Entity) object).getWorld().getTimeToCollisionWithBoundaries((Entity)object); 
		} catch (IllegalArgumentException E){
			throw new ModelException("IllegalArgumentException");
		} catch (AssertionError E){
			throw new ModelException("AssertionError");
		} catch (NullPointerException E){
			throw new ModelException("NullPointerException");
		} catch (IllegalStateException E){
			throw new ModelException("IllegalStateException");}
	}

	@Override
	public double[] getPositionCollisionBoundary(Object object) throws ModelException {
		try {
			return ((Entity) object).getWorld().getPositionToCollisionWithBoundaries((Entity)object).toDoubleArray(); 
		} catch (IllegalArgumentException E){
			throw new ModelException("IllegalArgumentException");
		} catch (AssertionError E){
			throw new ModelException("AssertionError");
		} catch (NullPointerException E){
			throw new ModelException("NullPointerException");
		} catch (IllegalStateException E){
			throw new ModelException("IllegalStateException");}
	}

	@Override
	public double getTimeCollisionEntity(Object entity1, Object entity2) throws ModelException {
		try {
			return ((Entity)entity1).getTimeToCollision((Entity)entity2);
		} catch (IllegalArgumentException E){
			throw new ModelException("IllegalArgumentException");
		} catch (AssertionError E){
			throw new ModelException("AssertionError");
		} catch (NullPointerException E){
			throw new ModelException("NullPointerException");
		} catch (IllegalStateException E){
			throw new ModelException("IllegalStateException");}
	}

	@Override
	public double[] getPositionCollisionEntity(Object entity1, Object entity2) throws ModelException {
		try {
			Vector result = ((Entity)entity1).getCollisionPosition((Entity)entity2);
			if (result == null)
				return null;
			else
				return result.toDoubleArray();
		} catch (IllegalArgumentException E){
			throw new ModelException("IllegalArgumentException");
		} catch (AssertionError E){
			throw new ModelException("AssertionError");
		} catch (IllegalStateException E){
			throw new ModelException("IllegalStateException");}
	}

	@Override
	public double getTimeNextCollision(World world) throws ModelException {
		try {
			return world.getFirstCollision().getTimeToCollision();
		} catch (IllegalArgumentException E){
			throw new ModelException("IllegalArgumentException");
		} catch (AssertionError E){
			throw new ModelException("AssertionError");
		} catch (NullPointerException E){
			throw new ModelException("NullPointerException");
		} catch (IllegalStateException E){
			throw new ModelException("IllegalStateException");}
	}

	@Override
	public double[] getPositionNextCollision(World world) throws ModelException {
		try {
			return world.getFirstCollision().getCollisionPosition().toDoubleArray();
		} catch (IllegalArgumentException E){
			throw new ModelException("IllegalArgumentException");
		} catch (AssertionError E){
			throw new ModelException("AssertionError");
		} catch (NullPointerException E){
			throw new ModelException("NullPointerException");
		} catch (IllegalStateException E){
			throw new ModelException("IllegalStateException");}
	}

	@Override
	public void evolve(World world, double dt, CollisionListener collisionListener) throws ModelException {
		try {
			world.evolve(dt, collisionListener);
		} catch (IllegalArgumentException E){
			throw new ModelException("IllegalArgumentException");
		} catch (AssertionError E){
			throw new ModelException("AssertionError");
		} catch (NullPointerException E){
			throw new ModelException("NullPointerException");
		} catch (IllegalStateException E){
			throw new ModelException("IllegalStateException");}
	}

	@Override
	public Object getEntityAt(World world, double x, double y) throws ModelException {
		try {
			return world.getEntityAtPosition(x, y);
		} catch (IllegalArgumentException E){
			throw new ModelException("IllegalArgumentException");
		} catch (AssertionError E){
			throw new ModelException("AssertionError");
		} catch (NullPointerException E){
			throw new ModelException("NullPointerException");
		} catch (IllegalStateException E){
			throw new ModelException("IllegalStateException");}
	}

	@Override
	public Set<? extends Object> getEntities(World world) throws ModelException {
		try {
			return world.getAllEntities();
		} catch (IllegalArgumentException E){
			throw new ModelException("IllegalArgumentException");
		} catch (AssertionError E){
			throw new ModelException("AssertionError");
		} catch (NullPointerException E){
			throw new ModelException("NullPointerException");
		} catch (IllegalStateException E){
			throw new ModelException("IllegalStateException");}
	}

	@Override
	public int getNbStudentsInTeam() {
		return 2;
	}

	@Override
	public Set<? extends Asteroid> getWorldAsteroids(World world) throws ModelException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addAsteroidToWorld(World world, Asteroid asteroid) throws ModelException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeAsteroidFromWorld(World world, Asteroid asteroid) throws ModelException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Set<? extends Planetoid> getWorldPlanetoids(World world) throws ModelException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addPlanetoidToWorld(World world, Planetoid planetoid) throws ModelException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removePlanetoidFromWorld(World world, Planetoid planetoid) throws ModelException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Asteroid createAsteroid(double x, double y, double xVelocity, double yVelocity, double radius)
			throws ModelException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void terminateAsteroid(Asteroid asteroid) throws ModelException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isTerminatedAsteroid(Asteroid asteroid) throws ModelException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public double[] getAsteroidPosition(Asteroid asteroid) throws ModelException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double[] getAsteroidVelocity(Asteroid asteroid) throws ModelException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getAsteroidRadius(Asteroid asteroid) throws ModelException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getAsteroidMass(Asteroid asteroid) throws ModelException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public World getAsteroidWorld(Asteroid asteroid) throws ModelException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Planetoid createPlanetoid(double x, double y, double xVelocity, double yVelocity, double radius,
			double totalTraveledDistance) throws ModelException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void terminatePlanetoid(Planetoid planetoid) throws ModelException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isTerminatedPlanetoid(Planetoid planetoid) throws ModelException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public double[] getPlanetoidPosition(Planetoid planetoid) throws ModelException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double[] getPlanetoidVelocity(Planetoid planetoid) throws ModelException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getPlanetoidRadius(Planetoid planetoid) throws ModelException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getPlanetoidMass(Planetoid planetoid) throws ModelException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getPlanetoidTotalTraveledDistance(Planetoid planetoid) throws ModelException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public World getPlanetoidWorld(Planetoid planetoid) throws ModelException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Program getShipProgram(Ship ship) throws ModelException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void loadProgramOnShip(Ship ship, Program program) throws ModelException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Object> executeProgram(Ship ship, double dt) throws ModelException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IProgramFactory<?, ?, ?, ? extends Program> createProgramFactory() throws ModelException {
		// TODO Auto-generated method stub
		return null;
	}
}
