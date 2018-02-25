package arbre.instruction;

import arbre.BlocDInstructions;
import arbre.expression.Expression;
import arbre.instruction.Instruction;
import exceptions.AnalyseSemantiqueException;
import tds.Entree;
import tds.TableSymbole;

public class Boucle extends Instruction {
    private Expression e;
    private BlocDInstructions blocDInstructions;

    public Boucle(int no, Expression e, BlocDInstructions blocDInstructions) {
        super(no);
        this.e = e;
        this.blocDInstructions = blocDInstructions;
    }

    @Override
    public void verifier() {
        e.verifier();
        blocDInstructions.verifier();
        if (!e.getType().equals("booleen")) {
            throw new AnalyseSemantiqueException("Doit etre booleen");
        }
    }

    @Override
    public String toMIPS() {
        StringBuilder sb = new StringBuilder();
        sb.append("tantQue_" + hashCode() + " :\n");
        sb.append(e.toMIPS());
        sb.append("beqz $v0, finTantQue_" + hashCode() + "\n");
        if(blocDInstructions != null) sb.append(blocDInstructions.toMIPS());
        sb.append("j tantQue_" + hashCode() + "\n");
        sb.append("finTantQue_" + hashCode() + " :\n");
        return sb.toString();
    }
}
