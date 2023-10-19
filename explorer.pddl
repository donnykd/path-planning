(define (domain explorer)
(:requirements :strips :negative-preconditions :typing)

(:types explorer vector)

(:predicates 
    (located ?e - explorer ?x - vector)
    (left ?x - vector ?y - vector)    
    (right ?x - vector ?y - vector)
    (up ?x - vector ?y - vector)
    (down ?x - vector ?y - vector)
    (free ?x - vector)
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

    

)