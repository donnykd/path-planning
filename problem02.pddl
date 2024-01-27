;;!pre-parsing:{type: "jinja2", data: "case2.json"}

;;;Problem initially made to test the traversal of 2 players between blocks,
;;;became more complex with the addition of extra actions and predicate.
;;;Problem now checks the traversal of 2 players with a wall inbetween them,
;;;a passageway and a chair blocking the way.
(define (problem paths_extended) (:domain explorer)
(:objects
    {% for explorer in data.explorers %}{% if not loop.last %}{{ explorer }}{% else %}{{ explorer }} - explorer {% endif %}
    {% endfor %}
    {% for block in data.blocks %}{% if not loop.last %}{{ block }}{% else %}{{ block }} - location {% endif %}
    {% endfor %}
    {{data.box}} - box
)

(:init
    (= (total-cost) 0)

    ;definining locations
    (located {{data.explorers[0]}} block1)
    (located {{data.explorers[1]}} block16)
    (located {{data.box}} block11)

    ;defining connectivity between blocks for player to go through
    {% for block in data.blocks %} {% if not loop.last %} {% if loop.index == 4 or loop.index == 8 or loop.index == 12 %}
    (connected {{data.blocks[loop.index-1]}} {{data.blocks[loop.index+3]}})
    (connected {{data.blocks[loop.index+3]}} {{data.blocks[loop.index-1]}})
    {% elif loop.index == 13 or loop.index == 14 or loop.index == 15 %}
    (connected {{data.blocks[loop.index-1]}} {{data.blocks[loop.index]}})
    (connected {{data.blocks[loop.index]}} {{data.blocks[loop.index-1]}})
    {% else %}
    (connected {{data.blocks[loop.index-1]}} {{data.blocks[loop.index]}})
    (connected {{data.blocks[loop.index]}} {{data.blocks[loop.index-1]}})
    (connected {{data.blocks[loop.index-1]}} {{data.blocks[loop.index+3]}})
    (connected {{data.blocks[loop.index+3]}} {{data.blocks[loop.index-1]}})
    {%endif%}{%endif%}{%endfor%}
    (blocked block5 block9)
    (blocked block6 block10)
    (blocked block8 block12)

    ;all blocks free except ones occupied by an entity
    {% for block in data.blocks %}{% if loop.index != 1 and loop.index != 16 and loop.index != 11%}
    (free {{data.blocks[loop.index-1]}}){% endif %}{% endfor %}
)

(:goal (and
    (located player1 block16)
    (located player2 block1)
))
(:metric minimize (total-cost))
)