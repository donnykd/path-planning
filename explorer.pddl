(define (domain explorer)
(:requirements :strips :negative-preconditions
    :typing :disjunctive-preconditions)

(:types location item entity - object
    explorer box - entity
    chest obstacle - box
)

(:predicates 
    (located ?i - entity ?x - location)
    (on ?k - item ?x - location)
    (connected ?x - location ?y - location)
    (free ?x - location)
    (stored ?i - entity ?k - item)
    (not_picked ?k - item)
    (locked ?c - chest)
    (blocked ?x - location ?y - location)
) 

(:action move
    :parameters (?e - explorer ?x - location ?y - location)
    :precondition (and 
        (located ?e ?x)
        (free ?y)
        (or
            (connected ?x ?y)
            (connected ?y ?x)
        )
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

(:action pick_up
    :parameters (?e - explorer ?k - item ?x - location)
    :precondition (and
        (located ?e ?x)
        (not_picked ?k)
        (on ?k ?x)
     )
    :effect (and
        (stored ?e ?k)
        (not(not_picked ?k))
     )
)

(:action pull
    :parameters (?e - explorer ?b - box ?x - location
         ?y - location ?z - location)
    :precondition (and 
        (located ?b ?x)
        (located ?e ?y)
        (free ?z)
        (or
            (connected ?x ?y)
            (connected ?y ?x)
        )
        (or
            (connected ?y ?z)
            (connected ?z ?y)
        )
        (or
            (not(blocked ?y ?z))
            (not(blocked ?z ?y))
        )
        (or
            (not(blocked ?y ?x))
            (not(blocked ?x ?y))
        )
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

(:action push
    :parameters (?e - explorer ?b - box ?x - location
         ?y - location ?z - location)
    :precondition (and 
        (located ?b ?x)
        (located ?e ?y)
        (free ?z)
        (or
            (connected ?x ?y)
            (connected ?y ?x)
        )
        (or
            (connected ?x ?z)
            (connected ?z ?x)
        )
        (or
            (not(blocked ?y ?x))
            (not(blocked ?x ?y))
        )
        (or
            (not(blocked ?z ?x))
            (not(blocked ?x ?z))
        )
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


(:action give
    :parameters (?e - explorer ?p - explorer ?k - item 
        ?x - location ?y - location)
    :precondition (and 
        (stored ?e ?k)
        (not(stored ?p ?k))
        (located ?e ?x)
        (located ?p ?y)
        (or
            (connected ?x ?y)
            (connected ?y ?x)
        )
        (or
            (not(blocked ?x ?y))
            (not(blocked ?y ?x))
        )
    )
    :effect (and 
        (not(stored ?e ?k))
        (stored ?p ?k)
    )
)


(:action unlock
    :parameters (?e - explorer ?k - item ?x - location ?c - chest)
    :precondition (and 
        (located ?c ?x)
        (locked ?c)
        (located ?e ?x)
        (stored ?e ?k)
    )
    :effect (and 
        (not(locked ?c))
        (not(stored ?e ?k))
    )
)


    

)