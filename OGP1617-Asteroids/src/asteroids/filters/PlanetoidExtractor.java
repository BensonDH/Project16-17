package asteroids.filters;

import asteroids.model.Planetoid;

public class PlanetoidExtractor extends Extractor {

	/**
	 * Return the given object if it is a Planetoid.
	 * 
	 * @param  	object
	 *         	The object to filter.
	 * @return 	The object if it is an instance the
	 * 			class Planetoid.
	 *         	| if (object instanceof Planetoid)
	 *        	|	 then result == object
	 * @return	Null if the given object is not a Bullet.
	 * 			| if !(object instanceof Planetoid)
	 * 			|	then result == null
	 */
	public Planetoid getItem(Object object){
		assert (object != null);
		if (object instanceof Planetoid)
			return (Planetoid)object;
		else
			return null;
	}

}
