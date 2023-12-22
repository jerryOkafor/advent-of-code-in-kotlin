/*
* 
* aoc-2023-in-kotlin
* 
* @Author : Jerry Okafor 
* @Date : 21/12/2023
*/

package adventOfCode.aoc2023.day16

import core.geoemtry.Point

fun <T> List<String>.toGrid(transform: (Char) -> T): Map<Point, T> {
    return mutableMapOf<Point, T>().also {
        forEachIndexed { y, row ->
            row.forEachIndexed { x, char ->
                it[Point(x, y)] = transform(char)
            }
        }
    }
}

fun List<String>.toGrid(): Map<Point, Char> {
    return toGrid { it }
}