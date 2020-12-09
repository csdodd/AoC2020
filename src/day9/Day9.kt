package day9

import loadFileToIntArray
import loadFileToLongArray
import printAsHeader

class Day9 {

    fun run() {
        "Day9".printAsHeader()
        test()
        realData()
    }

    private fun test() {
        val preambleSize = 5
        val input = loadFileToLongArray("/day9/example.txt")
        val validator = XmasValidator(input, preambleSize)
        val invalidNumber = validator.findFirstInvalidNumber()
        val rangeForInvalidNumber = validator.findEncryptionBreakingNumber(invalidNumber)
        println("Invalid number for test data is $invalidNumber")
        println("Encryption breaking number for test data is $rangeForInvalidNumber")
    }

    private fun realData() {
        val preambleSize = 25
        val input = loadFileToLongArray("/day9/input.txt")
        val validator = XmasValidator(input, preambleSize)
        val invalidNumber = validator.findFirstInvalidNumber()
        val rangeForInvalidNumber = validator.findEncryptionBreakingNumber(invalidNumber)
        println("Invalid number for real data is $invalidNumber")
        println("Encryption breaking number for real data is $rangeForInvalidNumber")
    }

}