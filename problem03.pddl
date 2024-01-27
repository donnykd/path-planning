;;!pre-parsing:{type: "jinja2", data: "case1.json"}

;;;Problem to test every feature connected with chests
(define (problem pick_up_item) (:domain explorer)
(:objects 
    {{data.explorer}} - explorer
    {% for block in data.blocks %}{% if not loop.last %}{{ block }}
    {% else %}{{ block }} - location {% endif %}{% endfor %}
    key - key
    chest - chest
    trophy - trophy
)

(:init
    (= (total-cost) 0)
    ;definining locations
    (located {{data.explorer}} block1)
    (on key block9)
    (located chest block3)
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
    {% for block in data.blocks %}{% if loop.index != 1 and loop.index != 3 and loop.index != 11%}
    (free {{data.blocks[loop.index-1]}}){% endif %}{% endfor %}
    ;item properties
    (locked chest)
    (type_chest key)
    (stored chest trophy)
)

(:goal (and
    (on trophy block1)
))
(:metric minimize (total-cost))
)
