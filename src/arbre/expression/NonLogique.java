package arbre.expression;

import exceptions.AnalyseSemantiqueException;

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
        expression.verifier();
        if(!expression.getType().equals("booleen")){
            throw new AnalyseSemantiqueException("Ligne "+getNoLigne()+" : l'opérande de 'non' doit etre un booleen");
        }else{
            type="booleen";
        }
    }

    @Override
    public String toMIPS() {
        StringBuilder res = new StringBuilder();
        res.append("\n## NonLogique\n");
        res.append(expression.toMIPS());
        res.append("xori $v0, $v0, 1\n");
        return res.toString();
    }
}
