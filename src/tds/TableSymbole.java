package tds;

import java.util.HashMap;

public class TableSymbole {

	private HashMap<Entree, Symbole> table;
	private int noBloc = 0;
	private int noImbric = 0;
	private TableSymbole() {
		table = new HashMap<>();
	}


	public static TableSymbole getInstance() {
		return new TableSymbole();
	}

	private void ajouter(Entree e, Symbole s) throws Exception {
		table.put(e, s);
	}

	public Symbole identifier(Entree e) {
		return table.get(e);
	}

	public void entreeBloc() {
		noBloc++;
		noImbric++;
	}

	public void sortieBloc() {
		noBloc--;
	}

	public int getNoBloc() {
		return noBloc;
	}

	public int getNoImbric() {
		return noImbric;
	}
}
