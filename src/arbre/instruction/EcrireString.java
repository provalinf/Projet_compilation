package arbre.instruction;

public class EcrireString extends Ecrire {

	protected String str;

	public EcrireString(String s, int no) {
		super(no);
		str = s;
	}

	@Override
	public void verifier() {

	}

	@Override
	public String toMIPS() {
		StringBuilder sb = new StringBuilder();

		sb.append("\n# Ecrire une chaine de caractère\n");
		sb.append(".data\n");
		sb.append("str_" + hashCode() + " :\t");
		sb.append(".asciiz " + str + "\n");
		sb.append(".text\n");
		sb.append("li $v0, 4\n");
		sb.append("la $a0, str_" + hashCode() + "\n");
		sb.append("syscall\n");

		return sb.toString();
	}
}
