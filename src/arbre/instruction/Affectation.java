package arbre.instruction;

import arbre.expression.Expression;
import exceptions.AnalyseSemantiqueException;
import tds.Entree;
import tds.Symbole;
import tds.TableSymbole;

public class Affectation extends Instruction{

	private Expression expr;
	private Symbole symbole;
	private String idf;

	public Affectation(String idf, Expression e) {
		super(e.getNoLigne());
		expr = e;
		this.idf = idf;
		try {
			symbole = TableSymbole.getInstance().identifier(new Entree(idf));
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	public void verifier() {
		expr.verifier();
		if(!expr.getType().equals(symbole.getType())){
			throw new AnalyseSemantiqueException("Type identifiant invalide");
		}
	}

	@Override
	public String toMIPS() {
		StringBuilder sb = new StringBuilder();
		sb.append(expr.toMIPS());
		sb.append("sw $v0, " +symbole.getDep()+"($s7)\n");
		return sb.toString();
	}
}
