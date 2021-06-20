Board "Test"

write "this is a test"

start Number a -> 10
start Number b -> 5 + a/2

if a equals b and true:
	/- start Number n -> (1 + 3) / 3
	/- start Point n3 -> n center
	/- start Number n3x -> n center x
	start Label string -> "this is a string"
	/- start Number n2 -> 5 ^ -1 ^ 3
	/- try number with exponencial
end

/-start Rectangle rec:
/-	display -> true
/-	width -> 400
/-	thickness -> 2
/-	height -> 400
/-	angle -> 0 deg
/-	center -> 0,0
/-	color -> #ffffff
/-	depth -> 1
/-	filled -> true
/-end

start Rectangle rec:
	width -> 40
	height -> 50
	thickness -> 2
	angle -> 3 º
	display -> true
	filled -> false
	depth -> 2
	color -> #ffffff
	color -> 21,179,91
	center -> 0,10
end

start Circle circ:
	diameter -> 50
	thickness -> 10
	display -> true
	filled -> false
	depth -> 2
	color -> 209, 19, 85
	center -> 0,10
end

/-start Line lin:
/-	thickness -> 2
/-	display -> true
/-	color -> 250,124,98
/-	color -> #ffffff
/-end

/-start Triangle trin:
/-	thickness -> 2
/-	display -> true
/-	color -> 250,124,98
/-	color -> #ffffff
/-end
draw rec

draw circ

each 30 ms :
	set rec center -> 0, rec center y - 1
end

/- have to remove delay 
close 3000 ms