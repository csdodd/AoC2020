package day16

import loadFileToStringArray
import printAsHeader

class Day16 {

    fun run() {
        "Day16".printAsHeader()
        test()
        //realData()
    }

    private fun test() {
        val input = loadFileToStringArray("/day16/example.txt")
        val ticketRules = TicketInformation(input)
        println("Test result is ${ticketRules.findSumOfInvalidFields()}")
    }

    private fun realData() {
        val input = loadFileToStringArray("/day16/input.txt")
        val ticketRules = TicketInformation(input)
        println("Real result is ${ticketRules.findSumOfInvalidFields()}")
    }
}