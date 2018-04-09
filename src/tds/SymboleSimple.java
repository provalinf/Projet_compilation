package tds;

/**
 * Created by Valentin.
 */
public class SymboleSimple extends Symbole {
	public SymboleSimple(String type) {
		super(type);
	}

	@Override
	public boolean isFonction() {
		return false;
	}

	@Override
	public boolean isTableau() {
		return false;
	}
}
