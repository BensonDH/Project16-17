package asteroids.model;

import java.util.Random;

import asteroids.part2.CollisionListener;
import be.kuleuven.cs.som.annotate.*;

/**
 *  GitHub repository : https://github.com/BensonDH/Project16-17
 */

/**
 * An immutable Class that contains all important information about a collision that
 * will happen in a game World.
 * 
 * @version	1.0
 * @author 	De Heel Benson (burgerlijk ingenieur computerwetenschappen - elektrotechniek, 
 * 			De Jaegere Xander burgerlijk ingenieur computerwetenschappen - elektrotechniek)  
 */
public class Collision{
	
	/**
	 * Initialize this new Collision object.
	 * 
	 * @param firstInvolvedEntity
	 * 			The first Entity involved in this collision.
	 * @param secondInvolvedEntity
	 * 			The second Entity involved in this collision.
	 * 			If secondInvolvedEntity is null, firstInvolvedEntity will collide
	 * 			with a boundary of its world.
	 * @param collisionPosition
	 * 			The position where this collision will happen.
	 * @param timeToCollision
	 * 			The time until this collision will happen, seen at the moment of creation of this object.
	 * @post	FirstInvolvedEntity will be set to the given firstInvolvedEntity
	 * 			| new.getFirstInvolvedEntity() == firstInvolvedEntity
	 * @post	SecondInvolvedEntity will be set to the given secondInvolvedEntity
	 * 			| new.getSecondInvolvedEntity() == secondInvolvedEntity
	 * @post	If the given firstInvolvedEntity is not null, collisionPosition and timeToCollision
	 * 			will be set to the given collisionPosition and timeToCollision respectively.
	 * 			| if (firstInvolvedEntity != null)
	 * 			| then (getCollisionPosition() == collisionPosition) &&
	 * 			|	   (getTimeToCollision() == timeToCollision)
	 * @post	If the given firstInvolvedEntity is null, collisionPosition and timeToCollision
	 * 			will be set to null and Double.POSITIVE_INFINITY respectively.
	 * 			| if (firstInvolvedEntity == null)
	 * 			| then (getCollisionPosition() == null) &&
	 * 			|	   (getTimeToCollision() == Double.POSITIVE_INFINITY)
	 */
	public Collision(Entity firstInvolvedEntity, Entity secondInvolvedEntity, 
					Vector collisionPosition, double timeToCollision){
		
		this.firstInvolvedEntity = firstInvolvedEntity;
		this.secondInvolvedEntity = secondInvolvedEntity;
		
		if (firstInvolvedEntity == null){
			this.timeToCollision = Double.POSITIVE_INFINITY;
			this.collisionPosition = null;
		} else { 	
			this.collisionPosition = collisionPosition;
			this.timeToCollision = timeToCollision;
		}
	}
	
	/**
	 * Initialize this new Collision object.
	 * 
	 * When this constructor is used, this Collision object will be the 
	 * equivalent to: "No collision will happen". 
	 * This means that firstInvolvedEntity, secondInvovledEntity and 
	 * collisionPosition will be null, and timeToCollision will be
	 * Double.POSITIVE_INFINITY
	 * 
	 * @effect	
	 * 			| this(null, null, null, Double.POSITIVE_INFINITY)
	 */
	public Collision(){
		this(null, null, null, Double.POSITIVE_INFINITY);
	}
	
	/**
	 * Get the first Entity that is involved in this collision.
	 * @see implementation
	 */
	@Basic @Immutable
	public Entity getFirstInvolvedEntity(){
		return firstInvolvedEntity;
	}
	
	/**
	 * A variable registering the first involved entity of this collision
	 */
	private final Entity firstInvolvedEntity;
	
	
	/**
	 * Get the second Entity that is involved in this collision
	 * When secondInvolvedEntity is null, firstInvolvedEntity will
	 * collide with a boundary of its world.
	 * 
	 * @return The second involved Entity of this collision.
	 * 			if (result == null) then getFistInvolvedEntity() will
	 * 			collide with a boundary of getFirstInvolvedEntity().getWorld()
	 */
	@Basic @Immutable
	public Entity getSecondInvolvedEntity(){
		return this.secondInvolvedEntity;
	}
	
