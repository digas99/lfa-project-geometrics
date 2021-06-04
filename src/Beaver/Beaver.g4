grammar Beaver;

// stats NEWLINE*???
program	: NEWLINE* containers NEWLINE* (stats NEWLINE?)* EOF;

containers	: 'containers' '=>' idsList? '>>' NEWLINE* ;

stats	: 'Pallete' ID '=>' idsList '>>'				#statsPallete
		| 'Color' ID '=>' (ID | color) '>>'				#statsColor
		| 'Number' ID '=>' NUMBER '>>'					#statsNumber
		| FIGURE ID '=>' inlineSet+	'>>'				#statsSet
		| ID 'contains' '=>' (stats NEWLINE?)+ '>>'		#statsContains
		;

idsList	: ID (',' NEWLINE? ID)* NEWLINE? ;

inlineSet	: ID (expr | pointsExpr | color | borderValue | angle) NEWLINE? ;

borderValue : expr (ID | color) ;

expr	: expr op=('*' | '/') expr			#exprMultDiv
		| expr op=('+' | '-') expr			#exprAddSub
		| NUMBER							#exprNumber
		| identifiers						#exprIds
		| '(' expr ')'						#exprParentheses
		| value=('+'|'-') (expr)			#exprUnary
		| expr '^' value=('+'|'-')? expr	#exprPower
		;

identifiers	: ID '[' ID ']'		#idProp
			| ID				#id
			;

pointsExpr	: pointsExpr ('+' | '-') pointsExpr		#pointsExprCalc
			| identifiers							#pointsIds
			| point									#pointsExprPoint
			| 'container-center'					#pointsCenter
			;

color 	: '#'(ID|NUMBER)									#colorHex
		| expr ',' expr ',' expr							#colorRGB
		| (var=ID | '['index=(ID | NUMBER) ']') 'from' ID	#colorPallete
		;

point : expr ',' expr ;
angle : expr ('º' | 'deg' | 'rad') ;

FIGURE: ('Point' | 'Rectangle' | 'Circle' | 'Line' | 'Triangle');
NUMBER : [0-9]+('.' [0-9]+)? | 'pi'; 
ID: [a-zA-Z0-9]+;	
NEWLINE : '\r'? '\n';
WS	: [ \t\n\r]+ -> skip;
COMMENT: '!!' ~[\n]* -> skip;