package day16

data class Rule(
        val name: String,
        private val lowerRange: Pair<Int,  Int>,
        private val higherRange: Pair<Int,  Int>,
) {
    private val validNumbers = mutableMapOf<Int, Boolean>()

    init {
        for (i in lowerRange.first .. lowerRange.second) {
            validNumbers[i] = true
        }

        for (i in higherRange.first .. higherRange.second) {
            validNumbers[i] = true
        }
    }

    fun isValidNumber(number: Int) = validNumbers[number] == true
}