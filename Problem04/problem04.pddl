;;!pre-parsing:{type: "jinja2", data: "..\case.json"}
;;;Problem was defined to test the interaction between players
(define (problem give_item) (:domain explorer)
(:objects 
    {% for explorer in data.eight_explorers %}{% if not loop.last %}{{ explorer }}{% else %}{{ explorer }} - explorer {% endif %}
    {% endfor %}
    {% for block in data.blocks %}{% if not loop.last %}{{ block }}
    {% else %}{{ block }} - location {% endif %}{% endfor %}
    
    key - key
    chest - chest
)

(:init
    (= (total-cost) 0)
    ;definining locations
    (located player1 block2)
    (located player2 block3)
    (located player3 block4)
    (located player4 block5)
    (located player5 block6)
    (located player6 block7)
    (located player7 block8)
    (located player8 block9)
    (on key block9)
    (located chest block1)
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
    ;item properties
    (locked chest)
    (type_chest key)
)

(:goal (and
    (not(locked chest))
))
(:metric minimize (total-cost))
)
