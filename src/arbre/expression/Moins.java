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
		if(!gauche.getType().equals("entier") && !droite.getType().equals("entier")){
			throw new AnalyseSemantiqueException("Ligne "+noLigne+" : les opérandes de soustraction doivent être du type 'entier'");
		}else{
			type="entier";
		}
	}

	@Override
	public String toMIPS() {
		StringBuilder sb = new StringBuilder();
		sb.append("# Soustraction\n");
		sb.append(gauche.toMIPS());
		sb.append("move $v0, $t8\n");
		sb.append(droite.toMIPS());
		sb.append("# Soustrait t8 à v0\n");
		sb.append("sub $v0, $v0, $t8\n");
		sb.append("move $v0, $t8\n");
		return sb.toString();
	}
}
