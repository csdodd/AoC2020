package day17

import loadFileToStringArray
import printAsHeader

class Day17 {

    fun run() {
        "Day17".printAsHeader()
        test()
        realData()
    }

    private fun test() {
        val input = loadFileToStringArray("/day17/example.txt")
        println("Test result for part 1 is ${part1(input)}")
        println("Test result for part 2 is ${part2(input)}")
    }

    private fun realData() {
        val input = loadFileToStringArray("/day17/input.txt")
        println("Real result for part 1 is ${part1(input)}")
        println("Real result for part 2 is ${part2(input)}")
    }

    private fun part1(input: ArrayList<String>): Int {
        val grid = CubeGrid()
        grid.create(input)
        return grid.runCycle()
    }

    private fun part2(input: ArrayList<String>): Int {
        val grid = CubeGrid()
        grid.create(input)
        return grid.run4DCycle()
    }

}