	/**
	 * A variable registering the second involved entity of this collision.
	 */
	private final Entity secondInvolvedEntity;
	
	/**
	 * Get the position where this collision will happen.
	 * @see implementation
	 */
	@Basic @Immutable
	public Vector getCollisionPosition(){
		return this.collisionPosition;
	}
	
	/**
	 * A variable registering the position of this collision
	 */
	private final Vector collisionPosition;
	
	
	/**
	 * Get the time until this collision will happen.
	 * I.e. the time until this collision when this Collision object
	 * was created.
	 * 
	 * @see implementation
	 */
	@Basic @Immutable
	public double getTimeToCollision(){
		return this.timeToCollision;
	}
	
	/**
	 * A variable registering the time until this collision.
	 */
	private final double timeToCollision;
	
	/**
	 * Handle this collision.
	 * TODO Documentation
	 */
	public void handleCollision(CollisionListener collisionListener){
		// If firstInvolvedEntity is null, this class represents "no collision will happen", 
		// In this case we have to do nothing.
		if (getFirstInvolvedEntity() == null)
			return;
		
		World gameWorld = getFirstInvolvedEntity().getWorld();
		Entity firstEntity = getFirstInvolvedEntity();
		Entity secondEntity = getSecondInvolvedEntity();
		gameWorld.advanceEntities(getTimeToCollision());
		
		// -*- Border Collision
		if (getSecondInvolvedEntity() == null){
			if (collisionListener != null)
				collisionListener.boundaryCollision(firstEntity, getCollisionPosition().getX(), getCollisionPosition().getY());
			borderCollision(getFirstInvolvedEntity());
			return;
		}
		
		// -*- ObjectCollision
		if (collisionListener != null){
			collisionListener.objectCollision(firstEntity, secondEntity, getCollisionPosition().getX(), getCollisionPosition().getY());
		}
		if (firstEntity instanceof Ship && secondEntity instanceof Ship)
			resolveCollision((Ship)getFirstInvolvedEntity(), (Ship)getSecondInvolvedEntity());
		else if (firstEntity instanceof MinorPlanet && secondEntity instanceof MinorPlanet)
			resolveCollision((MinorPlanet)firstEntity, (MinorPlanet)secondEntity);
		else if (firstEntity instanceof Bullet && secondEntity instanceof Bullet){
			firstEntity.die();
			secondEntity.die();
		}
		else if (firstEntity instanceof Ship && secondEntity instanceof Bullet)
			resolveCollision((Ship)firstEntity, (Bullet)secondEntity);
		else if (firstEntity instanceof Bullet && secondEntity instanceof Ship)
			resolveCollision((Ship)secondEntity, (Bullet)firstEntity);
		else if (firstEntity instanceof Ship && secondEntity instanceof Asteroid)
			resolveCollision((Ship)firstEntity, (Asteroid) secondEntity);
		else if (firstEntity instanceof Asteroid && secondEntity instanceof Ship)
			resolveCollision((Ship)secondEntity, (Asteroid) firstEntity);
		else if (firstEntity instanceof Ship && secondEntity instanceof Planetoid)
			resolveCollision((Ship)firstEntity, (Planetoid)secondEntity);
		else if (firstEntity instanceof Planetoid && secondEntity instanceof Ship)
			resolveCollision((Ship)secondEntity, (Planetoid)firstEntity);
	}
	
