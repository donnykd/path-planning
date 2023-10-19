(define (problem move-left-01) 

(:domain explorer)

(:objects
    player - explorer
    block0_1 block0_2 - vector
)

(:init
    (located player block0_2)
    (left block0_1 block0_2)
)

(:goal (and
    (located player block0_1)
    )

)
    
)