/*
* 
* aoc-2023-in-kotlin
* 
* @Author : Jerry Okafor 
* @Date : 21/12/2023
*/

package core.geoemtry

/**
 * Direction based on the Ordinal directions or the Intercardinal directions.
 * */
enum class Direction8 {
    NORTH, NORTH_EAST, EAST, SOUTH_EAST, SOUTH, SOUTH_WEST, WEST, NORTH_WEST;

    fun rotate(turn: Turn, times: Int): Direction8 {
        return (0 until (times % 4)).toList().fold(this) { acc, _ -> acc.rotate(turn) }
    }

    fun rotate(turn: Turn): Direction8 {
        return if (turn == Turn.RIGHT) when (this) {
            NORTH -> NORTH_EAST
            NORTH_EAST -> EAST
            EAST -> SOUTH_EAST
            SOUTH_EAST -> SOUTH
            SOUTH -> SOUTH_WEST
            SOUTH_WEST -> WEST
            WEST -> NORTH_WEST
            NORTH_WEST -> NORTH
        } else when (this) {
            NORTH -> NORTH_WEST
            NORTH_EAST -> NORTH
            EAST -> NORTH_EAST
            SOUTH_EAST -> EAST
            SOUTH -> SOUTH_EAST
            SOUTH_WEST -> SOUTH
            WEST -> SOUTH_WEST
            NORTH_WEST -> WEST
        }
    }

    fun reverse() = when (this) {
        NORTH -> SOUTH
        NORTH_EAST -> SOUTH_WEST
        EAST -> WEST
        SOUTH_EAST -> NORTH_WEST
        SOUTH -> NORTH
        SOUTH_WEST -> NORTH_EAST
        WEST -> EAST
        NORTH_WEST -> SOUTH_EAST
    }

    override fun toString() = when (this) {
        NORTH -> "↑"
        NORTH_EAST -> "↗"
        EAST -> "→"
        SOUTH_EAST -> "↘"
        SOUTH -> "↓"
        SOUTH_WEST -> "↙"
        WEST -> "←"
        NORTH_WEST -> "↖"
    }
}