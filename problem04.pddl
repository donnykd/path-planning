;;;Problem was defined to test the interaction between players
(define (problem give_item) (:domain explorer)
(:objects 
    player1 player2 player3
    player4 player5 player6
    player7 player8 - explorer
    block1 block2 block3
    block4 block5 block6 
    block7 block8 block9 - location
    key - key
    chest - chest
)

(:init
    (= (cost) 0)
    ;definining locations
    (located player1 block2)
    (located player2 block3)
    (located player3 block4)
    (located player4 block5)
    (located player5 block6)
    (located player6 block7)
    (located player7 block8)
    (located player8 block9)
    (on key block9)
    (located chest block1)
    ;defining connectivity between blocks for player to go through
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
    ;all blocks free except ones occupied by an entity
    (free block9)
    ;item properties
    (locked chest)
    (type_chest key)
)

(:goal (and
    (not(locked chest))
))
(:metric minimize (cost))
)
