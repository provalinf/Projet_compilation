#!/usr/bin/env bash
cd analyse
rm -f *.class AnalyseurLexical.java AnalyseurSyntaxique.java CodesLexicaux.java
java -jar ../../lib/java-cup-11a.jar -parser AnalyseurSyntaxique -symbols CodesLexicaux Grammaire.cup
java -jar ../../lib/jflex-1.6.1.jar AnalyseurLexical.jflex
read