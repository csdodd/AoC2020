package day1

import loadFileToIntArray
import java.io.File
import java.net.URI

internal class Part2 {

    private val numbers = mutableSetOf<Int>()

    fun run(): Int {
        readNumbersFromFile()
        return process()
    }

    private fun readNumbersFromFile() {
        val entries = loadFileToIntArray("/day1/input.txt")
        entries.forEach {number -> numbers.add(number) }
    }

    private fun process(): Int {
        numbers.forEach { i ->
            val remaining = 2020 - i
            numbers.forEach { j ->
                val matchingNumber = remaining - j
                if (numbers.contains(matchingNumber)) {
                    return matchingNumber * i * j
                }
            }
        }
        return 0
    }
}