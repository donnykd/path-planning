(define (problem move-up) (:domain explorer)
(:objects 
    player - explorer
    block0_9
    block0_8 
    block0_7
    block0_6 
    block0_5 
    block0_4 
    block0_3
    block0_2 
    block0_1 - vector
)

(:init
    (located player block0_1)
    (up block0_2 block0_1)
    (up block0_3 block0_2)
    (up block0_4 block0_3)
    (up block0_5 block0_4)
    (up block0_6 block0_5)
    (up block0_7 block0_6)
    (up block0_8 block0_7)
    (up block0_9 block0_8)
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
    (located player block0_9)
))

)