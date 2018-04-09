package tds;

import arbre.expression.Expression;
import exceptions.AnalyseSemantiqueException;

public class Variable extends Expression {
	private String nom;
	private EntreeVariable e;

	public Variable(String nom, int no) {
		super(no);
		this.nom = nom;
		e = new EntreeVariable(nom);
		//System.out.println(e);
		type = getSymbole(e).getType();
	}

	private Symbole getSymbole(EntreeVariable e) {
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
