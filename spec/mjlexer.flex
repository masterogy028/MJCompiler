package rs.ac.bg.etf.pp1;

import java_cup.runtime.Symbol;

%%

%{

	// ukljucivanje informacije o poziciji tokena
	private Symbol new_symbol(int type) {
		return new Symbol(type, yyline+1, yycolumn);
	}

	// ukljucivanje informacije o poziciji tokena
	private Symbol new_symbol(int type, Object value) {
		return new Symbol(type, yyline+1, yycolumn, value);
	}

%}

%cup
%line
%column

%xstate COMMENT

%eofval{
	return new_symbol(sym.EOF);
%eofval}

%%

// # COMMENTS /////////////////////////////////////////////////////////////////////////////////////
<YYINITIAL> "//" 		    { yybegin(COMMENT); }
<COMMENT> .                 { yybegin(COMMENT); }
<COMMENT> "\r\n"            { yybegin(YYINITIAL); }


// #    WHITE SPACE //////////////////////////////////////////////////////////////////////////
" " 	{ }
"\b" 	{ }
"\t" 	{ }
"\r\n" 	{ }
"\f" 	{ }


// #    KEYWORDS /////////////////////////////////////////////////////////////////////////////
"program"           { return new_symbol(sym.PROG, yytext()); }

"const"				{ return new_symbol(sym.CONST, yytext()); }
"enum"				{ return new_symbol(sym.ENUM, yytext()); }

"print" 	        { return new_symbol(sym.PRINT, yytext()); }
"read"		        { return new_symbol(sym.READ, yytext()); }

"return" 	        { return new_symbol(sym.RETURN, yytext()); }
"void" 	            { return new_symbol(sym.VOID, yytext()); }

"new"							{ return new_symbol(sym.NEW, yytext()); }
"null"							{ return new_symbol(sym.NULL, yytext()); }

"class"			    { return new_symbol(sym.CLASS, yytext()); }
"interface"			{ return new_symbol(sym.INTERFACE, yytext()); }
"extends"			{ return new_symbol(sym.EXTENDS, yytext()); }
"implements"		{ return new_symbol(sym.IMPLEMENTS, yytext()); }


// # CONST ////////////////////////////////////////////////////////////////////////////////////////
([0-9]+)				{ return new_symbol(sym.INT, new Integer(yytext())); }
"'"[ -~]"'"						{ return new_symbol(sym.CHAR, yytext().charAt(1)); }
("false"|"true")				{ return new_symbol(sym.BOOL, new Boolean(yytext())); }


// # IDENT
([a-z]|[A-Z])[a-z|A-Z|0-9|_]* 	{return new_symbol (sym.IDENT, yytext()); }


// # OPERATORS ////////////////////////////////////////////////////////////////////////////////////
<YYINITIAL>
"+" 		{ return new_symbol(sym.PLUS, yytext()); }
"-" 		{ return new_symbol(sym.MINUS, yytext()); }
"*" 		{ return new_symbol(sym.MULTIPLY, yytext()); }
"/" 		{ return new_symbol(sym.DIVIDE, yytext()); }
"%" 		{ return new_symbol(sym.MOD, yytext()); }

"." 		{ return new_symbol(sym.DOT, yytext()); }
";" 		{ return new_symbol(sym.SEMI, yytext()); }
"," 		{ return new_symbol(sym.COMMA, yytext()); }

"(" 		{ return new_symbol(sym.LPAREN, yytext()); }
")" 		{ return new_symbol(sym.RPAREN, yytext()); }
"{" 		{ return new_symbol(sym.LBRACE, yytext()); }
"}"			{ return new_symbol(sym.RBRACE, yytext()); }
"["			{ return new_symbol(sym.LBRACKET, yytext()); }
"]"			{ return new_symbol(sym.RBRACKET, yytext()); }

"="         { return new_symbol(sym.EQUALS, yytext());}
"=="		{ return new_symbol(sym.EQUAL, yytext()); }
"!="		{ return new_symbol(sym.NOTEQUAL, yytext()); }
">"			{ return new_symbol(sym.GREATER, yytext()); }
">="		{ return new_symbol(sym.GTEQUAL, yytext()); }
"<"			{ return new_symbol(sym.LESS, yytext()); }
"<="		{ return new_symbol(sym.LEEQUAL, yytext()); }

"+="		{ return new_symbol(sym.ADDEQ, yytext()); }
"-="		{ return new_symbol(sym.SUBEQ, yytext()); }
"/="		{ return new_symbol(sym.DIVEQ, yytext()); }
"*="		{ return new_symbol(sym.MULEQ, yytext()); }

"&&"		{ return new_symbol(sym.AND, yytext()); }
"||"		{ return new_symbol(sym.OR, yytext()); }

"++"		{ return new_symbol(sym.INCREMENT, yytext()); }
"--"		{ return new_symbol(sym.DECREMENT, yytext()); }



. { System.err.println("Leksicka greska ("+yytext()+") u liniji "+(yyline+1)); }






