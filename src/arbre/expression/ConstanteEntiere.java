package arbre.expression;

import exceptions.AnalyseException;
import exceptions.AnalyseSemantiqueException;

/**
 * 3 déc. 2015
 *
 * @author brigitte wrobel-dautcourt
 */

public class ConstanteEntiere extends Constante {
    
    public ConstanteEntiere(String texte, int n) { super(texte, n) ; }

    @Override
    public void verifier() {
        if(!cste.matches("-?\\d+")){
            throw new AnalyseSemantiqueException("Ligne "+noLigne+" : "+cste+" doit être un entier");
        }
    }

    @Override
    public String toMIPS() {
        StringBuilder res = new StringBuilder();
        res.append("#Ranger "+cste+" dans $t8\n");
        res.append("li $t8, "+cste+"\n");
        return res.toString();
    }
}
