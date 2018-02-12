package tds;

import java.util.HashMap;

public class TableSymbole {

    private HashMap<Entree, Symbole> table;

    private TableSymbole() {
        table = new HashMap<>();
    }

    private static TableSymbole INSTANCE = new TableSymbole();

    public static TableSymbole getInstance()
    {
        return INSTANCE;
    }

    private void ajouter(Entree e, Symbole s) throws Exception {
        table.put(e, s);
    }

    public Symbole identifier(Entree e) throws Exception {
    	return null;
	}

}
