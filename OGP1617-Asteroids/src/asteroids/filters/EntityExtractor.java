package asteroids.filters;

import asteroids.model.Entity;

public class EntityExtractor extends Extractor {
	/**
	 * Return the given object if it is an Entity.
	 * 
	 * @param  	object
	 *         	The object to filter.
	 * @return 	The object if it is an instance the
	 * 			class Entity.
	 *         	| if (object instanceof Entity)
	 *        	|	 then result == object
	 * @return	Null if the given object is not a Bullet.
	 * 			| if !(object instanceof Entity)
	 * 			|	then result == null
	 */
	public Entity getItem(Object object){
		assert (object != null);
		if (object instanceof Entity)
			return (Entity)object;
		else
			return null;
	}
}
