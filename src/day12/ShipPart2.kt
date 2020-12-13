package day12

import java.lang.Math.abs

class ShipPart2 {

    private var waypoint =  Pair(1, 10)
    private var manhattanDistance = Pair(0, 0)

    fun move(instructions: ArrayList<String>): Int {
        instructions.map { Instruction(it) }
                .forEach { processAction(it) }
        return abs(manhattanDistance.first) + abs(manhattanDistance.second)
    }

    private fun processAction(instruction: Instruction) {
        println("Processing $instruction")
        when {
            instruction.isRotational() -> handleRotation(instruction)
            instruction.isMoving() -> handleMoving(instruction)
            else -> moveForward(instruction)
        }
    }

    private fun handleRotation(instruction: Instruction) {
        val delta = instruction.amount / 90

        if (instruction.action == Action.R) {
            for (i in 1..delta) {
                waypoint = waypoint.copy(first = waypoint.second * -1, second = waypoint.first)
            }
        } else {
            for (i in 1..delta) {
                waypoint = waypoint.copy(first = waypoint.second, second = waypoint.first * -1)
            }
        }

        println("Current WP is now $waypoint")
    }

    private fun handleMoving(instruction: Instruction) {
        waypoint = when (instruction.action) {
            Action.N -> waypoint.copy(first = waypoint.first + instruction.amount)
            Action.S -> waypoint.copy(first = waypoint.first - instruction.amount)
            Action.E -> waypoint.copy(second = waypoint.second + instruction.amount)
            Action.W -> waypoint.copy(second = waypoint.second - instruction.amount)
            else -> waypoint
        }
        println("Current WP is now $waypoint")
    }

    private fun moveForward(instruction: Instruction) {
        val delta = waypoint.copy(first = waypoint.first * instruction.amount, second = waypoint.second * instruction.amount)
        manhattanDistance = manhattanDistance.copy(first = manhattanDistance.first + delta.first, second = manhattanDistance.second + delta.second)
        println("Current MD is now $manhattanDistance")
    }
}