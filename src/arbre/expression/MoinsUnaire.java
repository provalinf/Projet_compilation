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
        res.append("\n## MoinsUnaire \n");
        res.append(expression.toMIPS());
        res.append("sw $v0, ($sp)\n");

        res.append("addi $sp, $sp, -4\n");
        res.append("li $v0, 0\n");
        res.append("addi $sp, $sp, 4\n");

        res.append("lw $t8, ($sp)\n");
        res.append("sub $v0, $v0, $t8\n");
        return res.toString();
    }
}
