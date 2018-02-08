package arbre.expression;

import exceptions.AnalyseSemantiqueException;

/**
 * 3 déc. 2015
 *
 * @author brigitte wrobel-dautcourt
 */

public class MoinsUnaire extends Unaire {
    
    public MoinsUnaire(Expression expr) {
        super(expr);
    }

    @Override
    public String operateur() {
        return "- " ;
    }

    @Override
    public void verifier() {
        expression.verifier();
        if(!expression.getType().equals("entier")){
            throw new AnalyseSemantiqueException("Ligne "+getNoLigne()+" : l'opérande de 'moins unaire' doit être du type 'entier'");
        }else{
            type="entier";
        }
    }

    @Override
    public String toMIPS() {
        StringBuilder res = new StringBuilder();
        res.append("##MoinsUnaire \n");
        res.append(expression.toMIPS());
        res.append("addi $sp, $sp, 4\n");
        res.append("lw $v0, ($sp)\n");
        res.append("#Ranger -1 dans $t8\n");
        res.append("li $t8, -1\n");
        res.append("#Multiplication $t8 à $v0\n");
        res.append("mul $v0, $v0, $t8\n");
        res.append("sw $v0, ($sp)\n");
        res.append("addi $sp, $sp, -4\n");
        return res.toString();
    }
}
