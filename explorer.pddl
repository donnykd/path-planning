(define (domain explorer)
(:requirements :strips :negative-preconditions :typing)

(:types explorer vector)

(:predicates 
    (located ?e - explorer ?x - vector)
    (left ?x - vector ?y - vector)    
    (check_right ?e - explorer)
    (right ?e - explorer)
    (check_up ?e - explorer)
    (up ?e - explorer)
    (check_down ?e - explorer)
    (down ?e - explorer)

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
    :parameters (?e - explorer)
    :precondition (and 
        (check_right ?e)
    )
    :effect (and 
        (right ?e)
    )
)

(:action move-up
    :parameters (?e - explorer)
    :precondition (and 
        (check_up ?e)
    )
    :effect (and 
        (up ?e)
    )
)

(:action move-down
    :parameters (?e - explorer)
    :precondition (and 
        (check_down ?e)
    )
    :effect (and 
        (down ?e)
    )
)

    

)