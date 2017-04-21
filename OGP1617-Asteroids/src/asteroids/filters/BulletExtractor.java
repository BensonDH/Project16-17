package asteroids.filters;

import asteroids.model.Bullet;

public class BulletExtractor extends Extractor {
	
	/**
	 * Return the given object if it is a Bullet.
	 * 
	 * @param  	object
	 *         	The object to filter.
	 * @return 	The object if it is an instance the
	 * 			class Bullet.
	 *         	| if (object instanceof Bullet)
	 *        	|	 then result == object
	 * @return	Null if the given object is not a Bullet.
	 * 			| if !(object instanceof Bullet)
	 * 			|	then result == null
	 */
	public Bullet getItem(Object object){
		assert (object != null);
		if (object instanceof Bullet)
			return (Bullet)object;
		else
			return null;
	}
}
