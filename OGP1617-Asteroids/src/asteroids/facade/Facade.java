package asteroids.facade;

import java.util.Collection;
import java.util.Set;

import asteroids.model.Bullet;
import asteroids.model.Ship;
import asteroids.model.World;
import asteroids.part1.facade.IFacade;
import asteroids.part2.CollisionListener;
import asteroids.util.ModelException;

public class Facade implements IFacade, asteroids.part2.facade.IFacade{

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
		}
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
		}
	}

	/**
	 * Return the position of ship as an array of length 2, with the
	 * x-coordinate at index 0 and the y-coordinate at index 1.
	 */
	public double[] getShipPosition(Ship ship) throws ModelException {
		try {
		return ship.getPosition();
		} catch (IllegalArgumentException E){
			throw new ModelException("IllegalArgumentException");
		} catch (AssertionError E){
			throw new ModelException("AssertionError");
		}
	}

	/**
	 * Return the velocity of ship as an array of length 2, with the velocity
	 * along the X-axis at index 0 and the velocity along the Y-axis at index 1.
	 */
	public double[] getShipVelocity(Ship ship) throws ModelException {
		try {
		return ship.getVelocity();
		} catch (IllegalArgumentException E){
			throw new ModelException("IllegalArgumentException");
		} catch (AssertionError E){
			throw new ModelException("AssertionError");
		}
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
		}
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
		}
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
		}
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
		}
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
		}
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
		}	
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
		}catch (NullPointerException E){
			throw new ModelException("NullPointerError");
		}
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
		}
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
		}
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
		return ship1.getCollisionPosition(ship2);
		
		} catch (IllegalArgumentException E){
			throw new ModelException("IllegalArgumentException");
		} catch (AssertionError E){
			throw new ModelException("AssertionError");
		}
	}

	@Override
	public Ship createShip(double x, double y, double xVelocity, double yVelocity, double radius, double direction,
			double mass) throws ModelException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void terminateShip(Ship ship) throws ModelException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isTerminatedShip(Ship ship) throws ModelException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public double getShipMass(Ship ship) throws ModelException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public World getShipWorld(Ship ship) throws ModelException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isShipThrusterActive(Ship ship) throws ModelException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setThrusterActive(Ship ship, boolean active) throws ModelException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double getShipAcceleration(Ship ship) throws ModelException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Bullet createBullet(double x, double y, double xVelocity, double yVelocity, double radius)
			throws ModelException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void terminateBullet(Bullet bullet) throws ModelException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isTerminatedBullet(Bullet bullet) throws ModelException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public double[] getBulletPosition(Bullet bullet) throws ModelException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double[] getBulletVelocity(Bullet bullet) throws ModelException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getBulletRadius(Bullet bullet) throws ModelException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getBulletMass(Bullet bullet) throws ModelException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public World getBulletWorld(Bullet bullet) throws ModelException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Ship getBulletShip(Bullet bullet) throws ModelException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Ship getBulletSource(Bullet bullet) throws ModelException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public World createWorld(double width, double height) throws ModelException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void terminateWorld(World world) throws ModelException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isTerminatedWorld(World world) throws ModelException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public double[] getWorldSize(World world) throws ModelException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<? extends Ship> getWorldShips(World world) throws ModelException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<? extends Bullet> getWorldBullets(World world) throws ModelException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addShipToWorld(World world, Ship ship) throws ModelException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeShipFromWorld(World world, Ship ship) throws ModelException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addBulletToWorld(World world, Bullet bullet) throws ModelException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeBulletFromWorld(World world, Bullet bullet) throws ModelException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Set<? extends Bullet> getBulletsOnShip(Ship ship) throws ModelException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getNbBulletsOnShip(Ship ship) throws ModelException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void loadBulletOnShip(Ship ship, Bullet bullet) throws ModelException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void loadBulletsOnShip(Ship ship, Collection<Bullet> bullets) throws ModelException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeBulletFromShip(Ship ship, Bullet bullet) throws ModelException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void fireBullet(Ship ship) throws ModelException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double getTimeCollisionBoundary(Object object) throws ModelException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double[] getPositionCollisionBoundary(Object object) throws ModelException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getTimeCollisionEntity(Object entity1, Object entity2) throws ModelException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double[] getPositionCollisionEntity(Object entity1, Object entity2) throws ModelException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getTimeNextCollision(World world) throws ModelException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double[] getPositionNextCollision(World world) throws ModelException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void evolve(World world, double dt, CollisionListener collisionListener) throws ModelException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object getEntityAt(World world, double x, double y) throws ModelException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<? extends Object> getEntities(World world) throws ModelException {
		// TODO Auto-generated method stub
		return null;
	}

}
