grammar Geometrics;

program	: use* 'Board' STRING stats* 'close' time EOF ;

// Begin use
use		: 'use' STRING ':' (use_attribs)+ 'end' ;

use_attribs	: TYPE ID '->' ID ;
// End use

// Begin stats
stats	: vars_init		#statVarsInit
		| vars_set		#statVarsSet
		| list			#statList
		| 'draw' ID		#statDraw
		| loop			#statLoop
		| if			#statConditional
		| func			#statFunc
		| func_call		#statFuncCall
		| easteregg		#statEasterEgg
		;

// Begin list
list	: ID 'add' expr (first='first')?	
		| ID 'remove' (NUMBER | STRING)
		;
	
// End list

// Begin if
if	: 'if' boolean_logic ':' (stats* | stop='stop') 'end' ;
// End if

// Begin loop
loop	: 'each' (time | ID) ':' (stats+ | 'stop') 'end'										#eachTime
		| 'each' (time | ID) 'with' ID 'from' NUMBER 'to' NUMBER ':' (stats+ | 'stop') 'end'	#eachFor
		| 'each' (time | ID) 'until' boolean_logic ':' (stats+ | 'stop') 'end'					#eachWhile
		;
// End func

// Begin func
func 	: 'start' 'Task' ID ('with' ID (',' ID)* )? ':' stats* 'end' ;

func_call	: 'call' ID ('with' expr (',' expr)* )? ;
// End func

// Begin easteregg
easteregg	: 'where is' ID '?' ; 
// End easteregg

deg : expr ('º' | 'deg' | 'rad') ;
time : expr ('ms'|'s'); 

DECIMAL : [0-9]+ '.' [0-9]+; 
NUMBER	: [0-9]+;
ID: [a-zA-Z0-9]+;	
TYPE: [A-Z][a-z]+;
STRING: '"' .*? '"';
WS	: [ \t\n\r]+ -> skip;
COMMENT: '/-' .*? '\n' -> skip;