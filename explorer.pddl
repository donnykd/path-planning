(define (domain explorer)
(:requirements :strips :negative-preconditions
    :typing :disjunctive-preconditions)

(:types location item entity - object
    explorer box - entity
    chest - box
)

(:predicates 
    ;Player or box/chest on a block
    (located ?i - entity ?x - location)
    ;certain item is on a block
    (on ?k - item ?x - location)
    (connected ?x - location ?y - location)
    ;checks if there isnt anyone occupying a block
    (free ?x - location)
    ;either a chest or player is holding a key
    (stored ?i - entity ?k - item)
    (picked ?k - item)
    (locked ?c - chest)
    ;no direct access between both location
    (blocked ?x - location ?y - location)
    ;similar to blocked but can be opened
    (door ?x - location ?y - location)
    ;used to open door
    (in_door ?k - item)
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
    )
)

;pick up item from currently located block
(:action pick_up
    :parameters (?e - explorer ?k - item ?x - location)
    :precondition (and
        (located ?e ?x)
        (on ?k ?x)
     )
    :effect (and
        (stored ?e ?k)
        (picked ?k)
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
    )
)

;push box
(:action push
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
    )
)

;interaction between 2 explorers to give key to another
(:action give
    :parameters (?e - explorer ?p - explorer ?k - item 
        ?x - location ?y - location)
    :precondition (and 
        (stored ?e ?k)
        (not(stored ?p ?k))
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
        (not(stored ?e ?k))
        (stored ?p ?k)
    )
)

;open chest
(:action open
    :parameters (?e - explorer ?k - item ?x - location 
        ?y - location    ?c - chest)
    :precondition (and 
        (located ?c ?x)
        (locked ?c)
        (located ?e ?y)
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
    )
)

;unlock door
(:action unlock
    :parameters (?e - explorer ?x - location ?y - location
         ?k - item)
    :precondition (and 
        (located ?e ?x)
        (stored ?e ?k)
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
    )
)

;remove key
(:action remove_key
    :parameters (?e - explorer ?x - location ?y - location
         ?k - item)
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
    )
)


;lock door
(:action lock
    :parameters (?e - explorer ?x - location ?y - location
         ?k - item)
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
    )
)


    

)