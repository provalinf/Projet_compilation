package arbre.fonctions;

import arbre.BlocDInstructions;
import arbre.instruction.Instruction;
import exceptions.AnalyseSemantiqueException;
import tds.EntreeFonction;
import tds.Symbole;
import tds.SymboleFonction;
import tds.TableSymbole;

public class FonctionsSansParam extends Instruction {

	private String type;
	private String nom;
	private BlocDInstructions bloc;
	private SymboleFonction sf;

	public FonctionsSansParam(String type, String nom, BlocDInstructions bloc) {
		super(bloc.getNoLigne());
		this.type = type;
		this.nom = nom;
		this.bloc = bloc;
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
	}


	public String toMIPS() {
		StringBuilder sb = new StringBuilder();
		sb.append("# Fonction " + nom + "\n");
		sb.append("fonc_"+sf.hashCode() + ":\n");

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




		return sb.toString();
	}

}
