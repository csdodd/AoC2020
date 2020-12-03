package day1

import java.io.File
import java.net.URI

internal class Part1 {

    val numbers = mutableSetOf<Int>()

    fun run(): Int {
        return processNumbersFromFile()
    }

    private fun processNumbersFromFile(): Int {
        val fileUrl = javaClass.getResource("input.txt")
        val fileUri = URI(fileUrl.toString())
        File(fileUri).useLines {
            it.forEach {
                number ->
                val match = tryFindMatch(number.toInt())
                if (match != null) return match
                else numbers.add(number.toInt())
            }
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