package day11

import loadFileToStringArray
import printAsHeader

class Day11 {

    fun run() {
        "Day11".printAsHeader()
        test()
        realData()
    }

    private fun test() {
        val input = loadFileToStringArray("/day11/example.txt")
        testPart1(input)
        testPart2(input)
    }

    private fun testPart1(input: ArrayList<String>) {
        val seatGrid = SeatGrid()
        seatGrid.create(input)
        val numOccupiedSeats = seatGrid.toggleUntilStable()
        println("Number occupied seats after toggling in test data is $numOccupiedSeats")
    }

    private fun testPart2(input: ArrayList<String>) {
        val seatGrid = SeatGrid()
        seatGrid.create(input)
        val numOccupiedSeats = seatGrid.toggleUntilStablePart2()
        println("Number occupied seats after toggling in test data part 2 is $numOccupiedSeats")
    }

    private fun realData() {
        val input = loadFileToStringArray("/day11/input.txt")
        realDataPart1(input)
        realDataPart2(input)
    }

    private fun realDataPart1(input: ArrayList<String>) {
        val seatGrid = SeatGrid()
        seatGrid.create(input)
        val numOccupiedSeats = seatGrid.toggleUntilStable()
        println("Number occupied seats after toggling in real data is $numOccupiedSeats")
    }

    private fun realDataPart2(input: ArrayList<String>) {
        val seatGrid = SeatGrid()
        seatGrid.create(input)
        val numOccupiedSeats = seatGrid.toggleUntilStablePart2()
        println("Number occupied seats after toggling in real data part 2 is $numOccupiedSeats")
    }

}