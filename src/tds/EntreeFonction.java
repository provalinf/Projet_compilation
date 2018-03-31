package tds;

/**
 * Created by Valentin.
 */
public class EntreeFonction extends Entree {

	private int nbParam;

	public EntreeFonction(String id, int nbParam) {
		super(id);
		this.nbParam = nbParam;
	}

	public int getNbParam() {
		return nbParam;
	}

	@Override
	public boolean isFunction() {
		return true;
	}

}
