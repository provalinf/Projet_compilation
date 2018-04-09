package tds;

public abstract class Symbole {
	private String type;
	private int dep;
	private int NRegion;
	private int NImbric;

	public Symbole(String type) {
		this(type, TableSymbole.getInstance().getDep(), TableSymbole.getInstance().getNoRegion(), TableSymbole.getInstance().getNoImbric());
	}

	public Symbole(String type, int dep, int NRegion, int NImbric) {
		this.type = type;
		this.dep = dep;
		this.NRegion = NRegion;
		this.NImbric = NImbric;
	}

	public String getType() {
		return type;
	}

	public int getDep() {
		return dep;
	}

	public int getNRegion() {
		return NRegion;
	}

	public int getNImbric() {
		return NImbric;
	}

	public abstract boolean isFonction();

	public abstract boolean isTableau();
}
