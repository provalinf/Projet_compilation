package arbre;

import arbre.instruction.Instruction;
import tds.TableSymbole;

import java.util.ArrayList;

/**
 * 3 déc. 2015
 *
 * @author brigitte wrobel-dautcourt
 */

public class BlocDInstructions extends ArbreAbstrait {

    protected ArrayList<Instruction> bloc;
    protected BlocDInstructions ldecl;

    public BlocDInstructions(int n, BlocDInstructions ld) {
        super(n);
        bloc = new ArrayList<Instruction>();
        ldecl = ld;
    }

    @Override
    public void verifier() {
        //System.out.println("ldecl :"+ldecl);
        if(ldecl != null){
            for (Instruction inst : ldecl.getBloc()) {
                inst.verifier();
            }
        }
        for (Instruction inst : bloc) {
            inst.verifier();
        }
    }

    @Override
    public String toMIPS() {
        StringBuilder sb = new StringBuilder();
		/*sb.append("move $s7, $sp\n");
		//Allocation
		sb.append("addi $sp, $sp, "+ TableSymbole.getInstance().getDep()+"\n");

		// Déplacement
		for (int i = 0; i > TableSymbole.getInstance().getDep(); i-=4) {
			sb.append("sw $t8, "+i+"($s7)\n");
		}*/

        if(ldecl != null){
            for (Instruction inst : ldecl.getBloc()) {
                sb.append(inst.toMIPS());
            }
        }

        for (Instruction instr : bloc) {
            sb.append(instr.toMIPS());
        }

        //sb.append("\nend:\nmove $v1, $v0\nli $v0,10\nsyscall");
        return sb.toString();
    }

    public void addLDecl(BlocDInstructions b){
        ldecl = b;
    }

    public void ajouter(Instruction a) {
        bloc.add(a);
    }

    public int getsizeBloc() {
        return bloc.size();
    }

    public ArrayList<Instruction> getBloc() {
        return bloc;
    }

    @Override
    public String toString() {
        return bloc.toString();
    }

}
