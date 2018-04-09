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

		sb.append("li $t8, -4\n");
		sb.append("mult $v0, $t8\n");
		sb.append("mflo $v0\n");

		sb.append("sw $v0, 0($sp)\n");
		sb.append("add $sp, $sp, -4\n");
		/*sb.append("sw $v0, " + symbole.getDep() + "($s7)\n");*/
		//((SymboleTableau)symbole).getEmplacement()

		sb.append(expr2.toMIPS());
		sb.append("add $sp, $sp, 4\n");
		sb.append("lw $t8, 0($sp)\n");
		sb.append("add $s7, $s7, "+TableSymbole.getInstance().getDep()+"\n");
		sb.append("add $s7, $s7, $t8\n");
		sb.append("sw $v0, ($s7)\n");
		sb.append("add $s7, $s7, "+TableSymbole.getInstance().getDep()+"\n");
		sb.append("sub $s7, $s7, $t8\n");
		System.out.println("HELP ME PLEASE !!");
		return sb.toString();
	}


}
