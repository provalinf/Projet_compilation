package arbre.instruction;

import arbre.BlocDInstructions;

public abstract class Instruction extends BlocDInstructions {

	public Instruction(int no) {
		super(no, null);
	}

	@Override
	public abstract void verifier();

	@Override
	public abstract String toMIPS();

	@Override
	public String toString(){
		return "Instr"+hashCode();
	}
}
