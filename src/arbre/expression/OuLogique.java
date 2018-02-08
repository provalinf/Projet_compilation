package arbre.expression;

import exceptions.AnalyseSemantiqueException;

/**
 * 3 déc. 2015
 *
 * @author brigitte wrobel-dautcourt
 */

public class OuLogique extends BinaireLogique {

    public OuLogique(Expression gauche, Expression droite) {
        super(gauche, droite);
    }
    
    @Override
    public String operateur() {
        return " ou " ;
    }

    @Override
    public void verifier() {
        gauche.verifier();
        droite.verifier();
        if(!gauche.getType().equals("booleen") || !droite.getType().equals("booleen")){
            throw new AnalyseSemantiqueException("Ligne "+getNoLigne()+" : les opérandes de 'ou' doivent être des booleens");
        }else{
            type="booleen";
        }
    }

    @Override
    public String toMIPS() {
        StringBuilder res = new StringBuilder();
        res.append("##OuLogique \n");
        res.append(gauche.toMIPS());
        res.append(droite.toMIPS());
        res.append("addi $sp, $sp 4\t# Ou logique\n");
        res.append("lw $v0, ($sp)\n");
        res.append("addi $sp, $sp 4\n");
        res.append("lw $t8, ($sp)\n");
        res.append("#Compare $t8 à $v0\n");
        res.append("or $v0, $v0, $t8\n");
        res.append("sw $v0, ($sp)\n");
        res.append("addi $sp, $sp, -4\n");
        return res.toString();
    }
}
