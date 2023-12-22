/*
* 
* aoc-2023-in-kotlin
* 
* @Author : Jerry Okafor 
* @Date : 21/12/2023
*/

package adventOfCode.aoc2023.day07


fun List<Int>.toCardType(): CardType {
    return when {
        5 in this -> CardType.FiveOfAKind()
        4 in this -> CardType.FiveOfAKind()
        3 in this && 2 in this -> CardType.FullHouse()
        3 in this -> CardType.ThreeOfAKind()
        2 in this && 2 in (this - 2) -> CardType.TwoPair()
        2 in this -> CardType.OnePair()
        else -> CardType.HighCard()
    }

}
sealed interface CardType {
    val strength: Int

    data class FiveOfAKind(override val strength: Int = 7) : CardType
    data class FourOfAKind(override val strength: Int = 6) : CardType
    data class FullHouse(override val strength: Int = 5) : CardType
    data class ThreeOfAKind(override val strength: Int = 4) : CardType
    data class TwoPair(override val strength: Int = 3) : CardType
    data class OnePair(override val strength: Int = 2) : CardType
    data class HighCard(override val strength: Int = 1) : CardType
}

data class Hand(
    val cardsStr: String,
    val cards: List<Int>,
    val cardsGroup: List<Int>,
    val bid: Int,
    val cardType: CardType
) : Comparable<Hand> {
    override fun compareTo(other: Hand): Int {
        return cardType.strength.compareTo(other.cardType.strength)
    }

}