	// -*-*-*-*-*-*-*-*- Collision Helper Methods -*-*-*-*-*-*-*-*-
	/**
	 * Handle a collision of an entity with a boundary.
	 * 
	 * When the entity is a Bullet and it has bounced off a boundary too many
	 * times, the bullet will die.
	 * If the entity collides with a horizontal border, the Y-component of its
	 * velocity vector is reversed.
	 * If the entity collides with a vertical border, the X-component of its
	 * velocity vector is reversed.
	 * 
	 * @param entity
	 * 			The entity that will collide with a boundary
	 * @post	If the entity is a bullet and it has reached the maximum amount of collisions 
	 * 			with boundaries, the bullet will die.
	 * 		    | if (entity instanceof Bullet && 
	 * 			|	  entity.getNbTimesBounced()+1 == entity.getMaxTimesBounced())
	 * 			| then new.entity.isDead() == true
	 * @post	If the entity is a bullet and it did not reach its maximum amount of collisions
	 * 			with boundaries yet, the bullet's NbTimesBounced will be increased with 1.
	 * 			| if (entity instanceof Bullet &&
	 * 			| 	  entity.getNbTimesBounced()+1 < entity.getMaxTimesBounced())
	 * 			| then new.entity.getNbTimesBounced() == old.entity.getNbTimesBounced()+1
	 * @effect	
	 * 			| if (apparentlyCollidesWithHorizontalBorder(entity))
	 * 			|		entity.setVelocity(-1*entity.getVelocity().getX(),
	 * 			|						   entity.getVelocity().getY())
     * 			| else
     * 			|		entity.setVelocity(entity.getVelocity().getX(),
     * 			|						   -1*entity.getVelocity()*getY())
     * 
     * This is a helper method for the method handleCollision.
	 */
	private void borderCollision(Entity entity){
		World gameWorld = entity.getWorld();
		// If the entity is a bullet, we have to check how many times the bullet has 
		// bounced off a boundary
		if (entity instanceof Bullet) {
			int nbTimesBounced = ((Bullet)entity).getNbTimesBounced();
			int maxTimesBounced = ((Bullet)entity).getMaxTimesBounced();
			
			if (nbTimesBounced == maxTimesBounced)
				entity.die();
			else
				((Bullet)entity).setNbTimesBounced(((Bullet)entity).getNbTimesBounced()+1);
		}
		
		// It's a horizontal border
		if (gameWorld.apparentlyCollidesWithHorizontalBorder(entity)) {
			Vector velocity = entity.getVelocity();
			// Flip the Y-Component of the 
			entity.setVelocity(velocity.getX(), -velocity.getY());
		}
		// It's a vertical border
		else {
			Vector velocity = entity.getVelocity();
			// Flip the X-Component of the 
			entity.setVelocity(-velocity.getX(), velocity.getY());
			
			// We're done, exit the method
			return;
		}
	}
	
	/**
	 * Handle a collision with two ships.
	 * 
	 * @param firstShip
	 * 			The first ship involved in the collision.
	 * @param secondShip
	 * 			The second ship involved in the collision.
	 * 
	 * @post	Both ships their velocity will be changed in a way
	 * 			that they bounce off each other.
	 * 			The mass of both ships influences the collision's resolution.
	 * 
	 * This is a helper method for the method handleCollision.
	 */
	private void resolveCollision(Ship firstShip, Ship secondShip){
		bounceOff(firstShip, secondShip);
	}
	
	private void resolveCollision(MinorPlanet firstPlanet, MinorPlanet secondPlanet){
		bounceOff(firstPlanet, secondPlanet);
	}
	
	/**
	 * Make two entities bounce away from each other.
	 * 
	 * @param firstEntity
	 * 			The first Entity involved in the collision.
	 * @param secondEntity
	 * 			The second ship involved in the collision.
	 * 
	 * @post	Both Entities their velocity will be changed in a way
	 * 			that they bounce off each other.
	 * 			The mass of both Entities influences the collision's resolution.
	 * 
	 * @see implementation for the formulas used for the new velocities.
	 */
	private void bounceOff(Entity firstEntity, Entity secondEntity){
		double mi = firstEntity.getTotalMass();
		double mj = secondEntity.getTotalMass();
		double sigma = firstEntity.getRadius()+secondEntity.getRadius();
		
		Vector firstVel = firstEntity.getVelocity();
		Vector secondVel = secondEntity.getVelocity();
		
		Vector deltaPos = secondEntity.getPosition().subtract(firstEntity.getPosition());
		Vector deltaVel = secondVel.subtract(firstVel);

		double J = (2*mi*mj*deltaVel.dot(deltaPos))/ (sigma*(mi+mj));
		
		firstEntity.setVelocity(firstVel.getX()+(J*deltaPos.getX())/(sigma*mi),
								firstVel.getY()+(J*deltaPos.getY())/(sigma*mi));
		
		secondEntity.setVelocity(secondVel.getX()-(J*deltaPos.getX())/(sigma*mj),
								 secondVel.getX()-(J*deltaPos.getY())/(sigma*mj));
	}
	
