package day11

class SeatGrid {

    private var grid = arrayListOf<ArrayList<SeatMarker>>()

    fun create(input: ArrayList<String>) {
        input.forEach { line ->
            val row = arrayListOf<SeatMarker>()
            line.forEach {
                row.add(SeatMarker(it))
            }
            grid.add(row)
        }
    }

    fun toggleUntilStable(): Int {
        val newGrid = deepCopyGrid(grid)
        var numOccupiedSeats = -1

        while (true) {
            grid.forEachIndexed { rowIndex, row ->
                row.forEachIndexed { colIndex, seatMarker ->
                    val occupiedAdjacentSeats = getNumberOfAdjacentSeats(rowIndex, colIndex)

                    when (seatMarker.type) {
                        Type.EmptySeat -> {
                            if (occupiedAdjacentSeats == 0) newGrid[rowIndex][colIndex] = SeatMarker('#')
                        }

                        Type.OccupiedSeat -> {
                            if (occupiedAdjacentSeats >= 4) {
                                newGrid[rowIndex][colIndex] = SeatMarker('L')
                            }
                        }
                        else -> {}
                    }
                }
            }

            val newNumberOfOccupiedSeats = countOfOccupiedSeats(newGrid)
            if (newNumberOfOccupiedSeats == numOccupiedSeats) return newNumberOfOccupiedSeats

            numOccupiedSeats = newNumberOfOccupiedSeats
            grid = deepCopyGrid(newGrid)
        }
    }

    fun toggleUntilStablePart2(): Int {
        val newGrid = deepCopyGrid(grid)
        var numOccupiedSeats = -1

        while (true) {
            grid.forEachIndexed { rowIndex, row ->
                row.forEachIndexed { colIndex, seatMarker ->
                    val occupiedAdjacentSeats = getNumberOfAdjacentSeatsPart2(rowIndex, colIndex)

                    when (seatMarker.type) {
                        Type.EmptySeat -> {
                            if (occupiedAdjacentSeats == 0) newGrid[rowIndex][colIndex] = SeatMarker('#')
                        }

                        Type.OccupiedSeat -> {
                            if (occupiedAdjacentSeats >= 5) {
                                newGrid[rowIndex][colIndex] = SeatMarker('L')
                            }
                        }
                        else -> {}
                    }
                }
            }

            val newNumberOfOccupiedSeats = countOfOccupiedSeats(newGrid)
            if (newNumberOfOccupiedSeats == numOccupiedSeats) return newNumberOfOccupiedSeats

            numOccupiedSeats = newNumberOfOccupiedSeats
            grid = deepCopyGrid(newGrid)
        }
    }

    private fun countOfOccupiedSeats(g: ArrayList<ArrayList<SeatMarker>>): Int {
        return g.sumBy { row ->
            row.count { it.type == Type.OccupiedSeat }
        }
    }

    private fun getNumberOfAdjacentSeats(rowIndex: Int, colIndex: Int): Int {
        var count = 0
        // The three seats above
        if (rowIndex > 0) {
            if (grid[rowIndex - 1][colIndex].type == Type.OccupiedSeat) count++
            if (colIndex > 0 && grid[rowIndex - 1][colIndex - 1].type == Type.OccupiedSeat) count++
            if (colIndex < grid.first().size - 1 && grid[rowIndex - 1][colIndex + 1].type == Type.OccupiedSeat) count++
        }

        // The three seats below
        if (rowIndex < grid.size - 1) {
            if (grid[rowIndex + 1][colIndex].type == Type.OccupiedSeat) count++
            if (colIndex > 0 && grid[rowIndex + 1][colIndex - 1].type == Type.OccupiedSeat) count++
            if (colIndex < grid.first().size - 1 && grid[rowIndex + 1][colIndex + 1].type == Type.OccupiedSeat) count++
        }

        // The two on the same row
        if (colIndex > 0 && grid[rowIndex][colIndex - 1].type == Type.OccupiedSeat) count++
        if (colIndex < grid.first().size - 1 && grid[rowIndex][colIndex + 1].type == Type.OccupiedSeat) count++

        return count
    }

    private fun getNumberOfAdjacentSeatsPart2(rowIndex: Int, colIndex: Int): Int {
        var count = 0
        var ri = -1
        var ci = -1
        // Seat above
        ri = rowIndex - 1
        while (ri >= 0 && grid[ri][colIndex].type == Type.Floor) {
            ri--
        }
        if (ri != -1 && grid[ri][colIndex].type == Type.OccupiedSeat) count++

        // Seat below
        ri = rowIndex + 1
        while (ri <= grid.size -1 && grid[ri][colIndex].type == Type.Floor) {
            ri++
        }
        if (ri != grid.size && grid[ri][colIndex].type == Type.OccupiedSeat) count++

        // Seat Left
        ci = colIndex - 1
        while (ci >= 0 && grid[rowIndex][ci].type == Type.Floor) {
            ci--
        }
        if (ci != -1 && grid[rowIndex][ci].type == Type.OccupiedSeat) count++

        // Seat Right
        ci = colIndex + 1
        while (ci <= grid.first().size -1 && grid[rowIndex][ci].type == Type.Floor) {
            ci++
        }
        if (ci != grid.first().size && grid[rowIndex][ci].type == Type.OccupiedSeat) count++


        // Seat up,left
        ri = rowIndex - 1
        ci = colIndex - 1
        while (ri >= 0 && ci >= 0 && grid[ri][ci].type == Type.Floor) {
            ri--
            ci--
        }
        if (ri != -1 && ci != -1 && grid[ri][ci].type == Type.OccupiedSeat) count++

        // Seat up,right
        ri = rowIndex - 1
        ci = colIndex + 1
        while (ri >= 0 && ci<= grid.first().size -1 && grid[ri][ci].type == Type.Floor) {
            ri--
            ci++
        }
        if (ri != -1 && ci != grid.first().size && grid[ri][ci].type == Type.OccupiedSeat) count++

        // Seat down,left
        ri = rowIndex + 1
        ci = colIndex - 1
        while (ri <= grid.size -1 && ci >= 0 && grid[ri][ci].type == Type.Floor) {
            ri++
            ci--
        }
        if (ri != grid.size && ci != -1 && grid[ri][ci].type == Type.OccupiedSeat) count++

        // Seat down,right
        ri = rowIndex + 1
        ci = colIndex + 1
        while (ri <= grid.size -1 && ci != grid.first().size && grid[ri][ci].type == Type.Floor) {
            ri++
            ci++
        }
        if (ri != grid.size && ci != grid.first().size && grid[ri][ci].type == Type.OccupiedSeat) count++

        return count
    }

    private fun deepCopyGrid(from: ArrayList<ArrayList<SeatMarker>>): ArrayList<ArrayList<SeatMarker>> {
        val copy =  arrayListOf<ArrayList<SeatMarker>>()
        from.forEachIndexed { i, list ->
            val row = arrayListOf<SeatMarker>()
            list.forEachIndexed { j, seatMarker ->
                row.add(seatMarker.copy())
            }
            copy.add(row)
        }
        return copy
    }

    private fun prettyPrint(g: ArrayList<ArrayList<SeatMarker>>) {
        g.forEach {row ->
            row.forEach { print(it.type) }
            print("\n")
        }
    }
}