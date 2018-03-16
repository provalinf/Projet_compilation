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
            res.append("\n# Ranger 1(vrai) dans $v0\n");
            res.append("li $v0, 1\n");
        }else{
            res.append("\n# Ranger 0(faux) dans $v0\n");
            res.append("li $v0, 0\n");
        }
        res.append("sw $v0, ($sp)\n");
        res.append("addi $sp, $sp, -4\n");
        return res.toString();
    }
}
