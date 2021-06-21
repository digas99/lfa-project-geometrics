grammar Geometrics;
@header{
package Geometrics; 
}

program	: NEWLINE* use* NEWLINE* 'Board' STRING NEWLINE* (stats NEWLINE*)* NEWLINE* EOF ;

// Begin use
use		: 'use' STRING ':' (useAttribs)+ 'end' ;

useAttribs	: (FIGURE|color) ID '->' ID NEWLINE* ;
// End use

// Begin stats
stats	: 'start' varsInit NEWLINE*		#statVarsInit
	| varsSet							#statVarsSet
	| 'draw' ID	(',' ID)* NEWLINE*		#statDraw
	| loop								#statLoop
	| conditional						#statConditional
	| 'write' (ID | STRING)				#statConsoleLog
	| container							#statContainer
	;

// Begin container
container	: 'container' ID ':' stats+ 'end' ;
// End container

// Begin expr
expr returns[String var = null]
	: expr op=('*' | '/') expr			#exprMultDiv
	| expr op=('+' | '-') expr			#exprAddSub
	| NUMBER							#exprNumber
	| identifiers						#exprId
	| '(' expr ')'						#exprParentesis
	| value=('+'|'-') (expr)			#exprUnary
	| expr '^' value=('+'|'-')? expr	#exprPower
	;
// End expr

identifiers	: ID+ ;

// Begin pointsExpr
pointsExpr returns[String var = null]
		: pointsExpr op=('+' | '-') pointsExpr		#pointsExprCalc
		| identifiers								#pointsId
		| point										#pointsExprPoint
		| 'container-center'						#pointsCenter
		;

// End pointsExpr

// Begin boolean logic
// different is equivalent to xor
booleanLogic returns [String var = null]
		: booleanLogic op=('or' | 'and' | 'different'
			| 'equals' | 'greater' | 'lower' | 'greater'
			| 'greater equal' | 'lower equal'
			| '|' | '&' | '!' | '=' | '>' | '<' | '>=' | '<=') booleanLogic	#boolLogicOps
		| expr																#boolLogicExpr
		| '(' booleanLogic ')'												#boolLogicParentesis			
		| 'not' booleanLogic												#boolLogicNot
		| (id0=ID|board0=BOARD) 'collides' (id1=ID|board1=BOARD)			#boolLogicCollides
		| TRUTHVAL															#boolLogicTruthval
		;
// End boolean logic

// Begin vars initialization
varsInit: (OBJECT | FIGURE) ID												#varsOnlyInit
		| OBJECT ID '->' attribs											#varsInitObject
		| FIGURE ID blockSet												#varsInitFigure
		;

inlineSet	: ID '->' attribs NEWLINE* ;

blockSet : ':' inlineSet+ 'end' ;

attribs	returns [String var = null]: (STRING | expr | angle | time | pointsExpr | color | TRUTHVAL) NEWLINE*;
// End vars initialization

// Begin vars set
varsSet	: 'set' ID (inlineSet | blockSet) 			#varsSetProperties
		| 'set' ID '->' expr						#varsSetExpr
		| 'set' ID op=('*' | '/' | '+' | '-') expr	#varsSetCalc
		;

// End objects properties

// in color, the properties are #xxxxx and rgb
// falta implementar ação com pallete
color returns [String var = null]
		: ID						#colorId 
		| '#'(ID|NUMBER)			#colorHex
		| expr ',' expr ',' expr	#colorRGB
		;

// End vars initialization

// Begin conditional
conditional	: 'if' booleanLogic ':' blockStats* 'end' ;
// End conditional

blockStats	: stats | stop='stop' | NEWLINE ;

// Begin loop
loop	: 'each' (time | ID) ':' (stats NEWLINE*)+ 'end' ;

angle returns [String var = null]: expr type=('º' | 'deg' | 'rad') ;
time returns [String var = null]: expr type=('ms'|'s'); 
point returns [String var = null]: expr ',' expr ;

BOARD : 'boardTop' | 'boardRight' | 'boardBottom' | 'boardLeft' ;
FIGURE : 'Figure' | 'Triangle' | 'Rectangle' | 'Circle' | 'Line' ;
OBJECT : 'Label' | 'Point' | 'Number' | 'Angle' | 'Time';
TRUTHVAL : 'true' | 'false';
NUMBER : [0-9]+('.' [0-9]+)? | 'pi'; 
ID: [a-zA-Z0-9]+;	
NEWLINE : '\r'? '\n';
STRING: '"' .*? '"';
WS	: [ \t\n\r]+ -> skip;
COMMENT: '/-' ~[\n]* -> skip;