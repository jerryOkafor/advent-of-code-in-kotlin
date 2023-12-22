/*
* 
* aoc-2023-in-kotlin
* 
* @Author : Jerry Okafor 
* @Date : 21/12/2023
*/

package adventOfCode.aoc2023.day05

import utils.chunkedByEmptyLine


fun List<String>.getSeeds() = this.first()
    .substringAfter(":")
    .trim()
    .split("\\s++".toRegex())
    .map { it.trim().toLong() }

fun List<String>.getMaps() = this.drop(2)
    .chunkedByEmptyLine()
    .map { section ->
        section.lines()
            .drop(1) //drop section title
            .associate {
                it.split("\\s++".toRegex())
                    .map { it.trim().toLong() }
                    .let { (destinationRangeStart, sourceRangeStart, rangeLength) ->
                        //98..100 -> 50..52 and 50..98 -> 52..100
                        sourceRangeStart..(sourceRangeStart + rangeLength) to destinationRangeStart..(destinationRangeStart + rangeLength)
                    }
            }
    }