package adventOfCode.aoc2023.day01

import utils.solve

fun main() = solve { lines ->
    lines.sumOf { line ->
        val first = line.first(Char::isDigit)
        val last = line.last(Char::isDigit)
        "$first$last".toInt()
    }
}