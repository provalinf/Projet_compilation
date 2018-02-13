package arbre.instruction;

/**
 * Created by bonnal4u.
 */
public class Declaration extends Instruction {
	private String idf;
	private String type;

	public Declaration(int no, String idf, String type) {
		super(no);
		this.idf = idf;
		this.type = type;
	}

	@Override
	public void verifier() {
	}

	@Override
	public String toMIPS() {
		StringBuilder sb = new StringBuilder();
		sb.append("lw $v0," + idf);
		return sb.toString();    //////////////////// /!\
	}
}
