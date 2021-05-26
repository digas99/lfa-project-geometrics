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
		| 'draw' ID				#statDraw
		| loop					#statLoop
		| conditional			#statConditional
		| funcCall				#statFuncCall
		| easteregg				#statEasterEgg
		| 'write' (ID | STRING)	#statConsoleLog
		;

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

varsInitSpecifics	: prop=('Figure' | 'Square' | 'Line'
						| 'Circle' | 'Text' | 'Number'
						| 'Time' | 'Angle') ID										#varsOnlyInit
					| prop=('Figure' | 'Square' | 'Line'
						| 'Circle') value='List' ID									#varsInitList
					| 'Text' ID '->' (STRING | funcCall)							#varsInitText
					| 'Number' ID '->' (expr | funcCall)							#varsInitNumber
					| 'Time' ID '->' (time | funcCall)								#varsInitTime
					| 'Angle' ID '->' (angle | funcCall)							#varsInitAngla						
					| 'Square' ID ':' (commonProperty | squareProperty)+ 'end'		#varsInitSquare
					| 'Line' ID ':' (commonProperty | lineProperty)+ 'end'			#varsInitLine
					| 'Circle' ID ':' (commonProperty | circleProperty)+ 'end'		#varsInitCircle
					| 'Figure' ID ':' (commonProperty | squareProperty)+ 'end'		#varsInitFigure
					| 'Task' ID ('with' ID (',' ID)* )? ':' stats+ 'end'			#varsInitFunc
					;
// End vars initialization

// Begin func call
funcCall	: 'call' ID ('with' (expr | STRING) (',' (expr | STRING))* )? ;
// End func call

// Begin vars set
varsSet	: 'set' ID (inlineSet | blockSet)
		| 'set' ID '->' expr
		| 'set' ID ('*' | '/' | '+' | '-') expr
		;

inlineSet	: (commonProperty | squareProperty | circleProperty) ;

blockSet : ':' inlineSet+ 'end' ;
// End vars set

// Begin objects properties
commonProperty	: prop=('posX' | 'posY' 
						| 'pos' | 'border'
						| 'width' | 'height'
						| 'radius' | 'diameter'
						| 'thickness' | 'rotate'
						| 'depth') '->' (expr)						#commonPropNumbers
				| prop=('color' | 'borderColor') '->' (ID|color)	#commonPropColors
				| 'display' '->' value=('exposed' | 'hidden')		#commonPropDisplay
				| 'collision' '->' (TRUTHVAL|ID)					#commonPropCollisions
				;

lineProperty	: 'width' '->' expr ;

squareProperty	: ('width' | 'height') '->' expr ;

circleProperty	: ('radius' | 'diameter') '->' expr ;
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
				| 'with' ID 'from' expr 'to' expr ':' (stats+ | 'stop') #eachFor
				;

// Begin easteregg
easteregg	: 'where iss' ID '?' ; 
// End easteregg

angle : expr ('º' | 'deg' | 'rad') ;
time : expr ('ms'|'s'); 

TRUTHVAL : 'true' | 'false';
NUMBER : [0-9]+('.' [0-9]+)? | 'pi'; 
ID: [a-zA-Z0-9]+;	
STRING: '"' .*? '"';
WS	: [ \t\n\r]+ -> skip;
COMMENT: '/-' .*? '\n' -> skip;