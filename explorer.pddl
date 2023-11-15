(define (domain explorer)
(:requirements :strips :negative-preconditions
    :typing :disjunctive-preconditions :fluents
    :action-costs
)

(:types location item entity - object
    explorer box - entity
    chest - box
    trophy tools - item
    key ladder - tools
)

(:functions 
    (cost)
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
    (in_door ?k - key)
    ;checks what type key
    (type_door ?k - key)
    (type_chest ?k - key)
) 

;move from one block to another
(:action move
    :parameters (?e - explorer ?x - location ?y - location)
    :precondition (and 
        (located ?e ?x)
        (free ?y)
        ;allows flexibility in init to only declare connection once
        (or
            (connected ?x ?y)
            (connected ?y ?x)
        )
        ;makes sure there is direct access
        (not(blocked ?x ?y))
        (not(blocked ?y ?x))
    )
    :effect (and 
        (located ?e ?y)
        (not(located ?e ?x))
        (free ?x)
        (not(free ?y))
        ;cost amount
        (increase (cost) 3)
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
        ;cost amount
        (increase (cost) 1)
     )
)

;pull box
(:action pull
    :parameters (?e - explorer ?b - box ?x - location
         ?y - location ?z - location)
    :precondition (and 
        (located ?b ?x)
        (located ?e ?y)
        (free ?z)
        ;allows flexibility in init to only declare connection once
        ;explorer and box connected
        (or
            (connected ?x ?y)
            (connected ?y ?x)
        )
        ;explorer is connected to the free space to pull back to
        (or
            (connected ?y ?z)
            (connected ?z ?y)
        )
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
        (increase (cost) 6)
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
        ;allows flexibility in init to only declare connection once
        ;explorer and box connected
        (or
            (connected ?x ?y)
            (connected ?y ?x)
        )
        ;box is connected to the space to be pushed to
        (or
            (connected ?x ?z)
            (connected ?z ?x)
        )
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
        (increase (cost) 6)
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
        (or
            (connected ?x ?y)
            (connected ?y ?x)
        )
        ;makes sure there is direct access
        (not(blocked ?x ?y))
        (not(blocked ?y ?x))
    )
    :effect (and 
        (not(stored ?e ?i))
        (stored ?p ?i)
        ;cost amount
        (increase (cost) 3)
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
        ;allows flexibility in init to only declare connection once
        (or
            (connected ?x ?y)
            (connected ?y ?x)
        )
        (stored ?e ?k)
    )
    :effect (and 
        (not(locked ?c))
        (not(stored ?e ?k))
        ;cost amount
        (increase (cost) 2)
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
        (not(in_door ?k))

    )
    :effect (and 
        (not (stored ?e ?k))
        (in_door ?k)
        (not (blocked ?x ?y))
        (not (blocked ?y ?x))
        ;cost amount
        (increase (cost) 3)
    )
)

;remove key
(:action remove_key
    :parameters (?e - explorer ?k - key ?x - location 
        ?y - location)
    :precondition (and 
        (located ?e ?x)
        (or
            (door ?x ?y)
            (door ?y ?x)
        )
        (in_door ?k)
    )
    :effect (and 
        (not(in_door ?k))
        (stored ?e ?k)
        ;cost amount
        (increase (cost) 2)
    )
)


;lock door
(:action lock
    :parameters (?e - explorer ?k - key ?x - location 
        ?y - location)
    :precondition (and 
        (stored ?e ?k)
        (located ?e ?x)
        (or
            (door ?x ?y)
            (door ?y ?x)
        )
        (not(in_door ?k))
    )
    :effect (and 
        (not (stored ?e ?k))
        (in_door ?k)
        (blocked ?x ?y)
        ;cost amount
        (increase (cost) 2)
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
        ;cost amount
        (increase (cost) 5)
    )
)

;pick up item from chest
(:action claim
    :parameters (?e - explorer ?c - chest ?i - item)
    :precondition (and 
        ;chest is storing item and open
        (not(locked ?c))
        (stored ?c ?i)
    )
    :effect (and 
        (not(stored ?c ?i))
        (stored ?e ?i)
        ;cost amount
        (increase (cost) 1)
    )
)

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
        (increase (cost) 1)
    )
)


)