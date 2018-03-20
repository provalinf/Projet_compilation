package arbre.fonctions;

import arbre.BlocDInstructions;
import arbre.instruction.Instruction;
import exceptions.AnalyseSemantiqueException;
import tds.Entree;
import tds.TableSymbole;

public class FonctionsSansParam {

    private String type;
    private String nom;

    public FonctionsSansParam(String type, String nom, BlocDInstructions bloc) {
        this.type = type;
        this.nom = nom;
    }

    public void verifier() {

    }


    public String toMIPS() {
        StringBuilder sb = new StringBuilder();
        sb.append("# Fonction "+nom+"\n");
        sb.append(nom+":\n");

        return sb.toString();
    }

}
