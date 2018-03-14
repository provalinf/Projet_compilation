package analyse ;

import java_cup.runtime.*;
import exceptions.AnalyseLexicaleException;
      
%%
   
%class AnalyseurLexical
%public

%line
%column
    
%type Symbol
%eofval{
        return symbol(CodesLexicaux.EOF) ;
%eofval}

%cup

%{
  private Symbol symbol(int type) {
	return new Symbol(type, yyline, yycolumn) ;
  }

  private Symbol symbol(int type, Object value) {
	return new Symbol(type, yyline, yycolumn, value) ;
  }
%}

idf = [A-Za-z_][A-Za-z_0-9]*
csteE = [0-9]+
csteB = "vrai" | "faux"
csteC = \"[^\"]+\"
type = "entier"|"booleen"|"boolean"
finsi = "fsi"|"finsi"
ftantque = "fintantque"|ftantque

finDeLigne = \r|\n
espace = {finDeLigne}  | [ \t\f]
commentaire = [/][/].* | [#].*

%%

"+"                	{ return symbol(CodesLexicaux.PLUS); }
"-"                	{ return symbol(CodesLexicaux.MOINS); }
"*"                	{ return symbol(CodesLexicaux.MULT); }
"/"                	{ return symbol(CodesLexicaux.DIV); }

"="                    { return symbol(CodesLexicaux.EGAL); }
"=="                    { return symbol(CodesLexicaux.EGALEGAL); }
"!="                    { return symbol(CodesLexicaux.DIFF); }
"<"                	{ return symbol(CodesLexicaux.INF); }
">"                	{ return symbol(CodesLexicaux.SUP); }

"et"                	{ return symbol(CodesLexicaux.ET); }
"ou"                	{ return symbol(CodesLexicaux.OU); }
"non"                	{ return symbol(CodesLexicaux.NON); }

"("                	{ return symbol(CodesLexicaux.PAROUV); }
")"                	{ return symbol(CodesLexicaux.PARFER); }

";"					{ return symbol(CodesLexicaux.POINTVIRGULE); }

"tantque"			{ return symbol(CodesLexicaux.TANTQUE); }
"repeter"			{ return symbol(CodesLexicaux.REPETER); }
{ftantque}			{ return symbol(CodesLexicaux.FINTANTQUE); }

"si"				{ return symbol(CodesLexicaux.SI); }
"alors"				{ return symbol(CodesLexicaux.ALORS); }
"sinon"				{ return symbol(CodesLexicaux.SINON); }
{finsi}				{ return symbol(CodesLexicaux.FINSI); }

"programme"			{ return symbol(CodesLexicaux.PROGRAMME); }
"debut"				{ return symbol(CodesLexicaux.DEBUT); }
"fin"				{ return symbol(CodesLexicaux.FIN); }

"ecrire"			{ return symbol(CodesLexicaux.ECR); }
"lire"              { return symbol(CodesLexicaux.LIRE); }

{type}					{ return symbol(CodesLexicaux.TYPE, yytext()) ; }
{csteE}      	        { return symbol(CodesLexicaux.CONSTANTEINT, yytext()); }
{csteB}      	        { return symbol(CodesLexicaux.CONSTANTEBOOL, yytext()); }
{csteC}      	        { return symbol(CodesLexicaux.CSTECHAINE, yytext()); }
{idf}					{ return symbol(CodesLexicaux.IDF, yytext()) ; }

{espace}                { }

{commentaire}			{ }


.                       { throw new AnalyseLexicaleException(yyline, yycolumn, yytext()) ; }
