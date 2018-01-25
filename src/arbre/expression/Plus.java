package arbre.expression;

/**
 * 3 déc. 2015
 *
 * @author brigitte wrobel-dautcourt
 */

public class Plus extends BinaireArithmetique {

    public Plus(Expression gauche, Expression droite) {
        super(gauche, droite);
    }
    
    @Override
    public String operateur() {
        return " + " ;
    }

    @Override
    public void verifier() {

    }

    @Override
    public String toMIPS() {
        StringBuilder res = new StringBuilder();
        res.append(gauche.toMIPS());
        res.append("move $v0, $t8\n");
        res.append(droite.toMIPS());
        res.append("add $v0, $v0, $t8\n");
        res.append("move $t8, $v0\n");
        return res.toString();
    }
}
