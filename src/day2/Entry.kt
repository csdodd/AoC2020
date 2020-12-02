package day2

data class Entry (
    val firstNumber: Int,
    val secondNumber: Int,
    val letter: Char,
    val password: String
) {

    fun isValidAccordingToOldPolicy(): Boolean {
        val totalOccurrences = password.filter { it == letter }.count()
        return totalOccurrences in firstNumber..secondNumber
    }

    fun isValidAccordingToNewPolicy(): Boolean {
        val letterAtFirstPlace = password[firstNumber - 1]
        val letterAtSecondPlace = password[secondNumber - 1]
        val firstPositionIsCorrect = letterAtFirstPlace == letter
        val secondPositionIsCorrect = letterAtSecondPlace == letter
        return firstPositionIsCorrect.xor(secondPositionIsCorrect)
    }
}