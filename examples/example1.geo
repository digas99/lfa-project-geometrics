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

start Circle circBottom:
	diameter -> 50
	thickness -> 5
	filled -> false
	color -> 209, 19, 85
	center -> 0,200
end

start Circle circTop:
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
start Number leftAndRightCollided -> 0
start Number recSpeed -> 5

start Number twoWorldsCollide -> 0
start Number stopRec -> 0


each 30 ms:

    if leftAndRightCollided = 0 and (stopRec = 0):
	    set recLeft center -> (recLeft center x + recSpeed), 0
        set recRight center -> (recRight center x - recSpeed), 0
    end

    if recLeft collides recRight:
        set leftAndRightCollided -> 1
    end    

    if leftAndRightCollided = 1 and (stopRec = 0):
        set recLeft center -> (recLeft center x - recSpeed), 0
        set recRight center -> (recRight center x + recSpeed), 0
        set recLeft color -> 216,22,22
        set recRight color -> 216,22,22
    end

    if recLeft collides boardLeft or recRight collides boardRight:
        set twoWorldsCollide -> 1
        set stopRec -> 1
    end

    if twoWorldsCollide = 1:
        set recLeft color -> 0,0,0
        set recRight color -> 0,0,0
        set recLeft angle -> 45 deg
        set recRight angle -> -45 deg
        set recLeft filled -> true
        set recRight filled -> true
        draw circTopRight
        draw circBottomLeft
        set trigger -> 1
    end

    start Number moveCircles -> 0
    if trigger = 1:
        draw circTop
        draw circBottom
        if moveCircles = 0:
            set circTop center -> 0,circTop center y + 2
            set circBottom center -> 0,circBottom center y - 2
        end
        if circTop collides circBottom:
            set moveCircles -> 1
        end
        if moveCircles = 1:
            set circTop center -> 0,(circTop center y - 5)
            set circBottom center -> 0,(circBottom center y + 5)
        end
    end

    if circTop collides boardTop:
        set moveCircles -> 2 
        set circTop filled -> true
        set circBottom filled -> true
        set trigger2 -> 1
        set circBottom diameter -> 200
        set circTop diameter -> 200
    end

    start Number trigger3 -> 0

    if trigger2 equals 1 and (trigger3 = 0):
        set circTopRight center -> (circTopRight center x - 5),circTopRight center y - 5
        set circBottomLeft center -> (circBottomLeft center x + 5),circBottomLeft center y + 5
    end

    if (circTopRight center x = circBottomLeft center x) and (circTopRight center y = circBottomLeft center y):
        set trigger3 -> 1
    end
end     
