package tds;

import exceptions.AnalyseSemantiqueException;

import java.util.HashMap;

public class TableSymbole {

	private HashMap<Entree, Symbole> table;
	private int noRegion = 0;
	private int noImbric = 0;

	private TableSymbole() {
		table = new HashMap<>();
	}

	public static TableSymbole getInstance() {
		return new TableSymbole();
	}

	public void ajouter(Entree e, Symbole s) {
		if (table.containsKey(e)) throw new AnalyseSemantiqueException("Erreur : " + e.getId() + " est déjà déclaré");
		table.put(e, s);
	}

	public Symbole identifier(Entree e) {
		return table.get(e);
	}

	public void entreeBloc() {
		noRegion++;
		noImbric++;
	}

	public void sortieBloc() {
		noRegion--;
	}

	public int getDep() {
		return table.size() * -4;
	}

	public int getNoRegion() {
		return noRegion;
	}

	public int getNoImbric() {
		return noImbric;
	}
}
