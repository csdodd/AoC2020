package day5

class EmptySeats {

    private val maxNumRows = 128
    private val maxNumCols = 8
    private val allSeats = Array (maxNumRows) { Array<BoardingPass?>(maxNumCols) { null } }

    fun findLonelySeatId(passes: List<BoardingPass>): Int {
        passes.forEach {
            allSeats[it.row][it.col] = it
        }

        val missingSeatIds = arrayListOf<Int>()

        allSeats.forEachIndexed { row, rowData ->
            rowData.forEachIndexed { col, pass ->
                if (pass == null) {
                    missingSeatIds.add(row * 8 + col)
                }
            }
        }

        return missingSeatIds.first {
            !(missingSeatIds.contains(it - 1) || missingSeatIds.contains(it + 1))
        }
    }
}