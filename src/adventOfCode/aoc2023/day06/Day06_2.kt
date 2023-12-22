package adventOfCode.aoc2023.day06

import utils.solve

fun main() = solve { lines ->
    val cleanData = lines.map { it.substringAfter(":") }
        .map { it.trim() }
        .map { it.replace("\\s+".toRegex(), "") }
        .map { it.toLong() }

    val (time, distance) = cleanData

    (0 until time).count { i -> (time - i) * i > distance }
}