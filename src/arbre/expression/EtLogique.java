package arbre.expression;

import exceptions.AnalyseSemantiqueException;

/**
 * 3 déc. 2015
 *
 * @author brigitte wrobel-dautcourt
 */

public class EtLogique extends BinaireLogique {

    public EtLogique(Expression gauche, Expression droite) {
        super(gauche, droite);
    }
    
    @Override
    public String operateur() {
        return " et " ;
    }

    @Override
    public void verifier() {
        gauche.verifier();
        droite.verifier();
        if(!gauche.getType().equals("booleen") && !droite.getType().equals("booleen")){
            throw new AnalyseSemantiqueException("Ligne "+noLigne+" : les opérandes de 'et' doivent être des booleens");
        }else{
            type="booleen";
        }
    }

    @Override
    public String toMIPS() {
        StringBuilder res = new StringBuilder();
        res.append("##EtLogique \n");
        res.append(gauche.toMIPS());
        res.append("move $v0, $t8\n");
        res.append(droite.toMIPS());
        res.append("#Compare $t8 à $v0\n");
        res.append("and $v0, $v0, $t8\n");
        res.append("move $t8, $v0\n");
        return res.toString();
    }
}
