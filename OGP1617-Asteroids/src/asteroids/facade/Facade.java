package asteroids.facade;

import asteroids.model.Ship;
import asteroids.part1.facade.IFacade;
import asteroids.util.ModelException;

public class Facade implements IFacade{

	/**
	 * Create a new ship with a default position, velocity, radius and
	 * direction.
	 * 
	 * Result is a unit circle centered on <code>(0, 0)</code> facing right. Its
	 * speed is zero.
	 */
	public Ship createShip() throws ModelException {
		return new Ship();
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
		return new double[]{ship.getPositionX(), ship.getPositionY()};
	}

	/**
	 * Return the velocity of ship as an array of length 2, with the velocity
	 * along the X-axis at index 0 and the velocity along the Y-axis at index 1.
	 */
	public double[] getShipVelocity(Ship ship) throws ModelException {
		return new double[]{ship.getVelocityX(), ship.getVelocityY()};
	}

	/**
	 * Return the radius of ship.
	 */
	public double getShipRadius(Ship ship) throws ModelException {
		return ship.getRadius();
	}

	/**
	 * Return the orientation of ship (in radians).
	 */
	public double getShipOrientation(Ship ship) throws ModelException {
		return ship.getAngle();
	}

	/**
	 * Update ship's position, assuming it moves dt
	 * seconds at its current velocity.
	 */
	public void move(Ship ship, double dt) throws ModelException {
		ship.move(dt);
	}

	/**
	 * Update ship's velocity based on its current velocity, its
	 * direction and the given amount.
	 */
	public void thrust(Ship ship, double amount) throws ModelException {
		ship.thrust(amount);
	}
	
	/**
	 * update ship's velocity to cancel all movement, by setting the velocity to zero.
	 */
	public void killVelocity(Ship ship) throws ModelException {
		ship.killVelocity();
	}
	/**
	 * Update the direction of ship by adding angle
	 * (in radians) to its current direction. angle may be
	 * negative.
	 */
	public void turn(Ship ship, double angle) throws ModelException {
		ship.turn(angle);
		
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
		return ship1.getDistanceBetween(ship2);
	}

	/**
	 * Check whether ship1 and ship2 overlap. A ship
	 * always overlaps with itself.
	 */
	public boolean overlap(Ship ship1, Ship ship2) throws ModelException {
		return ship1.overlap(ship2);
	}

	/**
	 * Return the number of seconds until the first collision between
	 * ship1 and ship2, or Double.POSITIVE_INFINITY if
	 * they never collide. A ship never collides with itself.
	 */
	public double getTimeToCollision(Ship ship1, Ship ship2) throws ModelException {
		return ship1.getTimeToCollision(ship2);
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
		return ship1.getCollisionPosition(ship2);
	}

}
