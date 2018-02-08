package exceptions;

/**
 * Created by Valentin.
 */
public class AnalyseDivisionParZeroException extends AnalyseException {
	public AnalyseDivisionParZeroException(String m) {
		super("ERREUR DIVISION PAR ZERO :\n\t" + m) ;
	}
}
