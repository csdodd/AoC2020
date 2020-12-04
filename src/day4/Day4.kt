package day4

import loadFileToStringArray
import printAsHeader

class Day4 {

    fun run() {
        "Day4".printAsHeader()
        println("Test result is: ${test()}")

        val results = realData()
        println("The number of valid passports with loose validation is: ${results.first}")
        println("The number of valid passports with strict validation is: ${results.second}")
    }

    private fun realData(): Pair<Int, Int> {
        val input = loadFileToStringArray("/day4/input.txt")
        val passports = generatePassports(input)
        return Pair(
                validatePassportsLoosely(passports),
                validatePassportsStrictly(passports)
        )
    }
    private fun test(): Int {
        val input = loadFileToStringArray("/day4/example.txt")
        val passports = generatePassports(input)
        return validatePassportsLoosely(passports)
    }

    private fun validatePassportsLoosely(passports: ArrayList<Passport>): Int {
        return passports
                .filter { it.isLooselyValidated() }
                .count()
    }

    private fun validatePassportsStrictly(passports: ArrayList<Passport>): Int {
        return passports
                .filter { it.isStrictlyValidated() }
                .count()
    }

    private fun generatePassports(input: ArrayList<String>): ArrayList<Passport> {
        val passports = arrayListOf<Passport>()

        var passportString = ""

        input.forEachIndexed { i, line ->
            passportString = "$passportString $line"

            if (line.trim().isEmpty() || i == input.size - 1) {
                val passport = Passport(passportString.trim())
                passports.add(passport)
                passportString = ""
            }
        }
        return passports
    }
}