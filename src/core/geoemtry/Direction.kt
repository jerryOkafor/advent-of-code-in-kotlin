/*
* 
* aoc-2023-in-kotlin
* 
* @Author : Jerry Okafor 
* @Date : 21/12/2023
*/

package core.geoemtry

enum class Turn {
    RIGHT, LEFT;

    override fun toString() = when (this) {
        RIGHT -> "↻"
        LEFT -> "↺"
    }
}


/**
 * Direction model based on the 4 Cardinal points. All points are at 90 degree intervals in the clockwise direction.
 * */
enum class Direction {
    NORTH, EAST, SOUTH, WEST;

    fun rotate(turn: Turn, times: Int): Direction {
        return (0 until (times % 4)).toList().fold(this) { acc, _ -> acc.rotate(turn) }
    }

    fun rotate(turn: Turn): Direction {
        return if (turn == Turn.RIGHT) when (this) {
            NORTH -> EAST
            EAST -> SOUTH
            SOUTH -> WEST
            WEST -> NORTH
        } else when (this) {
            NORTH -> WEST
            EAST -> NORTH
            SOUTH -> EAST
            WEST -> SOUTH
        }
    }

    fun reverse() = when (this) {
        NORTH -> SOUTH
        SOUTH -> NORTH
        WEST -> EAST
        EAST -> WEST
    }

    override fun toString() = when (this) {
        NORTH -> "↑"
        EAST -> "→"
        SOUTH -> "↓"
        WEST -> "←"
    }
}
