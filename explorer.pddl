(define (domain explorer)
(:requirements :strips :negative-preconditions :typing)

(:types explorer)

(:predicates 
    (check_left ?e - explorer)
    (left ?e - explorer)    
    (check_right ?e - explorer)
    (right ?e - explorer)
) 

(:action move-left 
    :parameters (?e - explorer)
    :precondition (and 
        (check_left ?e)
        
        )
    :effect (and 
        (left ?e)
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

    

)