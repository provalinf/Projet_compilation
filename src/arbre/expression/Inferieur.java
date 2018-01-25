package arbre.expression;

import exceptions.AnalyseSemantiqueException;

/**
 * 3 déc. 2015
 *
 * @author brigitte wrobel-dautcourt
 */

public class Inferieur extends Comparaison {

    public Inferieur(Expression gauche, Expression droite) {
        super(gauche, droite);
    }

    @Override
    public String operateur() {
        return " < ";
    }

    @Override
    public void verifier() {
        gauche.verifier();
        droite.verifier();
        if(!gauche.getType().equals(droite.getType())){
            throw new AnalyseSemantiqueException("Ligne "+noLigne+" : les opérandes de '<' doivent être du meme type");
        }
    }

    @Override
    public String toMIPS() {
        StringBuilder res = new StringBuilder();
        res.append("##Inferieur \n");
        res.append(gauche.toMIPS());
        res.append("move $v0, $t8\n");
        res.append(droite.toMIPS());
        res.append("#Compare $t8 à $v0\n");
        res.append("slt $v0, $v0, $t8\n");
        res.append("move $t8, $v0\n");
        return res.toString();
    }
}
