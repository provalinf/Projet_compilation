package arbre.fonctions;

import arbre.instruction.Instruction;

public class AppelFonction extends Instruction {
    private String idf;
    private String type;

    public AppelFonction(String idf, int no){
        super(no);
    }

    @Override
    public void verifier() {

    }

    @Override
    public String toMIPS() {
        StringBuilder sb = new StringBuilder();
        sb.append("#Appel fonction\n");
        sb.append("addi $sp, $sp, -4\n");
        sb.append("jal "+idf);

        sb.append("\n");

        sb.append("# Dépile retour \n");
        sb.append("add $sp, $sp, 4\n");
        sb.append("lw $v0, 0($sp)\n");
        sb.append("jr $ra\n");

        return sb.toString();
    }
}
