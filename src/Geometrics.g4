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
		| funcCall				#statFuncCall
		| easteregg				#statEasterEgg
		| 'write' (ID | STRING)	#statConsoleLog
		;
		// 		| if					#statConditional

// Begin vars initialization
varsInit	: 'start' varsInitSpecifics ;

varsInitSpecifics	: prop=('Figure' | 'Square' | 'Line'
						| 'Circle' | 'Text' | 'Number'
						| 'Time' | 'Angle') ID										#varsOnlyInit
					| prop=('Figure' | 'Square' | 'Line'
						| 'Circle') value='List' ID									#varsInitList
					| 'Text' ID '->' (STRING | funcCall)							#varsInitText
					| 'Number' ID '->' (NUMBER | funcCall)							#varsInitNumber
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
// funcCall	: 'call' ID ('with' expr (',' expr)* )? ;
// trocar NUMBER | ID por expr
funcCall	: 'call' ID ('with' (NUMBER | ID | STRING) (',' (NUMBER | ID | STRING))* )? ;
// End func call

// Begin vars set
varsSet	: 'set' ID (inlineSet | blockSet) ;

inlineSet	: (commonProperty | squareProperty | circleProperty) ;

blockSet : ':' inlineSet+ 'end' ;
// End vars set

// Begin objects properties
commonProperty	: prop=('pos x' | 'pos y' 
						| 'pos' | 'border'
						| 'width' | 'height'
						| 'radius' | 'diameter'
						| 'thickness' | 'rotate'
						| 'depth') '->' (ID | NUMBER)			#commonPropNumbers
				| prop=('color' | 'border color') '->' color	#commonPropColors
				| 'display' '->' value=('exposed' | 'hidden')	#commonPropDisplay
				| 'collision' '->' TRUTHVAL						#commonPropCollisions
				;

lineProperty	: 'width' '->' NUMBER ;

squareProperty	: ('width' | 'height') '->' NUMBER ;

circleProperty	: ('radius' | 'diameter') '->' NUMBER ;
// End objects properties


// in color, the properties are #xxxxx and rgb
color	: (ID | '#'NUMBER | NUMBER ',' NUMBER ',' NUMBER) ;

// End vars initialization

// Begin list
list	: ID 'add' ID (first='first')?		#listAdd
		| ID 'remove' (NUMBER | STRING)		#listRemove
		;
		// |  ID 'add' expr (first='first')?	#listAdd
	
// End list

// Begin if
//if	: 'if' booleanLogic ':' (stats* | stop='stop') 'end' ;
// End if

// Begin loop
loop	: 'each' (time | ID) loopSpecifics 'end' ;

loopSpecifics	: ':' (stats+ | 'stop')										#eachTime
				| 'with' ID 'from' NUMBER 'to' NUMBER ':' (stats+ | 'stop') #eachFor
				;
				// | 'until' booleanLogic ':' (stats+ | 'stop')				#eachWhile

// Begin easteregg
easteregg	: 'where is' ID '?' ; 
// End easteregg

angle : NUMBER ('º' | 'deg' | 'rad') ;
time : NUMBER ('ms'|'s'); 
// deg : expr ('º' | 'deg' | 'rad') ;
// time : expr ('ms'|'s'); 

TRUTHVAL : 'true' | 'false';
NUMBER : [0-9]+('.' [0-9]+)? | 'pi'; 
ID: [a-zA-Z0-9]+;	
STRING: '"' .*? '"';
WS	: [ \t\n\r]+ -> skip;
COMMENT: '/-' .*? '\n' -> skip;