;;!pre-parsing:{type: "jinja2", data: "case1.json"}

;;;problem to test and try out the move action
;;;combination of old move actions; move_left, move_right, etc.
(define (problem paths) (:domain explorer)
(:objects
    {{data.explorer}} - explorer

    {% for block in data.blocks %}{% if not loop.last %}{{ block }}
    {% else %}{{ block }} - location {% endif %}
    {% endfor %}
)

(:init
    (= (total-cost) 0)
    ;definining locations
    (located {{data.explorer}} block1)
    ;defining connectivity between blocks for player to go through
    (connected {{data.blocks[0]}} {{data.blocks[1]}})
    (connected {{data.blocks[0]}} {{data.blocks[3]}})
    (connected {{data.blocks[1]}} {{data.blocks[2]}})
    (connected {{data.blocks[1]}} {{data.blocks[4]}})
    (connected {{data.blocks[2]}} {{data.blocks[5]}})
    (connected {{data.blocks[3]}} {{data.blocks[4]}})
    (connected {{data.blocks[3]}} {{data.blocks[6]}})
    (connected {{data.blocks[4]}} {{data.blocks[5]}})
    (connected {{data.blocks[4]}} {{data.blocks[7]}})
    (connected {{data.blocks[5]}} {{data.blocks[8]}})
    (connected {{data.blocks[6]}} {{data.blocks[7]}})
    (connected {{data.blocks[7]}} {{data.blocks[8]}})
    ;all blocks free except ones occupied by an entity
    (free {{data.blocks[1]}})
    (free {{data.blocks[2]}})
    (free {{data.blocks[3]}})
    (free {{data.blocks[4]}})
    (free {{data.blocks[5]}})
    (free {{data.blocks[6]}})
    (free {{data.blocks[7]}})
    (free {{data.blocks[8]}})
)

(:goal (and
    (located player {{data.blocks[8]}})
))
(:metric minimize (total-cost))
)