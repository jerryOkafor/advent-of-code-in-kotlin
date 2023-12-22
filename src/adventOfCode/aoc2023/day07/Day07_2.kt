package adventOfCode.aoc2023.day07

import utils.solve

fun main() = solve { lines ->
    val cardsMap = mapOf(
        'A' to 13,
        'K' to 12,
        'Q' to 11,
        'T' to 10,
        '9' to 9,
        '8' to 8,
        '7' to 7,
        '6' to 6,
        '5' to 5,
        '4' to 4,
        '3' to 3,
        '2' to 2,
        'J' to 1
    )

    lines.map { it.split(" ") }.map { (cardsStr, bid) ->
        val cards = cardsStr.map { card -> cardsMap.get(card)!! }

        //Find the highest value for all possible placements of the the joker, card J
        //Use that as the group
        val cardsGroup = (2..13)
            .map { joker ->
                cards.map { if (it == 1) joker else it }
                    .groupBy { it }.map { it.value.size }
                    .sortedByDescending { it }
            }
            .sortedWith(compareBy({ it[0] }, { it.getOrNull(1) }))
            .onEach(::println)
            .last()

        Hand(
            cardsStr = cardsStr,
            cards = cards,
            cardsGroup = cardsGroup,
            bid = bid.toInt(),
            cardType = cardsGroup.toCardType(),
        )

    }.sortedWith(
        compareBy(
            { it.cardsGroup[0] },
            { it.cardsGroup.getOrNull(1) },
            { it.cards[0] },
            { it.cards[1] },
            { it.cards[2] },
            { it.cards[3] },
            { it.cards[4] },
        )
    ).onEach {
        print("${it.cardsStr} -> ${it.cardType}\n")
    }.mapIndexed { index, hand -> (index + 1) * hand.bid }
        .sum()
}