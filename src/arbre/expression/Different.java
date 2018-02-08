package arbre.expression;

import exceptions.AnalyseSemantiqueException;

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
        gauche.verifier();
        droite.verifier();
        if(!gauche.getType().equals(droite.getType())){
            throw new AnalyseSemantiqueException("Ligne "+getNoLigne()+" : les opérandes de '!=' doivent être du meme type");
        }else{
            type="booleen";
        }
    }

    @Override
    public String toMIPS() {
        StringBuilder res = new StringBuilder();
        res.append("##Different \n");
        res.append(gauche.toMIPS());
        res.append(droite.toMIPS());
        res.append("addi $sp, $sp 4\n");
        res.append("lw $t8, ($sp)\n");
        res.append("addi $sp, $sp 4\n");
        res.append("lw $v0, ($sp)\n");
        res.append("#Compare $t8 à $v0\n");
        res.append("sne $v0, $v0, $t8\n");
        res.append("sw $v0, ($sp)\n");
        res.append("addi $sp, $sp, -4\n");
        return res.toString();
    }
}
