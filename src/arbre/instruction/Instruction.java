package arbre.instruction;

import arbre.ArbreAbstrait;

public abstract class Instruction extends ArbreAbstrait{
    protected int numBloc;

    protected Instruction(int no, int numB) {
        super(no);
        numBloc = numB;
    }

    public int getNumBlock(){
        return numBloc;
    }

    public void setNumBloc(int n){
        numBloc = n;
    }

    @Override
    public abstract void verifier();

    @Override
    public abstract String toMIPS();
}
