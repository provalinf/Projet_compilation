package arbre.instruction;

import arbre.BlocDInstructions;
import arbre.expression.Expression;
import exceptions.AnalyseSemantiqueException;

public class Condition extends Instruction {
	private Expression si;
	private BlocDInstructions alors;
	private BlocDInstructions sinon;

	public Condition(Expression si, BlocDInstructions alors, BlocDInstructions sinon, int no) {
		super(no);
		this.si = si;
		this.alors = alors;
		this.sinon = sinon;
	}

	@Override
	public void verifier() {
		si.verifier();
		if (!si.getType().equals("booleen")) {
			throw new AnalyseSemantiqueException("Doit etre booleen");
		}
		if (alors != null) {
			alors.verifier();
		}
		if (sinon != null) {
			sinon.verifier();
		}
	}

	@Override
	public String toMIPS() {
		StringBuilder sb = new StringBuilder();
		sb.append("si_" + hashCode() + " :\n");
		sb.append(si.toMIPS());


		if (sinon != null)
			sb.append("beqz $v0, sinon_" + hashCode() + "\n");
		else
			sb.append("beqz $v0, fin_" + hashCode() + "\n");



		sb.append("alors_" + hashCode() + " :\n");
		sb.append(alors.toMIPS());
		sb.append("j fin_" + hashCode() + "\n");
		if (sinon != null) {
			sb.append("sinon_" + hashCode() + " :\n");
			sb.append(sinon.toMIPS());
		}
		sb.append("fin_" + hashCode() + " :\n");
		return sb.toString();
	}
}
