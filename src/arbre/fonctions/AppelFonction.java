package arbre.fonctions;

import arbre.expression.Expression;
import arbre.instruction.Instruction;
import exceptions.AnalyseSemantiqueException;
import tds.EntreeFonction;
import tds.Symbole;
import tds.SymboleFonction;
import tds.TableSymbole;

public class AppelFonction extends Expression {
	private String idf;
	private SymboleFonction sf;

	public AppelFonction(String idf, int no) {
		super(no);
		this.idf = idf;
	}

	@Override
	public void verifier() {
		type = "entier";
		Symbole sf = TableSymbole.getInstance().identifier(new EntreeFonction(idf, 0));
		if (sf == null) {
			throw new AnalyseSemantiqueException("Ligne " + getNoLigne() + " : Fonction \"" + idf + "\" n'existe pas'");
		}else if (!sf.isFonction()) {
			throw new AnalyseSemantiqueException("Ligne " + getNoLigne() + " : \"" + idf + "\" n'est pas une fonction'");
		}
		this.sf = (SymboleFonction) sf;

	}

	@Override
	public String toMIPS() {
		StringBuilder sb = new StringBuilder();
		sb.append("#Appel fonction\n");
		sb.append("addi $sp, $sp, -4\n");
		sb.append("jal fonc_" + sf.hashCode());

		sb.append("\n");

		sb.append("# Dépile retour \n");
		sb.append("add $sp, $sp, 4\n");
		sb.append("lw $v0, 0($sp)\n");


		return sb.toString();
	}

	@Override
	public String toString() {
		return "AppelFonction{connnnard}";
	}
}
