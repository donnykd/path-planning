;;!pre-parsing:{type: "jinja2", data: "..\case.json"}

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
    {% for block in data.blocks %} {% if not loop.last %} {% if loop.index == 3 or loop.index == 6 %}
    (connected {{data.blocks[loop.index-1]}} {{data.blocks[loop.index+2]}})
    (connected {{data.blocks[loop.index+2]}} {{data.blocks[loop.index-1]}})
    {% elif loop.index == 7 or loop.index == 8 %}
    (connected {{data.blocks[loop.index-1]}} {{data.blocks[loop.index]}})
    (connected {{data.blocks[loop.index]}} {{data.blocks[loop.index-1]}})
    {% else %}
    (connected {{data.blocks[loop.index-1]}} {{data.blocks[loop.index]}})
    (connected {{data.blocks[loop.index]}} {{data.blocks[loop.index-1]}})
    (connected {{data.blocks[loop.index-1]}} {{data.blocks[loop.index+2]}})
    (connected {{data.blocks[loop.index+2]}} {{data.blocks[loop.index-1]}})
    {%endif%}{%endif%}{%endfor%}
    ;all blocks free except ones occupied by an entity
    {% for block in data.blocks %}{% if not loop.first %}
    (free {{data.blocks[loop.index-1]}}){% endif %}{% endfor %}
)

(:goal (and
    (located player {{data.blocks[8]}})
))
(:metric minimize (total-cost))
)