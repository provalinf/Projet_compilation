package arbre.instruction;

import arbre.expression.Expression;
import exceptions.AnalyseSemantiqueException;
import tds.*;

public class AffectationTab extends Instruction {

	private Expression expr1, expr2;
	private Symbole symbole;
	private String idf;

	public AffectationTab(String idf, Expression e1, Expression e2) {
		super(e1.getNoLigne());
		expr1 = e1;
		expr2 = e2;
		this.idf = idf;
	}

	public void verifier() {
		expr1.verifier();
		expr2.verifier();
		symbole = TableSymbole.getInstance().identifier(new EntreeTableau(idf));
		if (symbole == null)
			throw new AnalyseSemantiqueException("Ligne " + getNoLigne() + " : Identifiant \"" + idf + "\" non déclaré");
		else if (!symbole.isTableau()) {
			throw new AnalyseSemantiqueException("Ligne " + getNoLigne() + " :\"" + idf + "\" n'est pas un tableau");
		}
		if (!expr1.getType().equals("entier")) {
			throw new AnalyseSemantiqueException("Ligne " + getNoLigne() + " : Position requise invalide");
		} else if (!symbole.getType().equals(expr2.getType())) {
			throw new AnalyseSemantiqueException("Ligne " + getNoLigne() + " : Types incompatibles");
		}
	}

	@Override
	public String toMIPS() {
		StringBuilder sb = new StringBuilder();
		sb.append(expr1.toMIPS());
		System.out.println("HELP ME !!");
		sb.append("\n# Affectation tab\n");
		/*sb.append("sw $v0, " + symbole.getDep() + "($s7)\n");*/

		sb.append(expr2.toMIPS());
		System.out.println("HELP ME PLEASE !!");
		return sb.toString();
	}


}
