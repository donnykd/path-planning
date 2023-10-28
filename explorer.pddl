(define (domain explorer)
(:requirements :strips :negative-preconditions :typing :disjunctive-preconditions)

(:types explorer vector item box)

(:predicates 
    (located ?e - explorer ?x - vector)
    (on ?k - item ?x - vector)
    (left ?x - vector ?y - vector)    
    (right ?x - vector ?y - vector)
    (up ?x - vector ?y - vector)
    (down ?x - vector ?y - vector)
    (free ?x - vector)
    (stored ?e - explorer ?k - item)
    (not_picked ?k - item)
    (placed_at ?b - box ?x - vector)
    (locked ?b - box)
) 

(:action move-left 
    :parameters (?e - explorer ?x - vector ?y - vector)
    :precondition (and 
        (located ?e ?x)
        (left ?y ?x)
        (free ?y)
        )
    :effect (and 
        (located ?e ?y)
        (not(located ?e ?x))
        (free ?x)
        (not(free ?y))
        )
    
    )

(:action move-right
    :parameters (?e - explorer ?x - vector ?y - vector)
    :precondition (and 
        (located ?e ?x)
        (right ?y ?x)
        (free ?y)
    )
    :effect (and 
        (located ?e ?y)
        (not(located ?e ?x))
        (free ?x)
        (not(free ?y))
    )
)

(:action move-up
    :parameters (?e - explorer ?x - vector ?y - vector)
    :precondition (and 
        (located ?e ?x)
        (up ?y ?x)
        (free ?y)
    )
    :effect (and 
        (located ?e ?y)
        (not(located ?e ?x))
        (free ?x)
        (not(free ?y))
    )
)

(:action move-down
    :parameters (?e - explorer ?x - vector ?y - vector)
    :precondition (and 
        (located ?e ?x)
        (down ?y ?x)
        (free ?y)
    )
    :effect (and 
        (located ?e ?y)
        (not(located ?e ?x))
        (free ?x)
        (not(free ?y))
    )
)

(:action pick_up
    :parameters (?e - explorer ?k - item ?x - vector)
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
        ?x - vector ?y - vector
    )
    :precondition (and 
        (stored ?e ?k)
        (not(stored ?p ?k))
        (located ?e ?x)
        (located ?p ?y)
        (or
            (right ?x ?y)
            (left ?x ?y)
            (up ?x ?y)
            (down ?x ?y)
        )
    )
    :effect (and 
        (not(stored ?e ?k))
        (stored ?p ?k)
    )
)


(:action open_box
    :parameters (?e - explorer ?k - item ?x - vector ?b - box)
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