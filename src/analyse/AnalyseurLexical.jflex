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
csteC = [A-Za-z_][A-Za-z_0-9]*

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

"programme"			{ return symbol(CodesLexicaux.PROGRAMME); }
"debut"				{ return symbol(CodesLexicaux.DEBUT); }
"fin"				{ return symbol(CodesLexicaux.FIN); }

"ecrire"			{ return symbol(CodesLexicaux.ECR); }

{idf}					{ return symbol(CodesLexicaux.IDF, yytext()) ; }
{csteE}      	        { return symbol(CodesLexicaux.CONSTANTEINT, yytext()); }
{csteB}      	        { return symbol(CodesLexicaux.CONSTANTEBOOL, yytext()); }
{csteC}      	        { return symbol(CodesLexicaux.CSTECHAINE, yytext()); }

{espace}                { }

{commentaire}			{ }


.                       { throw new AnalyseLexicaleException(yyline, yycolumn, yytext()) ; }
