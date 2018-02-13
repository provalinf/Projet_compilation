package arbre.instruction;

import arbre.BlocDInstructions;

public abstract class Instruction extends BlocDInstructions {

	public Instruction(int no) {
		super(no);
	}

	@Override
	public abstract void verifier();

	@Override
	public abstract String toMIPS();
}
