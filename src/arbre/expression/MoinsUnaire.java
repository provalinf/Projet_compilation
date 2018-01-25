package arbre.expression;

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

    }

    @Override
    public String toMIPS() {
        StringBuilder res = new StringBuilder();
        res.append("##MoinsUnaire \n");
        res.append(expression.toMIPS());
        res.append("move $v0, $t8\n");
        res.append("#Ranger -1 dans $t8\n");
        res.append("li $t8, -1\n");
        res.append("#Multiplication $t8 à $v0\n");
        res.append("mul $v0, $v0, $t8\n");
        res.append("move $t8, $v0\n");
        return res.toString();
    }
}
