package day1

import loadFileToIntArray
import java.io.File
import java.net.URI

internal class Part1 {

    private val numbers = mutableSetOf<Int>()

    fun run(): Int {
        return processNumbersFromFile()
    }

    private fun processNumbersFromFile(): Int {
        val entries = loadFileToIntArray("/day1/input.txt")
        entries.forEach { number ->
            val match = tryFindMatch(number)
            if (match != null) return match
            else numbers.add(number)
        }
        return 0
    }

    private fun tryFindMatch(newInt: Int): Int? {
        val matchingNumber = 2020 - newInt
        if (numbers.contains(matchingNumber)) {
            return matchingNumber * newInt
        }
        return null
    }
}