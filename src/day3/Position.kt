package day3

data class Position (
        var row: Int = 0,
        var col: Int = 0,
        var wrapSize: Int = 0
) {

    fun addDelta(delta: Pair<Int, Int>) {
        col += delta.first
        row += delta.second

        if (col > wrapSize) {
            col -= wrapSize + 1
        }
    }

    fun reset() {
        row = 0
        col = 0
    }
}