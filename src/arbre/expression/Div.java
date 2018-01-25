package arbre.expression;

/**
 * 3 déc. 2015
 *
 * @author brigitte wrobel-dautcourt
 */

public class Div extends BinaireArithmetique {

	public Div(Expression gauche, Expression droite) {
		super(gauche, droite);
	}

	@Override
	public String operateur() {
		return " / ";
	}

	@Override
	public void verifier() {

	}

	@Override
	public String toMIPS() {
		StringBuilder sb = new StringBuilder();
		sb.append("# Division\n");
		sb.append(gauche.toMIPS());
		sb.append("move $v0, $t8\n");
		sb.append(droite.toMIPS());
		sb.append("# Divise t8 à v0\n");
		sb.append("div $v0, $v0, $t8\n");
		sb.append("move $v0, $t8\n");
		return sb.toString();
	}
}
