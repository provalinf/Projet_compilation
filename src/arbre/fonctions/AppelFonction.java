package arbre.fonctions;

import arbre.expression.Expression;
import arbre.instruction.Instruction;
import exceptions.AnalyseSemantiqueException;
import tds.EntreeFonction;
import tds.Symbole;
import tds.SymboleFonction;
import tds.TableSymbole;

import java.util.ArrayList;

public class AppelFonction extends Expression {
	private String idf;
	private SymboleFonction sf;
	private ArrayList<Expression> params;

	public AppelFonction(String idf, int no) {
		super(no);
		this.idf = idf;
	}

	public AppelFonction(String idf, int no, ArrayList<Expression> params) {
		this(idf, no);
		//System.out.println("blblblblblbl"+params);
		this.params = params;
		type = "entier";
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
		for (Expression e : this.params){
			sb.append(e.toMIPS());
			sb.append("sw $v0, ($sp)\n");
			sb.append("add $sp, $sp, -4\n");
		}

		sb.append("addi $sp, $sp, -4\n");
		sb.append("jal fonc_" + sf.hashCode());

		sb.append("\n");

		sb.append("# Dépile retour \n");
		sb.append("addi $sp, $sp, 4\n");
		sb.append("lw $v0, 0($sp)\n");

		sb.append("add $sp, $sp, " + params.size() * 4 + "\n");
		return sb.toString();
	}
}
