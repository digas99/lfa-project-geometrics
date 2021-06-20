Board "Test"

start Rectangle rec:
	width -> 75
	height -> 75
	thickness -> 2
	angle -> 15 º
	display -> true
	filled -> true
	depth -> 2
	color -> 21,179,91
	center -> 0,10
end

start Circle circ:
	diameter -> 75
	thickness -> 10
	display -> true
	filled -> true
	depth -> 2
	color -> 0,0,0
	center -> 0,0
end

start Circle circ2:
	diameter -> 75
	thickness -> 10
	display -> true
	filled -> true
	depth -> 2
	color -> 0,0,0
	center -> -250,0
end

start Circle circ3:
	diameter -> 75
	thickness -> 10
	display -> true
	filled -> true
	depth -> 2
	color -> 0,0,0
	center -> 250,0
end

start Circle circ4:
	diameter -> 75
	thickness -> 10
	display -> true
	filled -> true
	depth -> 2
	color -> 0,0,0
	center -> 0,0
end

start Circle circ5:
	diameter -> 75
	thickness -> 10
	display -> true
	filled -> true
	depth -> 2
	color -> 0,0,0
	center -> 0,-250
end

start Circle circ6:
	diameter -> 75
	thickness -> 10
	display -> true
	filled -> true
	depth -> 2
	color -> 0,0,0
	center -> 0, 250
end

start Circle circ7:
	diameter -> 75
	thickness -> 10
	display -> true
	filled -> true
	depth -> 2
	color -> 0, 0, 0
	center -> 0,0
end

start Circle circ8:
	diameter -> 75
	thickness -> 10
	display -> true
	filled -> true
	depth -> 2
	color -> 0,0,0
	center -> 0,0
end

draw rec

draw circ, circ2, circ3, circ4, circ5, circ6, circ7, circ8

each 30 ms:
	start Number hitTop -> 0
	start Number velocity -> 20
	start Number switches -> 0
	start Number angleValue -> 0

	if circ collides boardBottom:
		set hitTop -> 0
		set switches -> switches + 1
		set angleValue -> angleValue + 15
	end

	if hitTop equals 0:
		set circ center -> circ center x, circ center y - velocity
		set circ2 center -> circ2 center x, circ2 center y - velocity
		set circ3 center -> circ3 center x, circ3 center y - velocity
		set circ4 center -> (circ4 center x - velocity), circ4 center y
		set circ5 center -> (circ5 center x - velocity), circ5 center y
		set circ6 center -> (circ6 center x - velocity), circ6 center y
		set circ7 center -> (circ7 center x - velocity), circ7 center y - velocity
		set circ8 center -> (circ8 center x + velocity), circ8 center y + velocity
		if switches greater 1:
			set circ color -> 255, 0, 0
			set circ2 color -> 255, 191, 0
			set circ3 color -> 187, 255, 0
			set circ4 color -> 0, 255, 38
			set circ5 color -> 0, 255, 208
			set circ6 color -> 0, 98, 255
			set circ7 color -> 153, 0, 255
			set circ8 color -> 255, 0, 187
			set rec angle -> angleValue deg
		end
	end

	if circ collides boardTop:
		set hitTop -> 1
		set switches -> switches + 1
		set angleValue -> angleValue + 15
	end

	if hitTop equals 1:
		set circ center -> circ center x, circ center y + velocity
		set circ2 center -> circ2 center x, circ2 center y + velocity
		set circ3 center -> circ3 center x, circ3 center y + velocity
		set circ4 center -> (circ4 center x + velocity), circ4 center y
		set circ5 center -> (circ5 center x + velocity), circ5 center y
		set circ6 center -> (circ6 center x + velocity), circ6 center y
		set circ7 center -> (circ7 center x + velocity), circ7 center y + velocity
		set circ8 center -> (circ8 center x - velocity), circ8 center y - velocity
		if switches greater 1:
			set circ color -> 255, 0, 187
			set circ2 color -> 153, 0, 255
			set circ3 color -> 0, 98, 255
			set circ4 color -> 0, 255, 208
			set circ5 color -> 0, 255, 38
			set circ6 color -> 187, 255, 0
			set circ7 color -> 255, 191, 0
			set circ8 color -> 255, 0, 0
			set rec angle -> angleValue deg
		end
	end
end