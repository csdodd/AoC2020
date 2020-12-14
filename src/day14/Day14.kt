package day14

import loadFileToStringArray
import printAsHeader

class Day14 {

    fun run() {
        "Day14".printAsHeader()
        test()
        realData()
    }

    private fun test() {
        val input = loadFileToStringArray("/day14/example.txt")
        val bitMasker = BitMaskerV1(input)
        println("Sum of all values from test data Part 1 is ${bitMasker.run()}")

        val inputV2 = loadFileToStringArray("/day14/example2.txt")
        val bitMaskerV2 = BitMaskerV2(inputV2)
        println("Sum of all values from test data Part 2 is ${bitMaskerV2.run()}")
    }

    private fun realData() {
        val input = loadFileToStringArray("/day14/input.txt")
        val bitMasker = BitMaskerV1(input)
        println("Sum of all values from real data Part 1 is ${bitMasker.run()}")

        val bitMaskerV2 = BitMaskerV2(input)
        println("Sum of all values from real data Part 2 is ${bitMaskerV2.run()}")
    }
}