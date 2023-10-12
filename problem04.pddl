(define (problem move-down-01) (:domain explorer)
(:objects 
    player - explorer
)

(:init
    (check_down player)
)

(:goal (and
    (down player)
))

)