	private void resolveCollision(Ship ship, Asteroid asteroid){
		ship.die();
	}
	
	private void resolveCollision(Ship ship, Planetoid planetoid){
		World gameWorld = ship.getWorld();
		double shipRadius = ship.getRadius();
		ship.removeWorld();
		
		double worldWidth = gameWorld.getWidth();
		double worldHeight = gameWorld.getHeight();
		
		Random random = new Random();
		double newXPosition = shipRadius+(worldWidth-2*shipRadius)*random.nextDouble();
		double newYPosition = shipRadius+(worldHeight-2*shipRadius)*random.nextDouble();
		
		ship.setPosition(newXPosition, newYPosition);
		
		if (gameWorld.overlapsWithAnyEntity(ship))
			ship.die();
		else
			gameWorld.addEntity(ship);
	}
	
	
	/**
	 * Handle a collision between a bullet and a ship.
	 * 
	 * @param ship
	 * 			The ship involved in the collision.
	 * @param bullet
	 * 			The bullet involved in the collision.
	 * @post	If the bullet belongs to the ship, the bullet is
	 * 			loaded on the ship.
	 * 			| if (bullet.getSourceShip() == ship)
	 * 			| then ship.addBullet(bullet);
	 * @post 	If the bullet does not belong to the ship,
	 * 			both bullet and ship die.
	 * 			| if (bullet.getSourceShip() != ship)
	 * 			| then ship.die()
	 * 			|	   bullet.die()
	 * 
	 * This is a helper method for the method handleCollision.
	 */
	private void resolveCollision(Ship ship, Bullet bullet){
		// Check whether the bullet belongs to the ship
		if (bullet.getSourceShip() == ship)
			ship.loadBullets(bullet);
		// If the bullet does not belong to the ship, both ship and bullet die
		else {
			ship.die();
			bullet.die();
		}
	}

	
	/**
	 * Return a textual representation of this collision
	 * 
	 * @return 
	 * 		  	| If getFirstInvolvedEntity() == null && getSecondInvolvedEntity() == null,
	 * 		  	| 	then result =="Collision: No collision will happen."
	 * @return 
	 * 			| If getFirstInvolvedEntity() != null && getSecondInvolvedEntity() == null
	 * 			| 	then result == "Collision: Between "+getFistInvolvedEntity()+" and a border at "+
	 *			|					getCollisionPosition()+" in "+getTimeToCollision()+" s.";
	 * @return
	 *			| If getFirstInvolvedEntity() != null && getSecondInvolvedEntity() != null
	 *			|	then result == "Collision between "+getFistInvolvedEntity()+" and "+getSecondInvolvedEntity()+" at "+
	 *								getCollisionPosition()+" in "+getTimeToCollision()+" s.";
	 */
	@Override
	public String toString(){
		if (getFirstInvolvedEntity() == null && getSecondInvolvedEntity() == null) {
			return "Collision: No collision will happen";
		}
		else if (getSecondInvolvedEntity() == null) {
			return "Collision: Between "+getFirstInvolvedEntity()+" and a border at "+
					getCollisionPosition()+" in "+getTimeToCollision()+"sec";
		}
		else {
			return "Collision between "+getFirstInvolvedEntity()+" and "+getSecondInvolvedEntity()+" at "+
			getCollisionPosition()+" in "+getTimeToCollision()+" s.";
		}
	}
}
