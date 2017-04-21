package asteroids.filters;

import asteroids.model.Ship;

public class ShipExtractor extends Extractor {
	/**
	 * Return the given object if it is a Ship.
	 * 
	 * @param  	object
	 *         	The object to filter.
	 * @return 	The object if it is an instance the
	 * 			class Ship.
	 *         	| if (object instanceof Ship)
	 *        	|	 then result == object
	 * @return	Null if the given object is not a Ship.
	 * 			| if !(object instanceof Ship)
	 * 			|	then result == null
	 */
	public Ship getItem(Object object){
		assert (object != null);
		if (object instanceof Ship)
			return (Ship)object;
		else
			return null;
	}
}
