grammar Geometrics;

program	: NEWLINE* use* NEWLINE* 'Board' STRING NEWLINE* (stats NEWLINE*)* 'close' time NEWLINE* EOF ;

// Begin use
use		: 'use' STRING ':' (useAttribs)+ 'end' ;

useAttribs	: (FIGURE|color) ID '->' ID NEWLINE* ;
// End use

// Begin stats
stats	: varsInit						#statVarsInit
		| varsSet						#statVarsSet
		| list							#statList
		| 'draw' ID	(',' ID)* NEWLINE*	#statDraw
		| loop							#statLoop
		| conditional					#statConditional
		| funcCall						#statFuncCall
		| easteregg						#statEasterEgg
		| 'write' (ID | STRING)			#statConsoleLog
		| container						#statContainer
		;

// Begin container
container	: 'container' ID ':' stats+ 'end' ;
// End container

// Begin expr
expr	: expr op=('*' | '/') expr			#exprMultDiv
		| expr op=('+' | '-') expr			#exprAddSub
		| NUMBER							#exprNumber
		| identifiers						#exprId
		| '(' expr ')'						#exprParentesis
		| value=('+'|'-') (expr)			#exprUnary
		| expr '^' value=('+'|'-')? expr	#exprPower
		;
// End expr

identifiers	: ID ID	ID?	#idProp
			| ID		#id
			;

// Begin pointsExpr
pointsExpr	: pointsExpr ('+' | '-') pointsExpr					#pointsExprCalc
			| identifiers										#pointsId
			| point												#pointsExprPoint
			| 'container-center'								#pointsCenter
			;

// End pointsExpr

// Begin boolean logic
// different is equivalent to xor
booleanLogic	: booleanLogic op=('or' | 'and' | 'different'
					| 'equals' | 'greater' | 'lower' | 'greater'
					| 'greater equal' | 'lower equal'
					| '|' | '&' | '!' | '=' | '>' | '<' | '>=' | '<=') booleanLogic	#boolLogicOps
				| expr																#boolLogicExpr
				| '(' booleanLogic ')'												#boolLogicParentesis			
				| 'not' booleanLogic												#boolLogicNot
				| ID 'collides' ID													#boolLogicCollides
				| TRUTHVAL															#boolLogicTruthval
				;
// End boolean logic

// Begin vars initialization
varsInit	: 'start' varsInitSpecifics NEWLINE* ;

varsInitSpecifics	: (OBJECT | FIGURE) ID											#varsOnlyInit
					| FIGURE value='List' ID										#varsInitList
					| OBJECT ID '->' attribs										#varsInitObject
					| FIGURE ID blockSet											#varsInitFigure
					| 'Task' ID ('with' ID (',' ID)* )? ':' (stats NEWLINE*)+ 'end'	#varsInitFunc
					;

inlineSet	: ID '->' attribs NEWLINE* ;

blockSet : ':' inlineSet+ 'end' ;

attribs	: (STRING | expr | angle | time | pointsExpr | funcCall | TRUTHVAL) NEWLINE*;
// End vars initialization

// Begin func call
funcCall	: 'call' ID ('with' (expr | STRING) (',' (expr | STRING))* )? ;
// End func call

// Begin vars set
varsSet	: 'set' ID (inlineSet | blockSet) 			#varsSetProperties
		| 'set' ID '->' expr						#varsSetExpr
		| 'set' ID ('*' | '/' | '+' | '-') expr		#varsSetCalc
		;

// End objects properties

// in color, the properties are #xxxxx and rgb
// falta implementar ação com pallete
color 	: ID						#colorId 
		| '#'(ID|NUMBER)			#colorHex
		| expr ',' expr ',' expr	#colorRGB
		;

// End vars initialization

// Begin list
list	: ID 'add' ID (first='first')?		#listAdd
		| ID 'remove' (expr | STRING)		#listRemove
		;
// End list

// Begin conditional
conditional	: 'if' booleanLogic ':' (stats* | stop='stop') 'end' ;
// End conditional

// Begin loop
loop	: 'each' (time | ID) loopSpecifics 'end' ;

loopSpecifics	: ':' (stats+ | 'stop')										#eachTime
				| 'until' booleanLogic ':' (stats+ | 'stop')				#eachWhile
				| 'with' ID 'from' expr 'to' expr ':' (stats+ | 'stop') 	#eachFor
				;

// Begin easteregg
easteregg	: 'where is' ID '?' ; 
// End easteregg

angle : expr ('º' | 'deg' | 'rad') ;
time : expr ('ms'|'s'); 
point : expr ',' expr ;

FIGURE : 'Figure' | 'Triangle' | 'Rectangle' | 'Circle';
OBJECT : 'Text' | 'Point' | 'Line' | 'Number' |  'Angle' | 'Time';
TRUTHVAL : 'true' | 'false';
ID: [a-zA-Z0-9]+;	
NUMBER : [0-9]+('.' [0-9]+)? | 'pi'; 
NEWLINE : '\r'? '\n';
STRING: '"' .*? '"';
WS	: [ \t\n\r]+ -> skip;
COMMENT: '/-' ~[\n]* -> skip;