package arbre.instruction;

import arbre.expression.Expression;

public class EcrireExpression extends Ecrire {
	protected Expression exp;
	protected boolean b;

	public EcrireExpression(Expression e) {
		super(e.getNoLigne());
		exp = e;
	}

	@Override
	public void verifier() {
		exp.verifier();
		b = exp.getType().equals("booleen");
	}

	@Override
	public String toMIPS() {
		StringBuilder sb = new StringBuilder();
		sb.append("# Ecrire une expression\n");
		sb.append(exp.toMIPS());
		if (b) {
			sb.append(".data\n");
			sb.append("v_"+hashCode()+": .asciiz \"vrai\"\n");
			sb.append("f_"+hashCode()+": .asciiz \"faux\"\n");
			sb.append(".text\n");
			sb.append("beqz $v0, alors_" + hashCode() + "\n");
			sb.append("la $a0, v_"+hashCode()+"\n");
			sb.append("j fin_" + hashCode() + "\n");
			sb.append("alors_" + hashCode() + " :\n");
			sb.append("la $a0, f_"+hashCode()+" \n");
			sb.append("fin_" + hashCode() + " :\n");
			sb.append("li $v0, 4\n");
		} else {
			sb.append("move $a0, $v0\n");
			sb.append("li $v0, 1\n");
		}
		sb.append("syscall\n");

		return sb.toString();
	}
}
