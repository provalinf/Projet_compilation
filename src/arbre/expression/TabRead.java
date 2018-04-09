package arbre.expression;

import exceptions.AnalyseSemantiqueException;
import tds.EntreeTableau;
import tds.Symbole;
import tds.SymboleTableau;
import tds.TableSymbole;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * Created by bonnal4u.
 */
public class TabRead extends Expression {

	private Expression e;
	private String idf;
	private Symbole symbole;
	private int position;

	public TabRead(int num, String idf, Expression e) {
		super(num);
		this.idf = idf;
		this.e = e;
	}

	@Override
	public void verifier() {
		e.verifier();
		symbole = TableSymbole.getInstance().identifier(new EntreeTableau(idf));
		if (symbole == null) {
			throw new AnalyseSemantiqueException("Ligne " + getNoLigne() + " : Tableau \"" + idf + "\" non déclaré");
		} else if (!((SymboleTableau) symbole).isTableau()) {
			throw new AnalyseSemantiqueException("Ligne " + getNoLigne() + " :\"" + idf + "\" n'est pas un tableau");
		}

		ScriptEngineManager mgr = new ScriptEngineManager();
		ScriptEngine engine = mgr.getEngineByName("JavaScript");
		try {
			position = Integer.parseInt(engine.eval(e.toString()).toString());
		} catch (ScriptException e1) {
			e1.printStackTrace();
		}

		if (position >= ((SymboleTableau) symbole).getTabSize()) {
			throw new AnalyseSemantiqueException("Ligne " + getNoLigne() + " : \"" + idf + "\" Index Out of Bound /!\\");
		}

		type = symbole.getType();
	}

	@Override
	public String toMIPS() {
		return null;
	}
}
