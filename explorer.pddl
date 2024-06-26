(define (domain explorer)
(:requirements :strips :negative-preconditions
    :typing :disjunctive-preconditions :action-costs
)

(:types location item entity - object
    explorer box - entity
    chest - box
    trophy tools - item
    key ladder - tools
)

(:functions 
    (total-cost)
)

(:predicates 
    ;Player or box/chest on a block
    (located ?i - entity ?x - location)
    ;certain item is on a block
    (on ?i - item ?x - location)
    (connected ?x - location ?y - location)
    (above ?x - location ?y - location)
    ;checks if there isnt anyone occupying a block
    (free ?x - location)
    ;either a chest or player is holding a key
    (stored ?e - entity ?i - item)
    (locked ?c - chest)
    ;no direct access between both location
    (blocked ?x - location ?y - location)
    ;similar to blocked but can be opened
    (door ?x - location ?y - location)
    ;used to open door
    (in_door ?k - key ?x - location ?y - location)
    ;checks what type key
    (type_door ?k - key)
    (type_chest ?k - key)
    ;cannot move from current location
    (restricted ?e - explorer)
) 

;move from one block to another
(:action move
    :parameters (?e - explorer ?x - location ?y - location)
    :precondition (and 
        (located ?e ?x)
        (free ?y)
        ;allows flexibility in init to only declare connection once
        (connected ?x ?y)
        ;makes sure there is direct access
        (not(blocked ?x ?y))
        (not(blocked ?y ?x))
        (not(restricted ?e))
    )
    :effect (and 
        (located ?e ?y)
        (not(located ?e ?x))
        (free ?x)
        (not(free ?y))
        ;cost amount
        (increase (total-cost) 3)
    )
)

;pick up key from currently located block
(:action pick_up
    :parameters (?e - explorer ?i - item ?x - location)
    :precondition (and
        (located ?e ?x)
        (on ?i ?x)
     )
    :effect (and
        (stored ?e ?i)
        (not(on ?i ?x))
        ;cost amount
        (increase (total-cost) 1)
     )
)

;pull box
(:action pull
    :parameters (?e - explorer ?b - box ?z - location
         ?y - location ?x - location)
    :precondition (and 
        (located ?b ?x)
        (located ?e ?y)
        (free ?z)
        (not(restricted ?e))
        ;allows flexibility in init to only declare connection once
        ;explorer and box connected
        (connected ?x ?y)
        ;explorer is connected to the free space to pull back to
        (connected ?z ?y)
        ;makes sure there is direct access
        (not(blocked ?y ?z))
        (not(blocked ?z ?y))
        (not(blocked ?y ?x))
        (not(blocked ?x ?y))
    )
    :effect (and 
        (located ?b ?y)
        (not(located ?b ?x))
        (located ?e ?z)
        (not(located ?e ?y))
        (free ?x)
        (not(free ?z))
        ;cost amount
        (increase (total-cost) 6)
    )
)

;push box
(:action push
    :parameters (?e - explorer ?b - box ?y - location
         ?x - location ?z - location)
    :precondition (and 
        (located ?b ?x)
        (located ?e ?y)
        (free ?z)
        (not(restricted ?e))
        ;allows flexibility in init to only declare connection once
        ;explorer and box connected
        (connected ?x ?y)
        ;box is connected to the space to be pushed to
        (connected ?x ?z)
        ;makes sure there is direct access
        (not(blocked ?y ?x))
        (not(blocked ?x ?y))
        (not(blocked ?z ?x))
        (not(blocked ?x ?z))
    )
    :effect (and 
        (located ?b ?z)
        (not(located ?b ?x))
        (located ?e ?x)
        (not(located ?e ?y))
        (free ?y)
        (not(free ?z))
        ;cost amount
        (increase (total-cost) 6)
    )
)

;interaction between 2 explorers to give key to another
(:action give
    :parameters (?e - explorer ?p - explorer ?i - item 
        ?x - location ?y - location)
    :precondition (and 
        (stored ?e ?i)
        (not(stored ?p ?i))
        ;both explorers are next to each other
        (located ?e ?x)
        (located ?p ?y)
        ;allows flexibility in init to only declare connection once
        (connected ?x ?y)
        ;makes sure there is direct access
        (not(blocked ?x ?y))
        (not(blocked ?y ?x))
    )
    :effect (and 
        (not(stored ?e ?i))
        (stored ?p ?i)
        ;cost amount
        (increase (total-cost) 3)
    )
)

;open chest
(:action open
    :parameters (?e - explorer ?k - key ?c - chest 
        ?x - location ?y - location)
    :precondition (and 
        (located ?c ?x)
        (locked ?c)
        (located ?e ?y)
        ;key type chest
        (type_chest ?k)
        (stored ?e ?k)
        ;allows flexibility in init to only declare connection once
        (connected ?x ?y)
        (not(blocked ?x ?y))
        (not(blocked ?y ?x))
    )
    :effect (and 
        (not(locked ?c))
        (not(stored ?e ?k))
        ;cost amount
        (increase (total-cost) 2)
    )
)

;unlock door
(:action unlock
    :parameters (?e - explorer ?k - key ?x - location 
        ?y - location)
    :precondition (and 
        (located ?e ?x)
        (stored ?e ?k)
        ;key type door
        (type_door ?k)
        ;if access between 2 blocks is blocked and there is a door
        (or
            (door ?x ?y)
            (door ?y ?x)
        )
        (or
            (blocked ?x ?y)
            (blocked ?y ?x)
        )
        ;key not in door
        (or
          (not(in_door ?k ?x ?y))
          (not(in_door ?k ?y ?x))
        )

    )
    :effect (and 
        (not (stored ?e ?k))
        (in_door ?k ?x ?y)
        (not (blocked ?x ?y))
        (not (blocked ?y ?x))
        ;cost amount
        (increase (total-cost) 3)
    )
)

;remove key
(:action remove_key
    :parameters (?e - explorer ?k - key ?x - location 
        ?y - location)
    :precondition (and 
        (type_door ?k)
        (located ?e ?x)
        (or
            (door ?x ?y)
            (door ?y ?x)
        )
        (or
        (in_door ?k ?x ?y)
        (in_door ?k ?y ?x)
        )
    )
    :effect (and 
        (not(in_door ?k ?x ?y))
        (not(in_door ?k ?y ?x))
        (stored ?e ?k)
        ;cost amount
        (increase (total-cost) 2)
    )
)


;lock door
(:action lock
    :parameters (?e - explorer ?k - key ?x - location 
        ?y - location)
    :precondition (and 
        (stored ?e ?k)
        (located ?e ?x)
        (type_door ?k)
        (or
            (door ?x ?y)
            (door ?y ?x)
        )
        ;key not in door
        (or
          (not(in_door ?k ?x ?y))
          (not(in_door ?k ?y ?x))
        )
    )
    :effect (and 
        (not (stored ?e ?k))
        (in_door ?k ?x ?y)
        (blocked ?x ?y)
        ;cost amount
        (increase (total-cost) 2)
    )
)

(:action climb
    :parameters (?e - explorer ?l - ladder 
        ?x - location ?y - location
    )
    :precondition (and 
        (or
            (on ?l ?x)
            (on ?l ?y)
        )
        (located ?e ?x)
        (free ?y)
        (not(restricted ?e))
        ;either above or below
        (or
            (above ?y ?x)
            (above ?x ?y)
        )
    )
    :effect (and 
        (located ?e ?y)
        (not(located ?e ?x))
        (free ?x)
        (not(free ?y))
        ;cost amount
        (increase (total-cost) 5)
    )
)

;pick up item from chest
(:action claim
    :parameters (?e - explorer ?c - chest ?i - item
        ?x - location ?y - location
    )
    :precondition (and 
        ;chest is storing item and open
        (not(locked ?c))
        (stored ?c ?i)
        (located ?c ?x)
        (located ?e ?y)
        (connected ?x ?y)
    )
    :effect (and 
        (not(stored ?c ?i))
        (stored ?e ?i)
        ;cost amount
        (increase (total-cost) 1)
    )
)

;put item stored down
(:action place_down
    :parameters (?e - explorer ?i - item ?x - location)
    :precondition (and 
        (located ?e ?x)
        (stored ?e ?i)
    )
    :effect (and 
        (not(stored ?e ?i))
        (on ?i ?x)
        ;cost amount
        (increase (total-cost) 1)
    )
)


)