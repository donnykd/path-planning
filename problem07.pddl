(define (problem pick_up_item) (:domain explorer)
(:objects 
    player - explorer
    block1 block2 block3
    block4 block5 block6 
    block7 block8 block9 - vector
    key - item
    chest - box
)

(:init
    (located player block1)
    (on key block9)
    (placed_at chest block3)
    (left block1 block2)
    (left block2 block3)
    (left block4 block5)
    (left block5 block6)
    (left block7 block8)
    (left block8 block9)
    (right block2 block1)
    (right block3 block2)
    (right block5 block4)
    (right block6 block5)
    (right block8 block7)
    (right block9 block8)
    (up block1 block4)
    (up block2 block5)
    (up block3 block6)
    (up block4 block7)
    (up block5 block8)
    (up block6 block9)
    (down block4 block1)
    (down block5 block2)
    (down block6 block3)
    (down block7 block4)
    (down block8 block5)
    (down block9 block6)
    (free block3)
    (free block4)
    (free block5)
    (free block6)
    (free block7)
    (free block8)
    (free block9)
    (not_picked key)
    (locked chest)
)

(:goal (and
    (not(locked chest))
))
)
