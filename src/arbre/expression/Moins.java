package arbre.expression;

import exceptions.AnalyseSemantiqueException;

/**
 * 3 déc. 2015
 *
 * @author brigitte wrobel-dautcourt
 */

public class Moins extends BinaireArithmetique {

	public Moins(Expression gauche, Expression droite) {
		super(gauche, droite);
	}

	@Override
	public String operateur() {
		return " - ";
	}

	@Override
	public void verifier() {
		gauche.verifier();
		droite.verifier();
		if(!gauche.getType().equals("entier") || !droite.getType().equals("entier")){
			throw new AnalyseSemantiqueException("Ligne "+getNoLigne()+" : les opérandes de soustraction doivent être du type 'entier'");
		}else{
			type="entier";
		}
	}

	@Override
	public String toMIPS() {
		StringBuilder sb = new StringBuilder();
		sb.append("\n## Soustraction\n");
		sb.append(gauche.toMIPS());

		sb.append("sw $v0, ($sp)\n");
		sb.append("addi $sp, $sp, -4\n");

		sb.append(droite.toMIPS());

		sb.append("addi $sp, $sp, 4\n");
		sb.append("lw $t8, ($sp)\n");

		sb.append("# Soustrait t8 à v0\n");
		sb.append("sub $v0, $t8, $v0\n");

		return sb.toString();
	}
}
