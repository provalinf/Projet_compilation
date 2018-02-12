package tds;

/**
 * Created by bonnal4u.
 */
public class Variable {
	private String nom;
	private Entree e;

	public Variable(String nom) {
		this.nom = nom;
		e = new Entree(nom);
	}

	private String getSymbole(Entree e) throws Exception {
		return TableSymbole.getInstance().identifier(e).toString();
	}

	public void verifier() throws Exception {
		if(getSymbole(e) != null){
			nom = getSymbole(e);
		}
		else{
			throw new Exception();
		}
	}
}
