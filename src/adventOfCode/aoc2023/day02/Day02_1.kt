package adventOfCode.aoc2023.day02

import utils.solve

fun processInput(input: List<String>): List<Map<Int, List<Map<String, Int>>>> {
    return input.map { line ->
        val game = "^Game\\s(\\d+)".toRegex().find(line.substringBefore(":"))?.groupValues!!.last().toInt()

        val gameSets = line.substringAfter(":")
            .split(";")
            .fold(mutableListOf<Map<String, Int>>()) { acc, item ->
                //find and add all the red, green and blue balls for eventy given set
                val redsInSet = "(\\d+)\\sred".toRegex().find(item)?.groupValues?.lastOrNull()?.toIntOrNull()
                    ?: 0
                val greensInSet = "(\\d+)\\sgreen".toRegex().find(item)?.groupValues?.lastOrNull()?.toIntOrNull()
                    ?: 0
                val bluesInSet = "(\\d+)\\sblue".toRegex().find(item)?.groupValues?.lastOrNull()?.toIntOrNull()
                    ?: 0
                acc.add(mapOf("red" to redsInSet, "green" to greensInSet, "blue" to bluesInSet))
                acc
            }

        mapOf(game to gameSets.toList())
    }
}

fun main() = solve { lines ->
    val config = buildMap<String, Int> {
        put("red", 12)
        put("green", 13)
        put("blue", 14)
    }
    processInput(lines)
        .filterNot { games ->
            games.any { sets ->
                sets.value.any { set ->
                    set["red"]!! > config["red"]!! || set["green"]!! > config["green"]!! || set["blue"]!! > config["blue"]!!
                }
            }
        }.flatMap { it.keys }.sum()
}