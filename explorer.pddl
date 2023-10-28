(define (domain explorer)
(:requirements :strips :negative-preconditions
    :typing :disjunctive-preconditions)

(:types explorer location item box)

(:predicates 
    (located ?e - explorer ?x - location)
    (on ?k - item ?x - location)
    (connected ?x - location ?y - location)
    (free ?x - location)
    (stored ?e - explorer ?k - item)
    (not_picked ?k - item)
    (placed_at ?b - box ?x - location)
    (locked ?b - box)
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
        (or
            (not(blocked ?x ?y))
            (not(blocked ?y ?x))
        )
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
    :parameters (?e - explorer ?k - item ?x - location ?b - box)
    :precondition (and 
        (placed_at ?b ?x)
        (locked ?b)
        (located ?e ?x)
        (stored ?e ?k)
    )
    :effect (and 
        (not(locked ?b))
        (not(stored ?e ?k))
    )
)


    

)