package adventOfCode.aoc2023.day05

import utils.solve
import utils.splitOnEmpty

fun main() = solve { lines ->
    val seeds: List<Long> = lines.splitOnEmpty().getSeeds()
    val maps: List<Map<LongRange, LongRange>> = lines.getMaps()

    seeds.minOf { seed ->
        //using the given seed, we move from left to right and return the
        //value of the source (seed -> soil -> fertilizer -> water -> light -> temp -> humidity)
        //or the original value if not match until we end up with location value
        maps.fold(seed) { acc, map ->
            map.entries
                .firstOrNull { acc in it.key }
                ?.let { (source, destination) ->
                    destination.first + (acc - source.first)
                } ?: acc
        }
    }
}