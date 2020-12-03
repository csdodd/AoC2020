package day3

data class MapMarker (
        val contents: Char
) {
    fun isTree() = contents == '#'
}