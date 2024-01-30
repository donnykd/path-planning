;;!pre-parsing:{type: "jinja2", data: "case5.json"}
(define (problem combination) (:domain explorer)
(:objects 
    {% for explorer in data.explorers %}{% if not loop.last %}{{ explorer }}{% else %}{{ explorer }} - explorer {% endif %}
    {% endfor %}
    {% for block in data.ed_first_floor_blocks %}{{ block }} 
    {% endfor %}
    {% for block in data.ed_second_floor_blocks %}{% if not loop.last %}{{ block }}{% else %}{{ block }} - location {% endif %}
    {% endfor %}
    ladder - ladder
    door_key chest_key - key
    trophy_chest - chest
    obstacle1 obstacle2 - box
    trophy - trophy
)

(:init
    (= (total-cost) 0)
    ;definining locations
    (located player block1_1)
    (located npc block2_2)
    (on door_key block1_9)
    (on chest_key block2_10)
    (located obstacle1 block1_17)
    (located obstacle2 block2_15)
    (on ladder block1_18)
    (located trophy_chest block2_1)
    (restricted npc)
    ;defining connectivity between blocks for player to go through
    ;first floor connectivity
    {% for block in data.ed_first_floor_blocks %} {% if not loop.last %} {% if loop.index == 3 or loop.index == 6 or loop.index == 9 or loop.index == 12 or loop.index == 15 %}
    (connected {{data.ed_first_floor_blocks[loop.index-1]}} {{data.ed_first_floor_blocks[loop.index+2]}})
    (connected {{data.ed_first_floor_blocks[loop.index+2]}} {{data.ed_first_floor_blocks[loop.index-1]}})
    {% elif loop.index == 16 or loop.index == 17 %}
    (connected {{data.ed_first_floor_blocks[loop.index-1]}} {{data.ed_first_floor_blocks[loop.index]}})
    (connected {{data.ed_first_floor_blocks[loop.index]}} {{data.ed_first_floor_blocks[loop.index-1]}})
    {% else %}
    (connected {{data.ed_first_floor_blocks[loop.index-1]}} {{data.ed_first_floor_blocks[loop.index]}})
    (connected {{data.ed_first_floor_blocks[loop.index]}} {{data.ed_first_floor_blocks[loop.index-1]}})
    (connected {{data.ed_first_floor_blocks[loop.index-1]}} {{data.ed_first_floor_blocks[loop.index+2]}})
    (connected {{data.ed_first_floor_blocks[loop.index+2]}} {{data.ed_first_floor_blocks[loop.index-1]}})
    {%endif%}{%endif%}{%endfor%}
    ;second floor connectivity
    {% for block in data.ed_second_floor_blocks %} {% if not loop.last %} {% if loop.index == 3 or loop.index == 6 or loop.index == 9 or loop.index == 12 or loop.index == 15 %}
    (connected {{data.ed_second_floor_blocks[loop.index-1]}} {{data.ed_second_floor_blocks[loop.index+2]}})
    (connected {{data.ed_second_floor_blocks[loop.index+2]}} {{data.ed_second_floor_blocks[loop.index-1]}})
    {% elif loop.index == 16 or loop.index == 17 %}
    (connected {{data.ed_second_floor_blocks[loop.index-1]}} {{data.ed_second_floor_blocks[loop.index]}})
    (connected {{data.ed_second_floor_blocks[loop.index]}} {{data.ed_second_floor_blocks[loop.index-1]}})
    {% else %}
    (connected {{data.ed_second_floor_blocks[loop.index-1]}} {{data.ed_second_floor_blocks[loop.index]}})
    (connected {{data.ed_second_floor_blocks[loop.index]}} {{data.ed_second_floor_blocks[loop.index-1]}})
    (connected {{data.ed_second_floor_blocks[loop.index-1]}} {{data.ed_second_floor_blocks[loop.index+2]}})
    (connected {{data.ed_second_floor_blocks[loop.index+2]}} {{data.ed_second_floor_blocks[loop.index-1]}})
    {%endif%}{%endif%}{%endfor%}
    ;first floor blockage
    (blocked block1_1 block1_2)
    (blocked block1_4 block1_5)
    (blocked block1_7 block1_8)
    (blocked block1_13 block1_14)
    (blocked block1_13 block1_16)
    (blocked block1_14 block1_17)
    (blocked block1_15 block1_18)
    ;second floor blockage
    (blocked block2_18 block2_17)
    (blocked block2_15 block2_14)
    (blocked block2_12 block2_11)
    (blocked block2_8 block2_9)
    (blocked block2_5 block2_6)
    (blocked block2_2 block2_3)
    (blocked block2_2 block2_5)
    (blocked block2_1 block2_4)
    (blocked block2_10 block2_13)
    (blocked block2_11 block2_14)
    ;all blocks free except ones occupied by an entity
    {% for block in data.ed_first_floor_blocks %}{% if loop.index != 1 and loop.index != 17 %}
    (free {{data.ed_first_floor_blocks[loop.index-1]}}){% endif %}{% endfor %}
    {% for block in data.ed_second_floor_blocks %}{% if loop.index != 1 and loop.index != 2 and loop.index != 15 %}
    (free {{data.ed_second_floor_blocks[loop.index-1]}}){% endif %}{% endfor %}
    ;doors and floors
    (door block1_10 block1_11)
    (door block1_13 block1_16)
    (door block2_2 block2_3)
    (door block2_8 block2_9)
    (above block2_18 block1_18)
    ;item properties
    (type_chest chest_key)
    (type_door door_key)
    (stored trophy_chest trophy)
    (locked trophy_chest)
)

(:goal (and
    (on trophy block1_6)
))


(:metric minimize (total-cost))
)
