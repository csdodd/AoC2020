package day11

data class SeatMarker(private val input: Char) {

    var type = convertToType(input)

    private fun convertToType(input: Char): Type {
        return when (input) {
            'L' -> Type.EmptySeat
            '.' -> Type.Floor
            else -> Type.OccupiedSeat
        }
    }

    fun copy() : SeatMarker {
        return SeatMarker(input)
    }
}

enum class Type {
    Floor,
    EmptySeat,
    OccupiedSeat;

    override fun toString(): String {
        return when (this) {
            Floor -> "."
            EmptySeat -> "L"
            OccupiedSeat -> "#"
        }
    }
}