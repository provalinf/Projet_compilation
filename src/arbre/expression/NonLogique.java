package arbre.expression;

import exceptions.AnalyseSemantiqueException;

/**
 * 3 déc. 2015
 *
 * @author brigitte wrobel-dautcourt
 */

public class NonLogique extends Unaire {
    
    public NonLogique(Expression expr) {
        super(expr);
    }

    @Override
    public String operateur() {
        return " non " ;
    }

    @Override
    public void verifier() {
        expression.verifier();
        if(!expression.getType().equals("booleen")){
            throw new AnalyseSemantiqueException("Ligne "+getNoLigne()+" : l'opérande de 'non' doit etre un booleen");
        }else{
            type="booleen";
        }
    }

    @Override
    public String toMIPS() {
        StringBuilder res = new StringBuilder();
        res.append("##NonLogique\n");
        res.append(expression.toMIPS());
        res.append("addi $sp, $sp, 4\n");
        res.append("lw $v0, ($sp)\n");
        res.append("li $t8, 0\n");
        res.append("#Compare $t8 a $v0\n");
        res.append("seq $v0, $v0, $t8\n");
        res.append("sw $v0, ($sp)\n");
        res.append("addi $sp, $sp, -4\n");
        return res.toString();
    }
}
