package arbre.expression;

/**
 * 3 déc. 2015
 *
 * @author brigitte wrobel-dautcourt
 */

public class Different extends Comparaison {

    public Different(Expression gauche, Expression droite) {
        super(gauche, droite);
    }

    @Override
    public String operateur() {
        return " != ";
    }

    @Override
    public void verifier() {

    }

    @Override
    public String toMIPS() {
        StringBuilder res = new StringBuilder();
        res.append("##Different \n");
        res.append(gauche.toMIPS());
        res.append("move $v0, $t8\n");
        res.append(droite.toMIPS());
        res.append("#Compare $t8 à $v0\n");
        res.append("sne $v0, $v0, $t8\n");
        res.append("move $t8, $v0\n");
        return res.toString();
    }
}
