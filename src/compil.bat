cd analyse
del *.class AnalyseurLexical.java AnalyseurSyntaxique.java CodesLexicaux.java
java -jar "C:\Projet_compilation\lib\java-cup-11b.jar" -parser AnalyseurSyntaxique -symbols CodesLexicaux Grammaire.cup
java -jar "C:\Projet_compilation\lib\jflex-1.6.1.jar" AnalyseurLexical.jflex
cd ..
ls
ne pas faire javac -cp "C:\Projet_compilation\src";"C:\Projet_compilation\lib\java-cup-11b.jar" .\analyse\*.java
pause