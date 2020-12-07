package day7

class Bag(
        private val rawInput: String
) {
    val name: String = calculateName()
    val amount = calculateAmount()

    private fun calculateName(): String {
        val words = normaliseBagName(rawInput).split(" ")
        return words.subList(1, words.count()).joinToString(" ")
    }

    private fun calculateAmount(): Int {
        val words = normaliseBagName(rawInput).split(" ")
        return words[0].toIntOrNull() ?: 0
    }

    private fun normaliseBagName(bagName: String): String {
        return bagName
                .replace("bags", "")
                .replace("bag", "")
                .replace(".", "")
                .trim()
    }
}