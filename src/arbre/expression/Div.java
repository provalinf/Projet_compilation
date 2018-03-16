package arbre.expression;

import exceptions.AnalyseDivisionParZeroException;
import exceptions.AnalyseSemantiqueException;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * 3 déc. 2015
 *
 * @author brigitte wrobel-dautcourt
 */

public class Div extends BinaireArithmetique {

	public Div(Expression gauche, Expression droite) {
		super(gauche, droite);
	}

	@Override
	public String operateur() {
		return " / ";
	}

	@Override
	public void verifier() {
		gauche.verifier();
		droite.verifier();
		if (!gauche.getType().equals("entier") || !droite.getType().equals("entier")) {
			throw new AnalyseSemantiqueException("Ligne " + getNoLigne() + " : les opérandes de division doivent être du type 'entier'");
		}

		ScriptEngineManager mgr = new ScriptEngineManager();
		ScriptEngine engine = mgr.getEngineByName("JavaScript");
		try {
			if (Double.parseDouble(engine.eval(droite.toString()).toString()) == 0.) {
				throw new AnalyseDivisionParZeroException("Ligne " + getNoLigne());
			} else {
				type = "entier";
			}
		} catch (ScriptException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String toMIPS() {
		StringBuilder sb = new StringBuilder();
		sb.append("\n## Division\n");
		sb.append(gauche.toMIPS());
		sb.append(droite.toMIPS());
		sb.append("addi $sp, $sp, 4\n");
		sb.append("lw $t8, ($sp)\n");
		sb.append("addi $sp, $sp, 4\n");
		sb.append("lw $v0, ($sp)\n");
		sb.append("# Divise t8 à v0\n");
		sb.append("div $v0, $v0, $t8\n");
		sb.append("sw $v0, ($sp)\n");
		sb.append("addi $sp, $sp, -4\n");
		return sb.toString();
	}
}
