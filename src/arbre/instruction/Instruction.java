package arbre.instruction;

import arbre.ArbreAbstrait;

public abstract class Instruction extends ArbreAbstrait{

    protected Instruction(int no) {
        super(no);
    }

    @Override
    public abstract void verifier();

    @Override
    public abstract String toMIPS();
}
