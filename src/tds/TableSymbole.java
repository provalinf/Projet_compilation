package tds;

import exceptions.AnalyseSemantiqueException;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class TableSymbole {

	private HashMap<Entree, Symbole> table;
	private int noRegion = 0;
	private int noImbric = 0;
	private static TableSymbole INSTANCE = new TableSymbole();

	private TableSymbole() {
		table = new HashMap<>();
	}

	public static TableSymbole getInstance() {
		return INSTANCE;
	}

	public void ajouter(Entree e, Symbole s) {
		if (table.containsKey(e)) throw new AnalyseSemantiqueException("Erreur : " + e.getId() + " est déjà déclaré");
		table.put(e, s);
	}

	public Symbole identifier(Entree e) {
		Iterator it = table.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry) it.next();
			/*System.out.println(pair.getKey() + " = " + pair.getValue());*/
			if (((Entree) pair.getKey()).getId().equals(e.getId())) {
				return (Symbole) pair.getValue();
			}
		}
		return null;
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
