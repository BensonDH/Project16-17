package asteroids.filters;

import asteroids.model.MinorPlanet;

public class MinorPlanetExtractor extends Extractor {
	/**
	 * Return the given object if it is a MinorPlanet.
	 * 
	 * @param  	object
	 *         	The object to filter.
	 * @return 	The object if it is an instance the
	 * 			class MinorPlanet.
	 *         	| if (object instanceof MinorPlanet)
	 *        	|	 then result == object
	 * @return	Null if the given object is not a MinorPlanet.
	 * 			| if !(object instanceof MinorPlanet)
	 * 			|	then result == null
	 */
	public MinorPlanet getItem(Object object){
		assert (object != null);
		if (object instanceof MinorPlanet)
			return (MinorPlanet)object;
		else
			return null;
	}
}
