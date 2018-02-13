package arbre;

import arbre.instruction.Instruction;

import java.util.ArrayList;

/**
 * 3 déc. 2015
 *
 * @author brigitte wrobel-dautcourt
 */

public class BlocDInstructions extends ArbreAbstrait {

    protected ArrayList<Instruction> bloc;

    public BlocDInstructions(int n) {
        super(n);
        bloc = new ArrayList<Instruction>();
    }

    @Override
    public void verifier() {
        for (Instruction inst:bloc) {
            inst.verifier();
        }
    }

    @Override
    public String toMIPS() {
        StringBuilder sb = new StringBuilder();
        for (Instruction instr:bloc) {
            sb.append(instr.toMIPS());
        }
        return sb.toString();
    }

    public void ajouter(Instruction a) {
        bloc.add(a);
    }

    @Override
    public String toString() {
        return bloc.toString();
    }

}
