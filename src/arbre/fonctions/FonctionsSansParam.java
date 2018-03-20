package arbre.fonctions;

import arbre.BlocDInstructions;
import arbre.instruction.Instruction;
import tds.TableSymbole;

public class FonctionsSansParam extends Instruction{

    private String type;
    private String nom;
    private BlocDInstructions bloc;

    public FonctionsSansParam(String type, String nom, BlocDInstructions bloc) {
        super(bloc.getNoLigne());
        this.type = type;
        this.nom = nom;
        this.bloc = bloc;
    }

    public void verifier() {

    }


    public String toMIPS() {
        StringBuilder sb = new StringBuilder();
        sb.append("# Fonction "+nom+"\n");
        sb.append(nom+":\n");

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
        sb.append("li $v0, "+ TableSymbole.getInstance().getNoRegion()+"\n");
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
