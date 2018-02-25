package arbre.instruction;

import tds.Entree;
import tds.Symbole;
import tds.TableSymbole;

public class Lire extends Instruction {
    private String idf;
    private Symbole symbole;

    public Lire(String idf, int no) {
        super(no);
        this.idf=idf;
    }

    @Override
    public void verifier() {
        symbole = TableSymbole.getInstance().identifier(new Entree(idf));
    }

    @Override
    public String toMIPS() {
        StringBuilder sb = new StringBuilder();
        sb.append("li $v0, 5\n");	// Valeur au hasard
        sb.append("syscall\n");
        sb.append("sw $v0, "+symbole.getDep()+"($s7)\n");
        return sb.toString();
    }
}
