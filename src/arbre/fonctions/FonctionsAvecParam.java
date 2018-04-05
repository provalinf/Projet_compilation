package arbre.fonctions;

import arbre.BlocDInstructions;
import arbre.instruction.Instruction;
import exceptions.AnalyseSemantiqueException;
import tds.EntreeFonction;
import tds.Symbole;
import tds.SymboleFonction;
import tds.TableSymbole;

import java.util.HashMap;

public class FonctionsAvecParam extends Instruction {

    private String nom;
    private BlocDInstructions bloc;
    private HashMap<String,String> params;
    private SymboleFonction sf;

    public FonctionsAvecParam(String nom, BlocDInstructions bloc, HashMap<String,String> params) {
        super(bloc.getNoLigne());
        this.nom = nom;
        this.params = params;
        this.bloc = bloc;
    }

    public void verifier() {
		System.out.println("numBloc2 "+TableSymbole.getInstance().getNoRegion());
        Symbole sf = TableSymbole.getInstance().identifier(new EntreeFonction(nom, params.size()));
        TableSymbole.getInstance().entreeBloc();
		System.out.println("numBloc2 "+TableSymbole.getInstance().getNoRegion());
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


    /*public class Fonction extends ArbreAbstrait {
    private String nom;
    private BlocDInstructions li;
    private BlocDInstructions param;
    private Stack<Integer> pile;

    public Fonction (int n, String nom, BlocDInstructions li, BlocDInstructions param) {
        super(n);
        this.nom = nom;
        this.li = li;
        this.param = param;
        pile = Declaration.getInstance().getPileClone();
      //  System.out.println(nom+pile.toString());
    }

    @Override
    public String toMIPS() {
        StringBuilder mips = new StringBuilder();
        mips.append(nom+":\n");
        mips.append("\tsw $ra, ($sp)\n");
        mips.append("\taddi $sp, $sp, -4\n");
        mips.append("\tli $v0, "+pile.get((pile.size()>=2) ? pile.size()-2 : pile.size()-1)+"\n");
        mips.append("\tsw $v0, ($sp)\n");
        mips.append("\taddi $sp, $sp, -4\n");
        mips.append("\tli $v0, "+pile.peek()+"\n");
        mips.append("\tsw, $v0, ($sp)\n");
        int nbvar = Declaration.getInstance().getNbVars(pile.peek());
        mips.append("\taddi $sp, $sp, "+(-4*nbvar+-4)+"\n");
        mips.append(li.toMIPS());
        mips.append("\taddi, $sp, $sp, -4\n");
        mips.append("\tlw $ra, ($sp)\n");
        mips.append("\taddi, $sp, $sp, 4\n");
        mips.append("\tjr $ra\n");
        mips.append(li.toMIPS());
        return mips.toString();
    }

    @Override
    public void verifier() {
        boolean retourne = false;
        Declaration.getInstance().doubleDeclFonc(nom, param.getSize(), pile);
        Iterator<ArbreAbstrait> arbre = li.getInstruction();
        ArbreAbstrait a = null;
        while (arbre.hasNext()){
            a = arbre.next();
            a.verifier();
            if (Declaration.getInstance().getRetourne()){
                retourne = true;
            }
        }
        System.out.println(retourne);
        if (!a.getClass().toString().equals("class yal.arbre.instruction.Retourne") && retourne == false){
            throw new AnalyseSemantiqueException(a.getNoLigne(), "La fonction " + nom + " doit se terminer par un l'instruction retourne");
        }

        li.verifier();
    }

    @Override
    public String toString() {
        return  "fonction " + nom + "() " + "debut " +  ((li != null) ? (li) : ("")) + " fin";
    }
}*/
//}
}
