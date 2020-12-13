package day13

class TimeTable(
        input: String
) {
    val busses = input
            .split(',')
            .map { it.toIntOrNull() }
            .filterNotNull()

    private val indexedBusses: List<IndexedBus> = input
            .split(",")
            .mapIndexedNotNull { index, s -> if (s == "x") null else IndexedBus(index, s.toLong()) }

    fun findUniqueBusId(departureTime: Int): Int {
        val nextBus = busses.map {
            Pair(it, departureTime % it)
        }
        .maxBy { it.second } ?: return 0

        val diff = (((departureTime / nextBus.first) + 1) * nextBus.first)  - departureTime
        return nextBus.first * diff
    }

    fun findAppropriateTimestamp(): Long {
        var stepSize = indexedBusses.first().bus
        var time = 0L
        indexedBusses.drop(1).forEach { (offset, bus) ->
            while ((time + offset) % bus != 0L) {
                time += stepSize
            }
            stepSize *= bus
        }
        return time
    }

    data class IndexedBus(val index: Int, val bus: Long)
}