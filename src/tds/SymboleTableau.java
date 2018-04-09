package tds;

import arbre.expression.Expression;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * Created by Valentin.
 */
public class SymboleTableau extends Symbole {
	private int taille;
	private int posDep;

	public SymboleTableau(String type, Expression e) {
		super(type);
		posDep = TableSymbole.getInstance().getDep();
		defineSize(e);
	}

	private void defineSize(Expression e) {
		ScriptEngineManager mgr = new ScriptEngineManager();
		ScriptEngine engine = mgr.getEngineByName("JavaScript");
		try {
			taille = Integer.parseInt(engine.eval(e.toString()).toString());
		} catch (ScriptException e1) {
			e1.printStackTrace();
		}
	}

	public SymboleTableau(String type, Expression e, int dep, int NRegion, int NImbric) {
		super(type, dep, NRegion, NImbric);
		posDep = TableSymbole.getInstance().getDep();
		defineSize(e);
	}

	public int getEmplacement(int key) {
		return posDep + key * -4;
	}

	public int getTabSize() {
		return taille;
	}

	@Override
	public boolean isFonction() {
		return false;
	}

	@Override
	public boolean isTableau() {
		return true;
	}
}
