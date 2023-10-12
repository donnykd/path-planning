(define (problem move-up-01) (:domain explorer)
(:objects 
    player - explorer
)

(:init
    (check_up player)
)

(:goal (and
    (up player)
))

)