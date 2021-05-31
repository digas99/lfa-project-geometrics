grammar Beaver;

program	: containers (stats '>>')* EOF;

containers	: 'containers' '=>' idsList '>>' ;

stats	: 'Pallete' ID '=>' idsList		#statsPallete
		| 'Color' ID '=>' color			#statsColor
		| FIGURE ID '=>' inlineSet+		#statsSet
		| ID 'contains' '=>' stats+		#statsContains
		;

idsList	: ID (',' ID)* ;

inlineSet	: ID (expr | pointsExpr | expr color | angle) ;

expr	: expr op=('*' | '/') expr			#exprMultDiv
		| expr op=('+' | '-') expr			#exprAddSub
		| NUMBER							#exprNumber
		| ID								#exprID
		| ID ID								#exprTypeProperty
		| '(' expr ')'						#exprParentesis
		| value=('+'|'-') (expr)			#exprUnary
		| expr '^' value=('+'|'-')? expr	#exprPower
		;

pointsExpr	: pointsExpr ('+' | '-') pointsExpr		#pointsExprCalc
			| point									#pointsExprPoint
			| expr									#pointsExprExpr
			| expr ',' expr							#pointsPointExpr
			| 'center'								#pointsCenter
			;

color : '#'ID | (NUMBER ',' NUMBER ',' NUMBER) ;
point : NUMBER ',' NUMBER ;
angle : expr ('º' | 'deg' | 'rad') ;

FIGURE: ('Point' | 'Rectangle' | 'Circle' | 'Line' | 'Triangle');
ID: [a-zA-Z0-9]+;	
NUMBER : [0-9]+('.' [0-9]+)? | 'pi'; 
WS	: [ \t\n\r]+ -> skip;
COMMENT: '!!' ~[\n]* -> skip;