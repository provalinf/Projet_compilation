package arbre.expression;

import arbre.ArbreAbstrait;

/**
 * 3 déc. 2015
 *
 * @author brigitte wrobel-dautcourt
 */

public abstract class Expression extends ArbreAbstrait {

    protected String type;

    protected Expression(int n) {
        super(n) ;
    }

    public String getType(){
        return type;
    }
}
