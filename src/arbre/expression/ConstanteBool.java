package arbre.expression;

/**
 * 3 déc. 2015
 *
 * @author brigitte wrobel-dautcourt
 */

public class ConstanteBool extends Constante {
    
    public ConstanteBool(String texte, int n) {
        super(texte, n) ;
    }

    @Override
    public void verifier() {

    }

    @Override
    public String toMIPS() {
        String res;
        if(cste.equals("vrai")){
            res = "li $t8, 1";
        }else{
            res = "li $t8, 0";
        }
        return res;
    }
}
