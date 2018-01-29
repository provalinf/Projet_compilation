package arbre.expression;

import exceptions.AnalyseSemantiqueException;

/**
 * 3 déc. 2015
 *
 * @author brigitte wrobel-dautcourt
 */

public class ConstanteBool extends Constante {
    
    public ConstanteBool(String texte, int n) {
        super(texte, n) ;
    }

    @Override
    public void verifier() {
        if(!cste.equals("vrai") && !cste.equals("faux")){
            throw new AnalyseSemantiqueException("Ligne "+getNoLigne()+" : "+cste+" doit être vrai ou faux");
        }else{
            type="booleen";
        }
    }

    @Override
    public String toMIPS() {
        StringBuilder res = new StringBuilder();
        if(cste.equals("vrai")){
            res.append("#Ranger 1 dans $t8\n");
            res.append("li $t8, 1\n");
        }else{
            res.append("#Ranger 0 dans $t8\n");
            res.append("li $t8, 0\n");
        }
        return res.toString();
    }
}
