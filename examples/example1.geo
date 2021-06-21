/- example1
Board "Geometrics"

start Number a -> 10
start Number b -> 5 + a/2

start Rectangle recRight:
	width -> 100
	height -> 100
	thickness -> 5
	angle -> 0 º
	filled -> false
	color -> 21,179,91
	center -> 400,300
end

start Rectangle recLeft:
    width -> 100
	height -> 100
	thickness -> 5
	angle -> 0 º
	filled -> false
	color -> 0,0,0
	center -> -400,300
end

start Circle circTop:
	diameter -> 50
	thickness -> 5
	filled -> false
	color -> 209, 19, 85
	center -> 0,200
end

start Circle circBottom:
    diameter -> 50
	thickness -> 5
	filled -> false
	color -> 0, 70, 235
	center -> 0,-200
end

start Circle circTopRight:
    diameter -> 100
	thickness -> 10
	filled -> false
	color -> 113,125,155
	center -> 450,450
end

start Circle circBottomLeft:
    diameter -> 100
	thickness -> 10
	filled -> false
	color -> 113,125,155
	center -> -450,-450
end


draw recLeft
draw recRight


start Number trigger -> 0
start Number trigger2 -> 0


each 30 ms:

	set recLeft center -> 1,0
    set recRight center -> -1,0

	if recLeft collides recRight:
        set recLeft center -> -2,0
        set recRight center -> 0,2
		set recLeft color -> 216,22,22
        set recRight color -> 216,22,22
	end

    if recLeft collides boardLeft:
        set retLeft center -> 0,0
        set recRight center -> 0,0
        set recLeft color -> 0,0,0
        set recRight color -> 0,0,0
        set recLeft filled -> true
        set recLeft filled -> true
        draw circTopRight
        draw circBottomLeft
        set trigger -> 1
    end

    if trigger = 1:
        draw circTop
        draw circBottom
        set circleTop center -> 0,-2
        set circleBottom center -> 0,2
        if circleTop collides circleBottom:
            set circleTop center -> 0,2
            set circleBottom center -> 0,-2
            
        end
    end

    if circTop collides boardTop: 
        set circleTop center -> 0,-2 
        set circleTop filled -> true
        set trigger2 -> 1
    end

    if circBottom collides boardBottom:
        set circleBottom center -> 0,2
        set circleTop filled -> true
        set trigger2 -> 1
    end

    if trigger2 equals 1:
        set circTopRight center -> -1,-1
        set circBottom center -> 1,1
    end
      
end