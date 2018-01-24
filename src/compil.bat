cd analyse
del *.class AnalyseurLexical.java AnalyseurSyntaxique.java CodesLexicaux.java
java -jar "C:\Documents\java-cup-11a.jar" -parser AnalyseurSyntaxique -symbols CodesLexicaux Grammaire.cup
java -jar "C:\Documents\jflex-1.6.1.jar" AnalyseurLexical.jflex
cd ..
ls
javac -cp "C:\Documents\compiltempo";"C:\Documents\java-cup-11a.jar" .\analyse\*.java
pause