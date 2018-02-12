package tds;

import exceptions.AnalyseSemantiqueException;

import java.util.HashMap;

public class TableSymbole {

    private HashMap<Entree, Symbole> table;

    private TableSymbole() {
        table = new HashMap<>();
    }


    public static TableSymbole getInstance()
    {
        return new TableSymbole();
    }

    private void ajouter(Entree e, Symbole s) throws Exception {
        table.put(e, s);
    }

    public Symbole identifier(Entree e) throws Exception {
    	if(table.get(e) != null) return table.get(e);
    	else throw new AnalyseSemantiqueException("identifier");
    }

    public void entreeBloc(){

    }

    public void sortieBloc(){

    }

}
