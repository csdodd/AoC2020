package day18

import loadFileToStringArray
import printAsHeader
import java.util.ArrayList

class Day18 {

    private val solver = EquationEvaluator()

    fun run() {
        "Day18".printAsHeader()
        test()
        realData()
    }

    private fun test() {
        val input = loadFileToStringArray("/day18/example.txt")
        println("Test result for part 1 is ${part1(input)}")
        println("Real result for part 2 is ${part2(input)}")
    }

    private fun realData() {
        val input = loadFileToStringArray("/day18/input.txt")
        println("Real result for part 1 is ${part1(input)}")
        println("Real result for part 2 is ${part2(input)}")
    }

    private fun part1(input: ArrayList<String>): Long {
        return input.sumOf { solver.evaluatePart1(it) }
    }

    private fun part2(input: ArrayList<String>): Long {
        return input.sumOf { solver.evaluatePart2(it) }
    }
}