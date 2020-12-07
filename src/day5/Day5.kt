package day5

import loadFileToStringArray
import printAsHeader

class Day5 {

    fun run() {
        "Day5".printAsHeader()
        println("Test result is: ${test()}")
        realData()
    }

    private fun realData() {
        val input = loadFileToStringArray("/day5/input.txt")
        val passes = generateBoardingPasses(input)
        val lonelySeat = EmptySeats().findLonelySeatId(passes)

        println("Highest seat ID from input is: ${passes.maxOf { it.seatId }}")
        println("Lonely empty seat is: $lonelySeat")
    }

    private fun test(): Int {
        val input = loadFileToStringArray("/day5/example.txt")
        val passes = generateBoardingPasses(input)
        return passes.maxOf { it.seatId }
    }

    private fun generateBoardingPasses(input: ArrayList<String>): List<BoardingPass> {
        return input.map { BoardingPass(it) }
    }
}