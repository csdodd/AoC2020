package day1

import printAsHeader

class Day1() {
    fun run() {
        val part1Solution = Part1().run()
        val part2Solution = Part2().run()
        "Day1".printAsHeader()
        println("The product of the two numbers that sum to 2020 is: $part1Solution")
        println("The product of the three numbers that sum to 2020 is: $part2Solution")
    }
}