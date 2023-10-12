(define (problem move-right-01) (:domain explorer)
(:objects 
    player - explorer
)

(:init
    (check_right player)
)

(:goal (and
    (right player)
))

)
