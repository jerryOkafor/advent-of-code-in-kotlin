/*
* 
* aoc-2023-in-kotlin
* 
* @Author : Jerry Okafor 
* @Date : 21/12/2023
*/

package adventOfCode.aoc2023.day12

fun findPossibleArrangements(arrangement: String, pattern: List<Int>): Long {
    buildMap {
        fun possibleArrangements(arrangement: String, pattern: List<Int>): Long =
            getOrPut(arrangement to pattern) {
                if (pattern.isEmpty()) return@getOrPut if (arrangement.none { it == '#' }) 1 else 0
                val firstGroupSize = pattern.first()
                val match = Regex("([#?]+)").find(arrangement) ?: return@getOrPut 0
                val firstMatch = match.groupValues[0]
                if (firstMatch.length == firstGroupSize && firstMatch.all { it == '#' }) {
                    val newArrangement = arrangement.replaceFirst(firstMatch, "").drop(1).dropWhile { it == '.' }
                    return@getOrPut possibleArrangements(newArrangement, pattern.drop(1))
                } else if (firstMatch.length < firstGroupSize && firstMatch.contains("#")) {
                    return@getOrPut 0
                } else if (firstMatch.length > firstGroupSize && firstMatch.indexOf("#".repeat(firstGroupSize + 1)) == 0) {
                    return@getOrPut 0
                }
                return@getOrPut possibleArrangements(
                    arrangement.replaceFirst("?", "."), pattern
                ) + possibleArrangements(
                    arrangement.replaceFirst("?", "#"), pattern
                )
            }
        return possibleArrangements(arrangement, pattern)
    }
}