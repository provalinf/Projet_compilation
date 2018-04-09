package tds;

import java.util.ArrayList;

/**
 * Created by Valentin.
 */
public abstract class Entree {
	private String id;

	public Entree(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public boolean isFunction() {
		return false;
	}

	public boolean isTab() {
		return false;
	}

	@Override
	public String toString() {
		return id;
	}	// Pour ContainKey
}
