(define (domain explorer)
(:requirements :strips :negative-preconditions :typing)

(:types explorer)

(:predicates 
    (check_left ?e - explorer)
    (left ?e - explorer)    
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
    

)