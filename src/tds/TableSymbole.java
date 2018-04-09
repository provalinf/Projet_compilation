package tds;

import exceptions.AnalyseSemantiqueException;

import java.util.*;
import java.util.stream.Collectors;

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
		Iterator it = table.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry) it.next();
			if (((Entree) pair.getKey()).getId().equals(e.getId())) {
				throw new AnalyseSemantiqueException("Erreur : " + e.getId() + " est déjà déclaré");
			}
		}
		//if (table.containsKey(e)) throw new AnalyseSemantiqueException("Erreur : " + e.getId() + " est déjà déclaré");	// Ne fonctionne pas...........
		table.put(e, s);
	}

	public Symbole identifier(Entree e) {
		Iterator it = table.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry) it.next();
			if (((Entree) pair.getKey()).getId().equals(e.getId())) {
				return (Symbole) pair.getValue();
			}
		}
		return null;
		//return table.get(e);	// Ne fonctionne pas non plus............
	}

	public void entreeBloc() {
		noRegion++;
		noImbric++;
	}

	public void sortieBloc() {
		noRegion--;
	}

	public int getDep() {
		int nbTab = (int) table.entrySet().stream().filter(map -> map.getValue().isTableau()).count();
		int allTabSize = table.entrySet().stream().filter(map -> map.getValue().isTableau()).map(map -> ((SymboleTableau) map.getValue()).getTabSize()).mapToInt(Integer::intValue).sum();
		//System.out.println("nb tableau : " + nbTab + ", somme taille : " + allTabSize);
		return (table.size() - nbTab + allTabSize) * -4;
	}

	public int getNoRegion() {
		return noRegion;
	}

	public int getNoImbric() {
		return noImbric;
	}
}
