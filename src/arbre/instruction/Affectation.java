package arbre.instruction;

import arbre.expression.Expression;
import exceptions.AnalyseSemantiqueException;
import tds.EntreeVariable;
import tds.Symbole;
import tds.TableSymbole;

public class Affectation extends Instruction {

	private Expression expr;
	private Symbole symbole;
	private String idf;

	public Affectation(String idf, Expression e) {
		super(e.getNoLigne());
		expr = e;
		this.idf = idf;
	}

	public void verifier() {
		expr.verifier();
		System.out.println(expr.toString());
		symbole = TableSymbole.getInstance().identifier(new EntreeVariable(idf));
		if (symbole == null)
			throw new AnalyseSemantiqueException("Ligne " + getNoLigne() + " : Identifiant \"" + idf + "\" non déclaré");
		if (!expr.getType().equals(symbole.getType())) {
			throw new AnalyseSemantiqueException("Ligne " + getNoLigne() + " : Type identifiant invalide");
		}
	}

	@Override
	public String toMIPS() {
		StringBuilder sb = new StringBuilder();
		sb.append(expr.toMIPS());
		sb.append("\n# Affectation\n");
		sb.append("sw $v0, " + symbole.getDep() + "($s7)\n");
		return sb.toString();
	}
}
