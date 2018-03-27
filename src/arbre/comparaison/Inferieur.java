package arbre.comparaison;

import arbre.expression.Expression;
import exceptions.AnalyseSemantiqueException;

/**
 * 3 déc. 2015
 *
 * @author brigitte wrobel-dautcourt
 */

public class Inferieur extends Comparaison {

	public Inferieur(Expression gauche, Expression droite) {
		super(gauche, droite);
	}

	@Override
	public String operateur() {
		return " < ";
	}

	@Override
	public void verifier() {
		gauche.verifier();
		droite.verifier();
		if (!gauche.getType().equals(droite.getType()) || !gauche.getType().equals("entier")) {
			throw new AnalyseSemantiqueException("Ligne " + getNoLigne() + " : les opérandes de '<' doivent être de même type et entier");
		} else {
			type = "booleen";
		}
	}

	@Override
	public String toMIPS() {
		StringBuilder res = new StringBuilder();
		res.append("\n# Inférieur \n");
		res.append(gauche.toMIPS());

		res.append("sw $v0, ($sp)\n");
		res.append("addi $sp, $sp, -4\n");

		res.append(droite.toMIPS());

		res.append("addi $sp, $sp, 4\n");
		res.append("lw $t8, ($sp)\n");

		res.append("#Compare $t8 à $v0\n");
		res.append("slt $v0, $t8, $v0\n");

		return res.toString();
	}
}
