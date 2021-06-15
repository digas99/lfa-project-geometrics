use "../../doc/Beaver/working/flags.bvr" :
	Figure flag1 -> japanese
	Figure sphere -> armilarSphere
end

Board "Test"

write "this is a test"

start Number a -> 10
start Number b -> 5 + a/2

if a equals b and true:
	/- start Number n -> (1 + 3) / 3
	start Point n3 -> n center
	start Number n3x -> n center x
	start Label string -> "this is a string"
	/- start Number n2 -> 5 ^ -1 ^ 3
	/- try number with exponencial
end

close 3000 ms