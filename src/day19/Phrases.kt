package day19

class Phrases(input: ArrayList<String>) {

    private val phrases = mutableListOf<String>()

    init {
        parsePhrases(input)
    }

    private fun parsePhrases(input: java.util.ArrayList<String>) {
        var inRulesSection = true
        input.forEach {
            if (it.isEmpty()) {
                inRulesSection = false
                return@forEach
            }

            if (inRulesSection) return@forEach

            phrases.add(it)
        }
    }

    fun get() = phrases
}