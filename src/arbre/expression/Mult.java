package arbre.expression;

/**
 * 3 déc. 2015
 *
 * @author brigitte wrobel-dautcourt
 */

public class Mult extends BinaireArithmetique {

    public Mult(Expression gauche, Expression droite) {
        super(gauche, droite);
    }
  
    @Override
    public String operateur() {
        return " * ";
    }

    @Override
    public void verifier() {

    }

    @Override
    public String toMIPS() {
        StringBuilder res = new StringBuilder();
        res.append("##Multiplication \n");
        res.append(gauche.toMIPS());
        res.append("move $v0, $t8\n");
        res.append(droite.toMIPS());
        res.append("#Multiplication $t8 à $v0\n");
        res.append("mul $v0, $v0, $t8\n");
        res.append("move $t8, $v0\n");
        return res.toString();
    }
}
