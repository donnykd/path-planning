;;!pre-parsing:{type: "jinja2", data: "case4.json"}
;;;Problem defined to test the dimensionality of blocks and how to traverse through them
(define (problem ladders) (:domain explorer)
(:objects 
    {{data.explorer}} - explorer
    {% for block in data.first_floor_blocks %}{{ block }} 
    {% endfor %}
    {% for block in data.second_floor_blocks %}{% if not loop.last %}{{ block }}{% else %}{{ block }} - location {% endif %}
    {% endfor %}
    ladder - ladder
    key - key
)

(:init
    (= (total-cost) 0)
    ;definining locations
    (located {{data.explorer}} block1_1)
    (on key block2_9)
    (on ladder block2_5)

    ;defining connectivity between blocks for player to go through
    {% for block in data.first_floor_blocks %} {% if not loop.last %} {% if loop.index == 3 or loop.index == 6 %}
    (connected {{data.first_floor_blocks[loop.index-1]}} {{data.first_floor_blocks[loop.index+2]}})
    (connected {{data.first_floor_blocks[loop.index+2]}} {{data.first_floor_blocks[loop.index-1]}})
    {% elif loop.index == 7 or loop.index == 8 %}
    (connected {{data.first_floor_blocks[loop.index-1]}} {{data.first_floor_blocks[loop.index]}})
    (connected {{data.first_floor_blocks[loop.index]}} {{data.first_floor_blocks[loop.index-1]}})
    {% else %}
    (connected {{data.first_floor_blocks[loop.index-1]}} {{data.first_floor_blocks[loop.index]}})
    (connected {{data.first_floor_blocks[loop.index]}} {{data.first_floor_blocks[loop.index-1]}})
    (connected {{data.first_floor_blocks[loop.index-1]}} {{data.first_floor_blocks[loop.index+2]}})
    (connected {{data.first_floor_blocks[loop.index+2]}} {{data.first_floor_blocks[loop.index-1]}})
    {%endif%}{%endif%}{%endfor%}
    {% for block in data.second_floor_blocks %} {% if not loop.last %} {% if loop.index == 3 or loop.index == 6 %}
    (connected {{data.second_floor_blocks[loop.index-1]}} {{data.second_floor_blocks[loop.index+2]}})
    (connected {{data.second_floor_blocks[loop.index+2]}} {{data.second_floor_blocks[loop.index-1]}})
    {% elif loop.index == 7 or loop.index == 8 %}
    (connected {{data.second_floor_blocks[loop.index-1]}} {{data.second_floor_blocks[loop.index]}})
    (connected {{data.second_floor_blocks[loop.index]}} {{data.second_floor_blocks[loop.index-1]}})
    {% else %}
    (connected {{data.second_floor_blocks[loop.index-1]}} {{data.second_floor_blocks[loop.index]}})
    (connected {{data.second_floor_blocks[loop.index]}} {{data.second_floor_blocks[loop.index-1]}})
    (connected {{data.second_floor_blocks[loop.index-1]}} {{data.second_floor_blocks[loop.index+2]}})
    (connected {{data.second_floor_blocks[loop.index+2]}} {{data.second_floor_blocks[loop.index-1]}})
    {%endif%}{%endif%}{%endfor%}
    (above block2_5 block1_5)

    ;all blocks free except ones occupied by an entity
    {% for block in data.first_floor_blocks %}{% if loop.index != 1 %}
    (free {{data.first_floor_blocks[loop.index-1]}}){% endif %}{% endfor %}
    {% for block in data.second_floor_blocks %}
    (free {{data.second_floor_blocks[loop.index-1]}}){% endfor %}
)

(:goal (and
    (stored player key)
    (located player block1_1)
))
(:metric minimize (total-cost))

)
