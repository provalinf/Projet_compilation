package arbre.expression;

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

    }

    @Override
    public String toMIPS() {
        StringBuilder res = new StringBuilder();
        res.append("##NonLogique\n");
        res.append(expression.toMIPS());
        res.append("move $v0, $t8\n");
        res.append("li $t8, 0\n");
        res.append("#Compare $t8 a $v0\n");
        res.append("seq $v0, $v0, $t8\n");
        res.append("move $t8, v0\n");
        return res.toString();
    }
}
