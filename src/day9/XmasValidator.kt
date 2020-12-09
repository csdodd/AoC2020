package day9

class XmasValidator(
        private val input: ArrayList<Long>,
        private val preambleSize: Int
) {
    fun findFirstInvalidNumber(): Long {

        input.forEachIndexed { i, number ->
            if (i < preambleSize) return@forEachIndexed

            val preamble = input.subList(i - preambleSize, i)
            if (!isValid(number, preamble)) return number
        }
        return 0
    }

    private fun isValid(number: Long, preamble: MutableList<Long>): Boolean {
        preamble.forEachIndexed outer@ { i, outerPreambleNumber ->
            val remainder = number - outerPreambleNumber
            if (remainder <= 0) return@outer

            preamble.forEachIndexed inner@ { j, innerPreambleNumber ->
                if (i == j) return@inner
                if (innerPreambleNumber == remainder) return true
            }
        }

        return false
    }

    fun findEncryptionBreakingNumber(invalidNumber: Long): Long {
        val range = findContiguousRangeForNumber(invalidNumber)
        return (range.minOrNull() ?: 0L) + (range.maxOrNull() ?: 0L)
    }

    private fun findContiguousRangeForNumber(target: Long): MutableList<Long> {
        input.forEachIndexed { lowerIndex, _ ->
            var count = 0L
            var upperIndex = lowerIndex

            for(n in input.subList(lowerIndex, input.size)) {
                upperIndex++
                count += n
                if (count == target) return input.subList(lowerIndex, upperIndex)
                if (count > target) break
            }
        }
        return mutableListOf()
    }
}