package arbre.instruction;

public class EcrireString extends Ecrire{

    protected String str;

    protected EcrireString(String s, int no, int numB) {
        super(no, numB);
        str = s;
    }

    @Override
    public void verifier() {

    }

    @Override
    public String toMIPS() {
        StringBuilder sb = new StringBuilder();

        sb.append("#Ecrire une chaine :\n");
        sb.append(".data\n");
        sb.append("str" + this.hashCode() + ":\t .asciiz " + str.replace("\"\"", "\\\"") + "\n");
        sb.append(".text\n");
        sb.append("li $v0, 4\n");
        sb.append("la $a0, str" + this.hashCode() + "\n");
        sb.append("syscall\n");

        return sb.toString();
    }
}
