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
            throw new AnalyseSemantiqueException("Ligne "+getNoLigne()+" : "+cste+" doit être un entier");
        }else{
            type="entier";
        }
    }

    @Override
    public String toMIPS() {
        StringBuilder res = new StringBuilder();
        res.append("\n# Ranger "+cste+" dans $v0\n");
        res.append("li $v0, "+cste+"\n");
        res.append("sw $v0, ($sp)\n");
        res.append("addi $sp, $sp, -4\n");
        return res.toString();
    }
}
