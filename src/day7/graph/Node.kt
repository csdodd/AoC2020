package day7.graph

data class Node(val name: String) {
    var contents: MutableList<Segment> = mutableListOf()
    var parents: MutableList<Segment> = mutableListOf()

    fun addContent(node: Node, count: Int) {
        val segment = Segment(node, count)
        contents.add(segment)
    }

    fun addParent(node: Node) {
        val segment = Segment(node, 0)
        parents.add(segment)
    }

    fun contentCount(): Int {
        return contents.sumBy { it.count }
    }
}

data class Segment(val node: Node, val count: Int) {
    //var calculatedDistFromSource: Int = 0
}