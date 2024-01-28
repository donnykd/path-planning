;;!pre-parsing:{type: "jinja2", data: "case1.json"}
;;;Problem checks the interactions with doors and blocked terrain
(define (problem door-interactions) (:domain explorer)
(:objects 
    {{data.explorer}} - explorer
    {% for block in data.blocks %}{% if not loop.last %}{{ block }}
    {% else %}{{ block }} - location {% endif %}{% endfor %}
    key - key
)

(:init
    (= (total-cost) 0)
    ;definining locations
    (located {{data.explorer}} block8)
    (on key block9)
    (door block5 block8)
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
    (blocked block4 block7)
    (blocked block5 block8)
    (blocked block6 block9)
    ;all blocks free except ones occupied by an entity
    {% for block in data.blocks %}{% if loop.index != 8 %}
    (free {{data.blocks[loop.index-1]}}){% endif %}{% endfor %}
    ;item properties
    (type_door key)
)

(:goal (and
    (blocked block5 block8)
    (located player block1)
))
(:metric minimize (total-cost))
)
