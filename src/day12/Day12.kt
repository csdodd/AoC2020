package day12

import loadFileToStringArray
import printAsHeader

class Day12 {

    fun run() {
        "Day12".printAsHeader()
       // test()
       realData()
    }

    private fun test() {
        val input = loadFileToStringArray("/day12/example.txt")
        val result = ShipPart1().move(input)
        println("Manhattan distance of test data Part 1 is $result")

        val result2 = ShipPart2().move(input)
        println("Manhattan distance of test data Part 2 is $result2")
    }

    private fun realData() {
        val input = loadFileToStringArray("/day12/input.txt")
        //val result = ShipPart1().move(input)
        //println("Manhattan distance of real data Part 1 is $result")

        val result2 = ShipPart2().move(input)
        println("Manhattan distance of real data Part 2 is $result2")
    }
}