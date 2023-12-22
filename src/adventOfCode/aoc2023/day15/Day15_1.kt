package adventOfCode.aoc2023.day15

import utils.solve

fun main() = solve { lines ->
    lines.first().split(",").map { text ->
        text.fold(0) { acc, char -> ((acc + char.code) * 17) % 256 }
    }.sum()
}