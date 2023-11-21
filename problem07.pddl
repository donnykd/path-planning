(define (problem combination) (:domain explorer)
(:objects 
    player npc - explorer
    block1_1 block1_2 block1_3
    block1_4 block1_5 block1_6 
    block1_7 block1_8 block1_9
    block1_10 block1_11 block1_12
    block1_13 block1_14 block1_15 
    block1_16 block1_17 block1_18
    block2_1 block2_2 block2_3
    block2_4 block2_5 block2_6 
    block2_7 block2_8 block2_9
    block2_10 block2_11 block2_12
    block2_13 block2_14 block2_15 
    block2_16 block2_17 block2_18 - location
    ladder - ladder
    door_key chest_key - key
    trophy_chest - chest
    obstacle1 obstacle2 - box
    trophy - trophy
)

(:init
    (= (cost) 0)
    ;definining locations
    (located player block1_1)
    (located npc block2_2)
    (on door_key block1_9)
    (on chest_key block2_10)
    (located obstacle1 block1_17)
    (located obstacle2 block2_15)
    (on ladder block1_18)
    (located trophy_chest block2_1)
    (restricted npc)
    ;defining connectivity between blocks for player to go through
    ;first floor connectivity
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
    (connected block1_7 block1_10)
    (connected block1_8 block1_9)
    (connected block1_8 block1_11)
    (connected block1_9 block1_12)
    (connected block1_10 block1_11)
    (connected block1_10 block1_13)
    (connected block1_11 block1_12)
    (connected block1_11 block1_14)
    (connected block1_12 block1_15)
    (connected block1_13 block1_14)
    (connected block1_13 block1_16)
    (connected block1_14 block1_15)
    (connected block1_14 block1_17)
    (connected block1_15 block1_18)
    (connected block1_16 block1_17)
    (connected block1_17 block1_18)
    ;second floor connectivity
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
    (connected block2_7 block2_10)
    (connected block2_8 block2_9)
    (connected block2_8 block2_11)
    (connected block2_9 block2_12)
    (connected block2_10 block2_11)
    (connected block2_10 block2_13)
    (connected block2_11 block2_12)
    (connected block2_11 block2_14)
    (connected block2_12 block2_15)
    (connected block2_13 block2_14)
    (connected block2_13 block2_16)
    (connected block2_14 block2_15)
    (connected block2_14 block2_17)
    (connected block2_15 block2_18)
    (connected block2_16 block2_17)
    (connected block2_17 block2_18)
    ;first floor blockage
    (blocked block1_1 block1_2)
    (blocked block1_4 block1_5)
    (blocked block1_7 block1_8)
    (blocked block1_13 block1_14)
    (blocked block1_13 block1_16)
    (blocked block1_14 block1_17)
    (blocked block1_15 block1_18)
    ;second floor blockage
    (blocked block2_18 block2_17)
    (blocked block2_15 block2_14)
    (blocked block2_12 block2_11)
    (blocked block2_8 block2_9)
    (blocked block2_5 block2_6)
    (blocked block2_2 block2_3)
    (blocked block2_2 block2_5)
    (blocked block2_1 block2_4)
    (blocked block2_10 block2_13)
    (blocked block2_11 block2_14)
    ;all blocks free except ones occupied by an entity
    (free block1_2)
    (free block1_3)
    (free block1_4)
    (free block1_5)
    (free block1_6)
    (free block1_7)
    (free block1_8)
    (free block1_9)
    (free block1_10)
    (free block1_11)
    (free block1_12)
    (free block1_13)
    (free block1_14)
    (free block1_15)
    (free block1_16)
    (free block1_18)
    (free block2_3)
    (free block2_4)
    (free block2_5)
    (free block2_6)
    (free block2_7)
    (free block2_8)
    (free block2_9)
    (free block2_10)
    (free block2_11)
    (free block2_12)
    (free block2_13)
    (free block2_14)
    (free block2_16)
    (free block2_17)
    (free block2_18)
    ;doors and floors
    (door block1_10 block1_11)
    (door block1_13 block1_16)
    (door block2_2 block2_3)
    (door block2_8 block2_9)
    (above block2_18 block1_18)
    ;item properties
    (type_chest chest_key)
    (type_door door_key)
    (stored trophy_chest trophy)
    (locked trophy_chest)
)

(:goal (and
    (on trophy block1_6)
))


(:metric minimize (cost))
)
