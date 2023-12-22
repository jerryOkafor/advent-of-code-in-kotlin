package adventOfCode.aoc2023.day07

import utils.solve

fun main() = solve { lines ->
    val cardsMap = mapOf(
        'A' to 13,
        'K' to 12,
        'Q' to 11,
        'J' to 10,
        'T' to 9,
        '9' to 8,
        '8' to 7,
        '7' to 6,
        '6' to 5,
        '5' to 4,
        '4' to 3,
        '3' to 2,
        '2' to 1
    )

    lines.map { it.split(" ") }.map { (cardsStr, bid) ->
        val cards = cardsStr.map { card -> cardsMap[card]!! }
        val cardsGroup = cards.groupBy { it }.map { it.value.size }.sortedByDescending { it }

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
            { it.cardsGroup[1] },
            { it.cards[0] },
            { it.cards[1] },
            { it.cards[2] },
            { it.cards[3] },
            { it.cards[4] },
        )
    ).onEach {
        print("${it.cardsStr} -> ${it.cardType}\n")
    }
        .mapIndexed { index, hand -> (index + 1) * hand.bid }
        .sum()
}