package day19

import loadFileToStringArray
import printAsHeader
import kotlin.collections.ArrayList

class Day19 {

    fun run() {
        "Day19".printAsHeader()
        test()
        realData()
    }

    private fun test() {
        val input = loadFileToStringArray("/day19/example.txt")
        val result = getCountOfValidPhrasesFromRule0(input)
        println("Result for test data is $result")
    }

    private fun realData() {
        val input = loadFileToStringArray("/day19/input.txt")
        val result = getCountOfValidPhrasesFromRule0(input)
        println("Result for real data Part 1 is $result")

        val input2 = loadFileToStringArray("/day19/input2.txt")
        val result2 = getCountOfValidPhrasesFromRule0(input2)
        println("Result for real data Part 2 is $result2")
    }

    private fun getCountOfValidPhrasesFromRule0(input: ArrayList<String>): Int {
        val rules = Rules(input)
        val phrases = Phrases(input)

        return phrases.get().count { phrase ->
            rules.isValid(phrase, listOf(0))
        }
    }
}