package day7.graph

import day7.Bag
import java.util.ArrayList

class Graph(input: ArrayList<String>) {

    private val graph = mutableSetOf<Node>()
    private var runningTotal: Int = 0

    init {
        input.map { line ->
            generateNodeFromLine(line, graph)
        }
    }

    private fun generateNodeFromLine(line: String, graph: MutableSet<Node>) {
        val parts = line.split(" contain ")
        val currentBag = Bag(" 1 " + parts[0])
        val bags = parseChildBags(parts[1])
        val node = graph.singleOrNull { it.name == currentBag.name } ?: Node(currentBag.name)

        bags.forEach { childBag ->
            val childNode = graph.singleOrNull { it.name == childBag.name } ?: Node(childBag.name)
            node.addContent(childNode, childBag.amount)
            graph.add(childNode)
            childNode.addParent(node)
        }
        graph.add(node)
    }

    private fun parseChildBags(input: String): ArrayList<Bag> {
        val childrenBags = ArrayList<Bag>()

        if (input == "no other bags.") {
            return childrenBags
        }
        val parts = input.split(",")
        parts.forEach {
            childrenBags.add(Bag(it))
        }
        return childrenBags
    }


    fun countNumberOfGoldContainers(): Int {
        val goldNode = graph.singleOrNull { it.name == "shiny gold" } ?: return 0

        val allParents = mutableSetOf<String>()
        return countNumberOfParents(goldNode, allParents)
    }

    fun countNumberOfGoldContents(): Int {
        runningTotal = 0
        val goldNode = graph.singleOrNull { it.name == "shiny gold" } ?: return 0
        return countNumberOfChildren(goldNode)
    }

    private fun countNumberOfParents(node: Node, allParents: MutableSet<String>): Int {
        node.parents.forEach {
            allParents.add(it.node.name)
            countNumberOfParents(it.node, allParents)
        }
        return allParents.count()
    }

    private fun countNumberOfChildren(node: Node, amount: Int = 1): Int {
        if (node.contents.isEmpty()) return 0
        return node.contentCount() + (node.contents.sumBy { it.count * countNumberOfChildren(it.node, it.count) })
    }
}