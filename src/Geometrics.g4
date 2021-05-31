grammar Geometrics;

program	: use* 'Board' STRING stats* 'close' time EOF ;

// Begin use
use		: 'use' STRING ':' (useAttribs)+ 'end' ;

useAttribs	: ('Figure' | 'Color') ID '->' ID ;
// End use

// Begin stats
stats	: varsInit				#statVarsInit
		| varsSet				#statVarsSet
		| list					#statList
		| 'draw' ID	(',' ID)*	#statDraw
		| loop					#statLoop
		| conditional			#statConditional
		| funcCall				#statFuncCall
		| easteregg				#statEasterEgg
		| 'write' (ID | STRING)	#statConsoleLog
		| container				#statContainer
		;

// Begin container
container	: 'container' ID ':' stats+ 'end' ;
// End container

// Begin expr
expr	: expr op=('*' | '/') expr			#exprMultDiv
		| expr op=('+' | '-') expr			#exprAddSub
		| NUMBER							#exprNumber
		| ID								#exprID
		| ID ID								#exprTypeProperty
		| '(' expr ')'						#exprParentesis
		| value=('+'|'-') (expr)			#exprUnary
		| expr '^' value=('+'|'-')? expr	#exprPower
		;
// End expr

// Begin pointsExpr
pointsExpr	: pointsExpr ('*' | '/' | '+' | '-') pointsExpr		#pointsExprCalc
			| point												#pointsExprPoint
			| ID												#pointsExprID
			| ID ('center' | 'startingPoint' | 'endingPoint'
				| 'topLeftPoint' | 'topRightPoint'
				| 'bottomLeftPoint' | 'bottomRightPoint')		#pointsExprTypeProperty
			| expr ',' expr										#pointsExprExpr
			| 'center'											#pointsCenter
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
varsInit	: 'start' varsInitSpecifics ;

varsInitSpecifics	: prop=('Figure' | 'Triangle' | 'Rectangle'
						| 'Circle' | 'Text' | 'Point' | 'Line'
						| 'Number' |  'Angle' | 'Time') ID								#varsOnlyInit
					| prop=('Figure' | 'Rectangle' | 'Line'
						| 'Circle') value='List' ID										#varsInitList
					| 'Text' ID '->' (STRING | funcCall)								#varsInitText
					| 'Number' ID '->' (expr | funcCall)								#varsInitNumber
					| 'Angle' ID '->' (angle | funcCall)								#varsInitAngle						
					| 'Time' ID '->' (time | funcCall)									#varsInitTime
					| 'Point' ID '->' pointsExpr										#varsInitPoint
					| 'Triangle' ID '->' (commonProperty | triangleProperty)+ 'end'		#varsInitTriangle
					| 'Rectangle' ID ':' (commonProperty | rectangleProperty)+ 'end'	#varsInitRectangle
					| 'Line' ID ':' (commonProperty | lineProperty)+ 'end'				#varsInitLine
					| 'Circle' ID ':' (commonProperty | circleProperty)+ 'end'			#varsInitCircle
					| 'Figure' ID ':' (commonProperty | rectangleProperty)+ 'end'		#varsInitFigure
					| 'Task' ID ('with' ID (',' ID)* )? ':' stats+ 'end'				#varsInitFunc
					;
// End vars initialization

// Begin func call
funcCall	: 'call' ID ('with' (expr | STRING) (',' (expr | STRING))* )? ;
// End func call

// Begin vars set
varsSet	: 'set' ID (inlineSet | blockSet)			#varsSetProperties
		| 'set' ID '->' expr						#varsSetExpr
		| 'set' ID ('*' | '/' | '+' | '-') expr		#varsSetCalc
		;

inlineSet	: (commonProperty | rectangleProperty | circleProperty | lineProperty | triangleProperty) ;

blockSet : ':' inlineSet+ 'end' ;
// End vars set

// Begin objects properties
commonProperty	: prop=('border' | 'thickness'
					|'rotate' | 'depth') '->' expr					#commonPropNumbers
				| prop=('color' | 'borderColor') '->' (ID|color)	#commonPropColors
				| 'display' '->' value=('exposed' | 'hidden')		#commonPropDisplay
				| 'collision' '->' (TRUTHVAL|ID)					#commonPropCollisions
				;

lineProperty	: 'width' '->' expr								#linePropertyWidth
				| ('center' | 'startingPoint'
					| 'endingPoint') '->' pointsExpr			#linePropertyPoints
				| 'angle' '->' (ID | angle)						#linePropertyAngle
				;

rectangleProperty	: ('width' | 'height') '->' expr			#rectanglePropertySize
					| 'center' '->' pointsExpr					#rectanglePropertyCenter
					| 'angle' '->' (ID | angle)					#rectanglePropertyAngle
					;

circleProperty	: ('radius' | 'diameter') '->' expr				#circlePropertySize
				| ('center' | 'startingPoint'						
					| 'endingPoint') '->' pointsExpr			#circlePropertyPointss
				;

triangleProperty	: ('p0' | 'p1' | 'p2') '->' expr ;

// End objects properties


// in color, the properties are #xxxxx and rgb
color	: (ID | '#'NUMBER | NUMBER ',' NUMBER ',' NUMBER) ;

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
point : NUMBER ',' NUMBER ;

TRUTHVAL : 'true' | 'false';
ID: [a-zA-Z0-9]+;	
NUMBER : [0-9]+('.' [0-9]+)? | 'pi'; 
STRING: '"' .*? '"';
WS	: [ \t\n\r]+ -> skip;
COMMENT: '/-' ~[\n]* -> skip;