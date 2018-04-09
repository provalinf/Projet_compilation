package tds;

/**
 * Created by bonnal4u.
 */
public class EntreeTableau extends Entree {
	private int size;

	public EntreeTableau(String idf) {
		super(idf);
	}

	@Override
	public boolean isTab() {
		return true;
	}

	public int getabSize() {
		return size;
	}
}
