package arbre.expression;

import exceptions.AnalyseSemantiqueException;

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
		gauche.verifier();
		droite.verifier();
		if(droite.toString().equals("0")) {
			throw new AnalyseSemantiqueException("Ligne "+getNoLigne()+" : Divison par 0 impossible");
		}
		if(!gauche.getType().equals("entier") || !droite.getType().equals("entier")) {
			throw new AnalyseSemantiqueException("Ligne "+getNoLigne()+" : les opérandes de division doivent être du type 'entier'");
		}else{
			type="entier";
		}
	}

	@Override
	public String toMIPS() {
		StringBuilder sb = new StringBuilder();
		sb.append("# Division\n");
		sb.append(gauche.toMIPS());
		sb.append(droite.toMIPS());
		sb.append("addi $sp, $sp 4\n");
		sb.append("lw $t8, ($sp)\n");
		sb.append("addi $sp, $sp 4\n");
		sb.append("lw $v0, ($sp)\n");
		sb.append("# Divise t8 à v0\n");
		sb.append("div $v0, $v0, $t8\n");
		sb.append("sw $v0, ($sp)\n");
		sb.append("addi $sp, $sp, -4\n");
		return sb.toString();
	}
}
