package day3

class MapMarker (contents: Char) {
    val type = if (contents == '#') Type.Tree else Type.Open
}

enum class Type {
    Tree,
    Open
}