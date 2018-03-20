package tds;

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

	@Override
	public String toString() {
		return id;
	}	// Pour ContainKey
}
