package day12

class Instruction (val input: String) {
    val action: Action
    val amount: Int

    init {
        action = createAction(input.substring(0, 1))
        amount = input.substring(1).toInt()
    }

    private fun createAction(rawAction: String): Action {
        return when (rawAction) {
            "N" -> Action.N
            "S" -> Action.S
            "E" -> Action.E
            "W" -> Action.W
            "L" -> Action.L
            "R" -> Action.R
            else -> Action.F
        }
    }

    fun isRotational() = action == Action.L || action == Action.R
    fun isMoving() = action == Action.N || action == Action.S || action == Action.E || action == Action.W
    fun isForward() = action == Action.F

    override fun toString(): String {
        return input
    }
}

enum class Action {
    N,
    S,
    E,
    W,
    L,
    R,
    F
}