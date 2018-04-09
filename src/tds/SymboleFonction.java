package tds;

public class SymboleFonction extends Symbole {
	public SymboleFonction(String type) {
		super(type);
	}

	public SymboleFonction(String type, int dep, int NRegion, int NImbric) {
		super(type, dep, NRegion, NImbric);
	}

	@Override
	public boolean isFonction() {
		return true;
	}

	@Override
	public boolean isTableau() {
		return false;
	}
}
