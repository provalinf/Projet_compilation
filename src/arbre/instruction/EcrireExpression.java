package arbre.instruction;

import arbre.expression.Expression;

public class EcrireExpression extends Ecrire{
    protected Expression exp;
    protected boolean b;

    public EcrireExpression(Expression e ,int no) {
        super(no);
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
            sb.append("beqz $v0, alors_" + hashCode() + "\n");
            sb.append("la $a0, 1\n");
            sb.append("j fin_" + hashCode() + "\n");
            sb.append("alors_" + hashCode() + " :\n");
            sb.append("la $a0, 0 \n");
            sb.append("fin_" + hashCode() + " :\n");
            sb.append("li $v0, 4\n");
        }else{
            sb.append("move $a0, $v0\n");
            sb.append("li $v0, 1\n");
        }
        sb.append("syscall\n");

        return sb.toString();
    }
}
