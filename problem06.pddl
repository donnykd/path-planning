(define (problem ladders) (:domain explorer)
(:objects 
    player - explorer
    block1_1 block1_2 block1_3
    block1_4 block1_5 block1_6 
    block1_7 block1_8 block1_9
    block2_1 block2_2 block2_3
    block2_4 block2_5 block2_6 
    block2_7 block2_8 block2_9 - location
    ladder - ladder
    key - key
)

(:init
    (located player block1_1)
    (connected block1_1 block1_2)
    (connected block1_1 block1_4)
    (connected block1_2 block1_3)
    (connected block1_2 block1_5)
    (connected block1_3 block1_6)
    (connected block1_4 block1_5)
    (connected block1_4 block1_7)
    (connected block1_5 block1_6)
    (connected block1_5 block1_8)
    (connected block1_6 block1_9)
    (connected block1_7 block1_8)
    (connected block1_8 block1_9)
    (connected block2_1 block2_2)
    (connected block2_1 block2_4)
    (connected block2_2 block2_3)
    (connected block2_2 block2_5)
    (connected block2_3 block2_6)
    (connected block2_4 block2_5)
    (connected block2_4 block2_7)
    (connected block2_5 block2_6)
    (connected block2_5 block2_8)
    (connected block2_6 block2_9)
    (connected block2_7 block2_8)
    (connected block2_8 block2_9)
    (free block1_2)
    (free block1_3)
    (free block1_4)
    (free block1_5)
    (free block1_6)
    (free block1_7)
    (free block1_8)
    (free block1_9)
    (free block2_1)
    (free block2_2)
    (free block2_3)
    (free block2_4)
    (free block2_5)
    (free block2_6)
    (free block2_7)
    (free block2_8)
    (free block2_9)
    (above block2_5 block1_5)
    (on key block2_9)
    (on ladder block2_5)
)

(:goal (and
    (stored player key)
    (located player block1_1)
))

)
