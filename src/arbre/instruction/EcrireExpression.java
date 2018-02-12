package arbre.instruction;

import arbre.expression.Expression;

public class EcrireExpression extends Ecrire{
    protected Expression exp;
    protected boolean b;

    protected EcrireExpression(Expression e ,int no, int numB) {
        super(no, numB);
        exp = e;
    }

    @Override
    public void verifier() {
        exp.verifier();
        if(exp.getType().equals("booleen")){
            b = true;
        }else{
            b = false;
        }
    }

    @Override
    public String toMIPS() {
        StringBuilder sb = new StringBuilder();
        sb.append("# Ecrire une expression\n");
        sb.append(exp.toMIPS());
        if(b){
            sb.append("si" + this.hashCode() + ": beqz $v0, sinon" + this.hashCode() + "\n");
            sb.append("alors" + this.hashCode() + ": .data\n");
            sb.append("str1_" + this.hashCode() + ":\t .asciiz \"vrai\"\n");
            sb.append(".text\n");
            sb.append("li $v0, 4\n");
            sb.append("la $a0, str1_" + this.hashCode() + "\n");
            sb.append("j finsi" + this.hashCode() + "\n");
            sb.append("sinon" + this.hashCode() + ": .data\n");
            sb.append("str2_" + this.hashCode() + ":\t .asciiz \"faux\"\n");
            sb.append(".text\n");
            sb.append("li $v0, 4\n");
            sb.append("la $a0, str2_" + this.hashCode() + "\n");
            sb.append("finsi" + this.hashCode() + ":\n");
            sb.append("syscall\n");
        }else{
            sb.append("move $t2, $v0\n");
            sb.append("li $v0, 1\n");
            sb.append("move $a0, $t2\n");
        }
        sb.append("syscall\n");

        return sb.toString();
    }
}
