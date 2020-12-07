package day5

data class BoardingPass (
    var row: Int = 0,
    var col: Int = 0,
    var seatId: Int = 0
) {

    private val maxNumRows = 128
    private val maxNumCols = 8

    constructor(rawInput: String) : this() {
        val rowDescription = rawInput.subSequence(0 , 7)
        val colDescription = rawInput.subSequence(7 , 10)

        row = calculatePosition(rowDescription, maxNumRows, 'F')
        col = calculatePosition(colDescription, maxNumCols, 'L')
        seatId = (row * 8) + col
    }

    private fun calculatePosition(description: CharSequence, maxCount: Int, lowerRangeCharacter: Char): Int {
        var pos = Pair(0, maxCount - 1)
        var delta = maxCount / 2

        description.forEach {
            pos = if (it == lowerRangeCharacter) {
                Pair(pos.first, pos.second - delta)
            } else {
                Pair(pos.first + delta, pos.second)
            }
            delta /= 2
        }
        return pos.first
    }
}