(define (problem move-right) (:domain explorer)
(:objects 
    player - explorer
    block1_0 block2_0 block3_0 block4_0 block5_0 block6_0 block7_0 block8_0 block9_0 - vector
)

(:init
    (located player block1_0)
    (right block2_0 block1_0)
    (right block3_0 block2_0)
    (right block4_0 block3_0)
    (right block5_0 block4_0)
    (right block6_0 block5_0)
    (right block7_0 block6_0)
    (right block8_0 block7_0)
    (right block9_0 block8_0)
    (free block2_0)
    (free block3_0)
    (free block4_0)
    (free block5_0)
    (free block6_0)
    (free block7_0)
    (free block8_0)
    (free block9_0)
)

(:goal (and
    (located player block8_0)
))

)
