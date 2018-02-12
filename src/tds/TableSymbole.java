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

    private void ajouter(Entree e, Symbole s){
        table.put(e, s);
    }

}
