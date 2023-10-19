(define (problem move-left-01) 

(:domain explorer)

(:objects
    player - explorer
    block0_1 block0_2 block0_3
    block0_4 block0_5 block0_6 
    block0_7 block0_8 block0_9 - vector
)

(:init
    (located player block0_9)
    (left block0_8 block0_9)
    (left block0_7 block0_8)
    (left block0_6 block0_7)
    (left block0_5 block0_6)
    (left block0_4 block0_5)
    (left block0_3 block0_4)
    (left block0_2 block0_3)
    (left block0_1 block0_2)
    (free block0_1)
    (free block0_2)
    (free block0_3)
    (free block0_5)
    (free block0_4)
    (free block0_6)
    (free block0_7)
    (free block0_8)
)

(:goal (and
    (located player block0_1)
    )

)
    
)