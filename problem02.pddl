;;;Problem initially made to test the traversal of 2 players between blocks,
;;;became more complex with the addition of extra actions and predicate.
;;;Problem now checks the traversal of 2 players with a wall inbetween them,
;;;a passageway and a chair blocking the way.
(define (problem paths_extended) (:domain explorer)
(:objects
    player1 player2 - explorer
    block1 block2 block3 block4
    block5 block6 block7 block8
    block9 block10 block11 block12
    block13 block14 block15 block16 - location
    chair - box
)

(:init
    (= (total-cost) 0)
    ;definining locations
    (located player1 block1)
    (located player2 block16)
    (located chair block11)
    ;defining connectivity between blocks for player to go through
    (connected block1 block2)
    (connected block2 block3)
    (connected block3 block4)
    (connected block5 block6)
    (connected block6 block7)
    (connected block7 block8)
    (connected block9 block10)
    (connected block10 block11)
    (connected block11 block12)
    (connected block13 block14)
    (connected block14 block15)
    (connected block15 block16)
    (connected block11 block10)
    (connected block1 block5)
    (connected block2 block6)
    (connected block3 block7)
    (connected block4 block8)
    (connected block5 block9)
    (connected block6 block10)
    (connected block7 block11)
    (connected block8 block12)
    (connected block9 block13)
    (connected block10 block14)
    (connected block11 block15)
    (connected block12 block16)
    (blocked block5 block9)
    (blocked block6 block10)
    (blocked block8 block12)
    ;all blocks free except ones occupied by an entity
    (free block2)
    (free block3)
    (free block4)
    (free block5)
    (free block6)
    (free block7)
    (free block8)
    (free block9)
    (free block10)
    (free block12)
    (free block13)
    (free block14)
    (free block15)
)

(:goal (and
    (located player1 block16)
    (located player2 block1)
))
(:metric minimize (total-cost))
)