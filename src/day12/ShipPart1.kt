package day12

import java.lang.Math.abs

class ShipPart1 {

    private var direction = Direction.E
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
        val currentPos = direction.ordinal

        if (instruction.action == Action.R) {
            direction = Direction.values()[(currentPos + delta) % Direction.values().size]
        } else {
            direction = Direction.values()[((currentPos - delta) + Direction.values().size) % Direction.values().size]
        }

        println("Current direction is now $direction")
    }

    private fun handleMoving(instruction: Instruction) {
        manhattanDistance = when (instruction.action) {
            Action.N -> manhattanDistance.copy(first = manhattanDistance.first + instruction.amount)
            Action.S -> manhattanDistance.copy(first = manhattanDistance.first - instruction.amount)
            Action.E -> manhattanDistance.copy(second = manhattanDistance.second + instruction.amount)
            Action.W -> manhattanDistance.copy(second = manhattanDistance.second - instruction.amount)
            else -> manhattanDistance
        }
        println("Current MD is now $manhattanDistance")
    }

    private fun moveForward(instruction: Instruction) {
        val rawInput = "${direction}${instruction.amount}"
        val constructedInstruction = Instruction(rawInput)
        handleMoving(constructedInstruction)
    }
}

enum class Direction {
    N {
        override fun toString() = "N"
    },
    E {
        override fun toString() = "E"
    },
    S {
        override fun toString() = "S"
    },
    W {
        override fun toString() = "W"
    };
}