(define (problem move-right-01) (:domain explorer)
(:objects 
    player - explorer
    block0_1 block0_2 block0_3
    block0_4 block0_5 block0_6 
    block0_7 block0_8 block0_9 - vector
)

(:init
    (located player block0_1)
    (right block0_2 block0_1)
    (right block0_3 block0_2)
    (right block0_4 block0_3)
    (right block0_5 block0_4)
    (right block0_6 block0_5)
    (right block0_7 block0_6)
    (right block0_8 block0_7)
    (right block0_9 block0_8)
    (free block0_2)
    (free block0_3)
    (free block0_4)
    (free block0_5)
    (free block0_6)
    (free block0_7)
    (free block0_8)
    (free block0_9)
)

(:goal (and
    (located player block0_8)
))

)
