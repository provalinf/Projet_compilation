package arbre.fonctions;

import arbre.instruction.Instruction;
import exceptions.AnalyseSemantiqueException;
import tds.EntreeFonction;
import tds.Symbole;
import tds.SymboleFonction;
import tds.TableSymbole;

public class AppelFonction extends Instruction {
	private String idf;
	private String type;
	private SymboleFonction sf;

	public AppelFonction(String idf, int no) {
		super(no);
		this.idf = idf;
	}

	@Override
	public void verifier() {
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
		sb.append("jal " + sf.hashCode());

		sb.append("\n");

		sb.append("# Dépile retour \n");
		sb.append("add $sp, $sp, 4\n");
		sb.append("lw $v0, 0($sp)\n");
		sb.append("jr $ra\n");

		return sb.toString();
	}
}
