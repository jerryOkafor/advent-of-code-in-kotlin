package adventOfCode.aoc2023.day05

import utils.solve
import utils.splitOnEmpty

fun main() = solve { lines ->

    fun getOutputRanges(inputSeedRange: LongRange, map: Map<LongRange, LongRange>): List<LongRange> {
        val mappedRanges = mutableListOf<LongRange>()
        val outputRange = map.entries.mapNotNull { (source, destination) ->
            val start = maxOf(source.first, inputSeedRange.first)
            val end = minOf(source.last, inputSeedRange.last)

            if (start <= end) {
                mappedRanges += start..end
                (destination.first - source.first).let {
                    (start + it)..(end + it)
                }
            } else null
        }

        val cutRange = listOf(inputSeedRange.first) + mappedRanges.sortedBy { it.first }
            .flatMap { listOf(it.first, it.last) } + listOf(inputSeedRange.last)

        val unMappedRanges = cutRange.chunked(2).mapNotNull { (first, second) ->
            if (second > first) {
                if (second == cutRange.last())
                    first..second
                else {
                    first..<second
                }
            } else {
                null
            }
        }
        return outputRange + unMappedRanges
    }

    val seeds: List<LongRange> = lines.splitOnEmpty()
        .getSeeds()
        .chunked(2)
        .map { it.first()..<it.first() + it.last() }
        .onEach(::println)
    val maps: List<Map<LongRange, LongRange>> = lines.getMaps()

    seeds.flatMap { seedsRange ->
        maps.fold(listOf(seedsRange)) { acc, map ->
            acc.flatMap { runningSeedsRange ->
                getOutputRanges(runningSeedsRange, map)
            }
        }
    }.minOf { it.first }
}