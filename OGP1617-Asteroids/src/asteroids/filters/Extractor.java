package asteroids.filters;

import asteroids.model.Entity;


public abstract class Extractor {
	
	/**
	 * TODO: Is dit goed genoeg?
	 * 
	 * Return the given object if it is an instance of the
	 * wanted entity type.
	 * 
	 * @param  object
	 *         The object to filter.
	 * @return The object if it is an instance of the wanted
	 * 		   Entity type, null otherwise.
	 *         | if (object instanceof wantedType)
	 *         |	 then result == object
	 *         | else result == null
	 */
	public abstract Entity getItem(Object object);
}
