(define (problem give_item) (:domain explorer)
(:objects 
    player1 player2 - explorer
    block1 block2 block3
    block4 block5 block6 
    block7 block8 block9 - location
    key - item
    chest - chest
)

(:init
    (located player1 block5)
    (located player2 block6)
    (on key block9)
    (located chest block1)
    (connected block1 block2)
    (connected block1 block4)
    (connected block2 block3)
    (connected block2 block5)
    (connected block3 block6)
    (connected block4 block5)
    (connected block4 block7)
    (connected block5 block6)
    (connected block5 block8)
    (connected block6 block9)
    (connected block7 block8)
    (connected block8 block9)
    (free block1)
    (free block2)
    (free block3)
    (free block4)
    (free block7)
    (free block8)
    (free block9)
    (locked chest)
)

(:goal (and
    (not(locked chest))
))
)
