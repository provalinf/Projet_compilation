package tds;

import arbre.expression.Expression;
import exceptions.AnalyseSemantiqueException;

public class Variable extends Expression {
	private String nom;
	private Entree e;

	public Variable(String nom, int no) {
		super(no);
		this.nom = nom;
		e = new Entree(nom);
		type = getSymbole(e).getType();
	}

	private Symbole getSymbole(Entree e) {
		return TableSymbole.getInstance().identifier(e);
	}

	@Override
	public void verifier() {
		if (getSymbole(e) != null) {
			nom = getSymbole(e).getType();
		} else {
			throw new AnalyseSemantiqueException("");
		}
	}

	@Override
	public String toMIPS() {
		return "lw $v0," + getSymbole(e).getDep() + "($s7)\n";
	}
}
