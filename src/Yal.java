import analyse.AnalyseurLexical;
import analyse.AnalyseurSyntaxique;
import arbre.ArbreAbstrait;
import exceptions.AnalyseException;
import tds.TableSymbole;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 24 mars 2015
 *
 * @author brigitte wrobel-dautcourt
 */

public class Yal {

	public Yal(String fichier) {
		try {
			AnalyseurSyntaxique analyseur = new AnalyseurSyntaxique(new AnalyseurLexical(new FileReader(fichier)));
			ArbreAbstrait arbre = (ArbreAbstrait) analyseur.parse().value;
			//System.err.println("expression stockée dans l'arbre : " + arbre);
			arbre.verifier();
			//System.out.println(arbre.toMIPS());

			Path path = Paths.get(fichier.replaceAll(".yal",".mips"));
			BufferedWriter writer = Files.newBufferedWriter(path);



			StringBuilder sb = new StringBuilder();
			sb.append("move $s7, $sp\n");
			//Allocation

			// Déplacement
			for (int i = 0; i > TableSymbole.getInstance().getDep(); i-=4) {
				sb.append("sw $v0, "+i+"($s7)\n");
			}
			sb.append("addi $sp, $sp, "+ TableSymbole.getInstance().getDep()+"\n");

			writer.write(sb.toString());
			writer.write(arbre.toMIPS());
			writer.write("\nend:\nmove $v1, $v0\nli $v0,10\nsyscall");
			//sb.append("\nend:\nmove $v1, $v0\nli $v0,10\nsyscall");
			//writer.write(sb.toString());


			writer.flush();
			writer.close();
			System.out.println("COMPILATION OK");


		} catch (FileNotFoundException ex) {
			System.err.println("Fichier " + fichier + " inexistant");
		} catch (AnalyseException ex) {
			System.err.println(ex.getMessage());
		} catch (Exception ex) {
			Logger.getLogger(Yal.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public static void main(String[] args) {
		if (args.length != 1) {
			System.err.println("Nombre incorrect d'arguments");
			System.err.println("\tjava -jar yal.jar <fichierSource.yal>");
			System.exit(1);
		}
		new Yal(args[0]);
	}

}
