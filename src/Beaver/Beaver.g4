grammar Beaver;

program	: containers (stats NEWLINE?)* EOF;

containers	: 'containers' '=>' idsList '>>' NEWLINE? ;

stats	: 'Pallete' ID '=>' idsList '>>'	#statsPallete
		| 'Color' ID '=>' color	'>>'		#statsColor
		| FIGURE ID '=>' inlineSet+	'>>'	#statsSet
		| ID 'contains' '=>' (stats NEWLINE?)+	'>>'	#statsContains
		;

idsList	: ID (',' NEWLINE? ID)* NEWLINE? ;

inlineSet	: ID (expr | pointsExpr | color | expr color | angle) NEWLINE? ;

expr	: expr op=('*' | '/') expr			#exprMultDiv
		| expr op=('+' | '-') expr			#exprAddSub
		| NUMBER							#exprNumber
		| ID '[' ID ']'						#exprTypeProperty
		| ID								#exprID
		| '(' expr ')'						#exprParentesis
		| value=('+'|'-') (expr)			#exprUnary
		| expr '^' value=('+'|'-')? expr	#exprPower
		;

pointsExpr	: pointsExpr ('+' | '-') pointsExpr		#pointsExprCalc
			| point									#pointsExprPoint
			| expr									#pointsExprExpr
			| 'container-center'					#pointsCenter
			;

color : '#'ID | (expr ',' expr ',' expr) ;
point : expr ',' expr ;
angle : expr ('º' | 'deg' | 'rad') ;

FIGURE: ('Point' | 'Rectangle' | 'Circle' | 'Line' | 'Triangle');
ID: [a-zA-Z0-9]+;	
NUMBER : [0-9]+('.' [0-9]+)? | 'pi'; 
NEWLINE : '\r'? '\n';
WS	: [ \t\n\r]+ -> skip;
COMMENT: '!!' ~[\n]* -> skip;