package arbre.fonctions;

import arbre.expression.Expression;
import arbre.instruction.Instruction;
import tds.TableSymbole;

public class RetourneFonction extends Instruction {
    Expression e;

    public RetourneFonction(Expression e){
        super(e.getNoLigne());
        this.e = e;
    }

    @Override
    public void verifier() {
		e.verifier();
    }

    @Override
    public String toMIPS() {
        StringBuilder sb = new StringBuilder();
        sb.append("# Retourne "+"\n");
        sb.append(e.toMIPS()+"\n");

        sb.append("\n");

        sb.append("# Revenir à la pile\n");
        sb.append("add $sp, $sp, " + TableSymbole.getInstance().getNoRegion() + "\n");

        sb.append("add $s7, $sp, 0\n");

        sb.append("# Adresse de retour\n");
        sb.append("lw $ra, 0($sp)\n");
        sb.append("add $sp, $sp, 4\n");

        sb.append("# Stockage retour\n");
        sb.append("lw $sp, 0($v0)\n ");

        sb.append("jr $ra\n");
        return sb.toString();
    }
}
