package mc.xesau.bukkitutils;

import java.util.HashMap;
import java.util.UUID;

public class UUIDManager<T> extends HashMap<UUID, T> {

	private static final long serialVersionUID = 5897751304953700891L;

	/**
	 * Set all instances in the manager to a specific value
	 * 
	 * @param value
	 *            The value to set all instances to
	 */
	public void putAll(T value) {
		for (UUID key : keySet()) {
			put(key, value);
		}
	}

	/**
	 * Remove all instances that are the same as <code>T value</code>
	 * 
	 * @param value
	 *            The value to check for
	 */
	public void removeValue(T value) {
		for (UUID key : keySet()) {
			if (get(key) == value)
				remove(key);
		}
	}

	/**
	 * Remove all the instances that are not <code>T value</code>
	 * 
	 * @param not
	 *            The instances that shouldn't be removed
	 */
	public void clear(T not) {
		for (UUID key : keySet()) {
			if (get(key) != not)
				remove(get(key));
		}
	}

}
