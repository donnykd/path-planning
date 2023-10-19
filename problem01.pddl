(define (problem move-left) 

(:domain explorer)

(:objects
    player - explorer
    block1_0 block2_0 block3_0 block4_0 block5_0 block6_0 block7_0 block8_0 block9_0 - vector
)

(:init
    (located player block9_0)
    (left block8_0 block9_0)
    (left block7_0 block8_0)
    (left block6_0 block7_0)
    (left block5_0 block6_0)
    (left block4_0 block5_0)
    (left block3_0 block4_0)
    (left block2_0 block3_0)
    (left block1_0 block2_0)
    (free block1_0)
    (free block2_0)
    (free block3_0)
    (free block4_0)
    (free block5_0)
    (free block6_0)
    (free block7_0)
    (free block8_0)
)

(:goal (and
    (located player block1_0)
    )

)
    
)