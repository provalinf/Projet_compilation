package tds;

/**
 * Created by Valentin.
 */
public class SymboleTableau extends Symbole {
	private int taille;
	public SymboleTableau(String type, String taille) {
		super(type);
		this.taille = Integer.parseInt(taille);
	}

	public SymboleTableau(String type, int taille, int dep, int NRegion, int NImbric) {
		super(type, dep, NRegion, NImbric);
		this.taille = taille;
	}

	public int getTabSize() {
		return taille;
	}

	@Override
	public boolean isFonction() {
		return false;
	}

	@Override
	public boolean isTableau() {
		return true;
	}
}
