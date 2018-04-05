package arbre.fonctions;

import arbre.expression.Expression;
import arbre.instruction.Instruction;
import exceptions.AnalyseSemantiqueException;
import tds.EntreeFonction;
import tds.Symbole;
import tds.SymboleFonction;
import tds.TableSymbole;

public class RetourneFonction extends Instruction {
	private String nom;
	private Expression e;
	private SymboleFonction sf;

	public RetourneFonction(String nom, Expression e) {
		super(e.getNoLigne());
		this.e = e;
		this.nom = nom;
	}

	@Override
	public void verifier() {
		e.verifier();
		System.out.println("VERIFIE MOI");
		Symbole sf = TableSymbole.getInstance().identifier(new EntreeFonction(nom, 0));
		if (sf == null) {
			throw new AnalyseSemantiqueException("Ligne " + getNoLigne() + " : Fonction \"" + nom + "\" n'existe pas'");
		} else if (!sf.isFonction()) {
			throw new AnalyseSemantiqueException("Ligne " + getNoLigne() + " : \"" + nom + "\" n'est pas une fonction'");
		}
		this.sf = (SymboleFonction) sf;
	}

	@Override
	public String toMIPS() {
		StringBuilder sb = new StringBuilder();
		sb.append("# Retourne " + "\n");
		sb.append(e.toMIPS() + "\n");

		sb.append("\n");
		sb.append("sw $v0, 16($s7)\n");
		sb.append("j finfonc_" + sf.hashCode() + "\n");
		return sb.toString();
	}
}
