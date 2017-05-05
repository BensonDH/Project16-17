package asteroids.filters;

import asteroids.model.Asteroid;

public class AsteroidExtractor extends Extractor {

	/**
	 * Return the given object if it is an Asteroid.
	 * 
	 * @param  	object
	 *         	The object to filter.
	 * @return 	The object if it is an instance the
	 * 			class Asteroid.
	 *         	| if (object instanceof Asteroid)
	 *        	|	 then result == object
	 * @return	Null if the given object is not a Bullet.
	 * 			| if !(object instanceof Asteroid)
	 * 			|	then result == null
	 */
	@Override
	public Asteroid getItem(Object object) {
		assert (object != null);
		if (object instanceof Asteroid)
			return (Asteroid)object;
		else
			return null;
	}
}
