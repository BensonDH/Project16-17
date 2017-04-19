package asteroids.model;

import be.kuleuven.cs.som.annotate.*;

/**
 * A Class that contains all the information about the next collision that
 * will happen in a game World
 * 
 */
public class FirstCollision {
	
	/**
	 * Initialize this new firstCollision object.
	 * 
	 * @param firstInvolvedEntity
	 * 			The first involved Entity of this collision
	 * @param secondInvolvedEntity
	 * 			The second involved Entity of this collision
	 * 			if secondInvolvedEntity is null, firstInvolvedEntity will collide
	 * 			with a boundary of its world.
	 * @param collisionPosition
	 * 			The position where this collision will happen.
	 * @param timeToCollision
	 * 			The time until this collision will happen, seen at the moment of creation of this object.
	 * @post	firstInvolvedEntity will be set to the given firstInvolvedEntity
	 * 			| getFirstInvolvedEntity() == firstInvolvedEntity
	 * @post	secondInvolvedEntity will be set to the given secondInvolvedEntity
	 * 			| getSecondInvolvedEntity() == secondInvolvedEntity
	 * @post	if the given firstInvolvedEntity is not null, collisionPosition and timeToCollision
	 * 			will be set to the given collisionPosition and timeToCollision respectively.
	 * 			| if (firstInvolvedEntity != null)
	 * 			| then (getCollisionPosition() == collisionPosition) &&
	 * 			|	   (getTimeToCollision() == timeToCollision)
	 * @post	if the given firstInvolvedEntity is null, collisionPosition and timeToCollision
	 * 			will be set to null and Double.POSITIVE_INFINITY respectively.
	 * 			| if (firstInvolvedEntity == null)
	 * 			| then (getCollisionPosition() == null) &&
	 * 			|	   (getTimeToCollision() == Double.POSITIVE_INFINITY)
	 */
	public FirstCollision(Entity firstInvolvedEntity, Entity secondInvolvedEntity, 
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
	 * Initialize this new firstCollision object.
	 * 
	 * When this constructor is used, this FirstCollision will be the 
	 * same as: "No collision will happen". 
	 * This means that firstInvolvedEntity, secondInvovledEntity and 
	 * collisionPosition will be null, and timeToCollision will be
	 * Double.POSITIVE_INFINITY
	 * 
	 * @effect	this(null, null, null, Double.POSITIVE_INFINITY)
	 */
	public FirstCollision(){
		this(null, null, null, Double.POSITIVE_INFINITY);
	}
	
	/**
	 * Get the first Entity that is involved in this collision.
	 * @see implementation
	 */
	@Basic @Immutable
	public Entity getFistInvolvedEntity(){
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
	 * I.e. the time until this collision when this FirstCollision object
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
	
}
