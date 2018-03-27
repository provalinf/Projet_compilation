package arbre.fonctions;

import arbre.BlocDInstructions;
import arbre.instruction.Instruction;
import exceptions.AnalyseSemantiqueException;
import tds.EntreeFonction;
import tds.Symbole;
import tds.SymboleFonction;
import tds.TableSymbole;

public class FonctionsSansParam extends Instruction {

	private String nom;
	private BlocDInstructions bloc;
	private SymboleFonction sf;
	private Instruction i;

	public FonctionsSansParam(String nom, BlocDInstructions bloc, Instruction i) {
		super(bloc.getNoLigne());
		this.nom = nom;
		this.bloc = bloc;
		this.i = i;
	}

	public void verifier() {

		Symbole sf = TableSymbole.getInstance().identifier(new EntreeFonction(nom, 0));
		TableSymbole.getInstance().entreeBloc();
		bloc.verifier();
		TableSymbole.getInstance().sortieBloc();
		if (sf == null) {
			throw new AnalyseSemantiqueException("Ligne " + getNoLigne() + " : Fonction \"" + nom + "\" non déclarée");
		}

		this.sf = (SymboleFonction) sf;
		if (i != null) i.verifier();
	}

	public String toMIPS() {
		StringBuilder sb = new StringBuilder();
		sb.append("# Fonction " + nom + "\n");
		sb.append("j pilefonc_" + sf.hashCode() + "\n");

		sb.append("fonc_" + sf.hashCode() + ":\n");

		sb.append("\n");

		sb.append("# Sauvegarder adresse de retour\n");
		sb.append("sw $ra, ($sp)\n");
		sb.append("addi $sp, $sp, -4\n");

		sb.append("\n");

		sb.append("# Sauvegarder base locale dans la pile\n");
		sb.append("sw $s7, ($sp)\n");
		sb.append("addi $sp, $sp, -4\n");

		sb.append("\n");

		sb.append("# Ajout num de bloc\n");
		sb.append("li $v0, " + TableSymbole.getInstance().getNoRegion() + "\n");
		sb.append("sw $v0, ($sp)\n");
		sb.append("addi $sp, $sp, -4\n");

		sb.append("\n");

		sb.append("# Déplacement base\n");
		sb.append("move $s7, $sp\n");

		sb.append("\n");

		// Réservation variable locale (yal 4)
		// Pas fait

		sb.append("# Instructions fonction\n");
		sb.append(bloc.toMIPS());
		if (i != null) i.toMIPS();

		sb.append("finfonc_" + sf.hashCode() + ":\n");

		sb.append("lw $ra, 12($s7)\n");
		sb.append("addi $sp, $s7, 12\n");
		sb.append("lw $s7, 8($s7)\n");
		sb.append("jr $ra\n");

		sb.append("pilefonc_" + sf.hashCode() + ":\n");


		return sb.toString();
	}

}
