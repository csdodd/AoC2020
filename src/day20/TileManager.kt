package day20

import kotlin.math.sqrt

class TileManager (input: ArrayList<String>) {

    private val tiles = mutableListOf<Tile>()
    private val seaMonsterMask = listOf(
            Pair(0, 18), Pair(1, 0), Pair(1, 5), Pair(1, 6), Pair(1, 11), Pair(1, 12),
            Pair(1, 17), Pair(1, 18), Pair(1, 19), Pair(2, 1), Pair(2, 4), Pair(2, 7),
            Pair(2, 10), Pair(2, 13), Pair(2, 16)
    )

    init {
        parseTiles(input)
    }

    private fun findTopCorner() =
        tiles
            .first { tile -> tile.getSharedBorderCount(tiles) == 2 }
            .allOrientations()
            .first {
                it.isBorderShared(Side.Down, tiles) && it.isBorderShared(Side.Right, tiles)
            }

    fun createImage(): List<List<Tile>> {
        val width = sqrt(tiles.count().toFloat()).toInt()
        var mostRecentTile = findTopCorner()
        var mostRecentRowHeader = mostRecentTile

        return (0 until width).map { row ->
            (0 until width).map { col ->
                when {
                    row == 0 && col == 0 ->
                        mostRecentTile
                    col == 0 -> {
                        mostRecentRowHeader =
                                mostRecentRowHeader.findNeighbouringTile(Side.Down, Side.Up, tiles)
                        mostRecentTile = mostRecentRowHeader
                        mostRecentRowHeader
                    }
                    else -> {
                        mostRecentTile =
                                mostRecentTile.findNeighbouringTile(Side.Right, Side.Left, tiles)
                        mostRecentTile
                    }
                }
            }
        }
    }

    fun applySeaMonsterMask(image: List<List<Tile>>): Int {
        return imageAsTile(image)
                .allOrientations()
                .first { it.applyMask(seaMonsterMask) }
                .countRoughness()
    }

    private fun imageAsTile(image: List<List<Tile>>): Tile {
        val rowsPerTile = tiles.first().numRows()
        val body = image.flatMap { row ->
            (1 until rowsPerTile - 1).map { y ->
                row.joinToString("") { it.removeBorderForRow(y) }
            }
        }
        return Tile(listOf("Tile: 0").plus(body))
    }


    private fun parseTiles(input: ArrayList<String>) {
        val lines = mutableListOf<String>()
        input.forEach {
            if (it.isEmpty()) {
                tiles.add(Tile(lines))
                lines.clear()
                return@forEach
            } else {
                lines.add(it)
            }
        }

        tiles.add(Tile(lines))
        lines.clear()
    }

}