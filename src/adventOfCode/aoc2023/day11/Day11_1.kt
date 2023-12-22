package adventOfCode.aoc2023.day11


import core.geoemtry.PointL
import utils.solve

fun main() = solve { lines ->
    val rowsToExpand = lines.indices.filter { y -> '#' !in lines[y] }
    val colsToExpand = lines[0].indices.filter { x -> lines.none { it[x] == '#' } }

    fun galaxies(expansion: Long) = lines.flatMapIndexed { row, line ->
        "#".toRegex().findAll(line, 0).map { x ->
            PointL(
                x.range.first.toLong() + colsToExpand.count { c -> x.range.first > c } * (expansion - 1),
                row.toLong() + rowsToExpand.count { c -> row > c } * (expansion - 1)
            )
        }
    }

    val expandedGalaxies = galaxies(2)

    expandedGalaxies
        .flatMap { a ->
            expandedGalaxies.map { b -> a to b }
        }
        .sumOf { (a, b) -> Math.abs(b.x - a.x) + Math.abs(b.y - a.y) } / 2
}