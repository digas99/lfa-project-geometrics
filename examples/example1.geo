/- Two rectangles colliding and changing color
Board "Geometrics"

start Number a -> 10
start Number b -> 5 + a/2

start Rectangle recRight:
	width -> 100
	height -> 100
	thickness -> 5
	angle -> 0 º
	display -> true
	filled -> false
	depth -> 2
	color -> #ffffff
	color -> 21,179,91
	center -> 400,300
end

start Rectangle recLeft:
    width -> 100
	height -> 100
	thickness -> 5
	angle -> 0 º
	display -> true
	filled -> false
	depth -> 2
	color -> #ffffff
	color -> 0,0,0
	center -> -400,300

start Circle circTop:
	diameter -> 50
	thickness -> 10
	display -> true
	filled -> false
	depth -> 2
	color -> 209, 19, 85
	center -> 0,200
end

start Circle circBottom:
    diameter -> 50
	thickness -> 10
	display -> true
	filled -> false
	depth -> 2
	color -> 209, 19, 85
	center -> 0,-200
end


draw recLeft
draw recRight

draw circTop
draw circleBottom

start Number hitTop -> 0 

each 30 ms:
	set rectLeft center -> 1,0
    set rectRight center -> -1,0

	if rectLeft collides rectRight:
        set rectLeft center -> 0,0
        set rectRight center -> 0,0
		set rectLeft color -> 216,22,22
        set rectRight color -> 216,22,22
	end

    set circleTop center -> 0,-2
    set circleBottom center -> 0,2

    if circleTop collides circleBottom:
        set circleTop center -> 0,2
        set circleBottom center -> 0,-2
    end

    if circTop collides boardTop  
        set circleTop center -> 0,-2 
    end

    if circBottom collides boardBottom:
        set circleBottom center -> 0,2
    end

end
 
close