package arbre.expression;

import exceptions.AnalyseSemantiqueException;

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
        gauche.verifier();
        droite.verifier();
        if(!gauche.getType().equals("entier") || !droite.getType().equals("entier")){
            throw new AnalyseSemantiqueException("Ligne "+getNoLigne()+" : les opérandes de multiplication doivent être du type 'entier'");
        }else{
            type="entier";
        }
    }

    @Override
    public String toMIPS() {
        StringBuilder res = new StringBuilder();
        res.append("\n## Multiplication \n");
        res.append(gauche.toMIPS());

        res.append("sw $v0, ($sp)\n");
        res.append("addi $sp, $sp, -4\n");

        res.append(droite.toMIPS());

        res.append("addi $sp, $sp, 4\n");
        res.append("lw $t8, ($sp)\n");

        res.append("#Multiplication $t8 à $v0\n");
        res.append("mult $t8, $v0\n");
        res.append("mflo $v0\n");

        return res.toString();
    }
}
