grammar Beaver;
@header{ 
package Beaver; 
}

// stats NEWLINE?
program	: NEWLINE* containers NEWLINE* (stats NEWLINE*)* EOF;

containers	: 'containers' '=>' idsList? '>>' NEWLINE* ;

// ID 'contains' '=>' (stats NEWLINE?)+ '>>'
stats	: 'Pallete' ID '=>' idsList '>>'				#statsPallete
		| 'Color' ID '=>' (ID | color) '>>'				#statsColor
		| 'Number' ID '=>' expr '>>'					#statsNumber
		| 'Point' ID '=>' pointsExpr '>>'				#statsPoint
		| FIGURE ID '=>' inlineSet+	'>>'				#statsSet
		| ID 'contains' '=>' (stats NEWLINE*)+ '>>'		#statsContains
		;

// idsList	: ID (',' NEWLINE? ID)* NEWLINE? ;
idsList	: ID (',' NEWLINE* ID)* NEWLINE* ;

// NEWLINE? 
inlineSet	: ID (expr | pointsExpr | color | angle | TRUTHVAL) NEWLINE* ;

expr	: expr op=('*' | '/') expr			#exprMultDiv
		| expr op=('+' | '-') expr			#exprAddSub
		| NUMBER							#exprNumber
		| identifiers						#exprIds
		| '(' expr ')'						#exprParentheses
		| value=('+'|'-') (expr)			#exprUnary
		| expr '^' value=('+'|'-')? expr	#exprPower
		;


pointsExpr	: pointsExpr op=('++' | '--') pointsExpr	#pointsExprCalc
			| identifiers								#pointsIds
			| point										#pointsExprPoint
			| 'container-center'						#pointsCenter
			;

identifiers	: ID						#id
			| ID '[' ID ']'('['ID']')?	#idProp
			;
			
color 	: '#'(ID|NUMBER)									#colorHex
		| expr ',' expr ',' expr							#colorRGB
		| (var=ID | '['index=(ID | NUMBER) ']') 'from' ID	#colorPallete
		;

point : expr ',' expr ;
angle : expr type=('º' | 'deg' | 'rad') ;

TRUTHVAL : 'true' | 'false';
FIGURE: ('Rectangle' | 'Circle' | 'Line' | 'Triangle');
NUMBER : [0-9]+('.' [0-9]+)? | 'pi'; 
ID: [a-zA-Z0-9]+;	
NEWLINE : '\r'? '\n';
WS	: [ \t\n\r]+ -> skip;
COMMENT: '!!' ~[\n]* -> skip;