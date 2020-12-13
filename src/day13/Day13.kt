package day13

import loadFileToStringArray
import printAsHeader

class Day13 {

    fun run() {
        "Day13".printAsHeader()
        test()
        realData()
    }

    private fun test() {
        val input = loadFileToStringArray("/day13/example.txt")
        val timetable = TimeTable(input[1])
        val departTime = input[0].toIntOrNull() ?: 0
        println("UniqueID of next bus for test data is ${timetable.findUniqueBusId(departTime)}")
        println("Timestamp for test data is ${timetable.findAppropriateTimestamp()}")
    }

    private fun realData() {
        val input = loadFileToStringArray("/day13/input.txt")
        val timetable = TimeTable(input[1])
        val departTime = input[0].toIntOrNull() ?: 0
        println("UniqueID of next bus for real data is ${timetable.findUniqueBusId(departTime)}")
        println("Timestamp for real data is ${timetable.findAppropriateTimestamp()}")
    }
}