(define (problem door-interactions) (:domain explorer)
(:objects 
    player - explorer
    block1 block2 block3
    block4 block5 block6 
    block7 block8 block9 - location
    key - key
)

(:init
    (= (cost) 0)
    ;definining locations
    (located player block8)
    (on key block9)
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
    (free block5)
    (free block6)
    (free block7)
    (free block9)
    (blocked block4 block7)
    (blocked block5 block8)
    (blocked block6 block9)
    (door block5 block8)
    (type_door key)
)

(:goal (and
    (blocked block5 block8)
    (located player block1)
))
)
