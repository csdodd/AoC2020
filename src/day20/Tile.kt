package day20

class Tile (input: List<String>) {

    var id: Int = -1
    private var data = mutableListOf<String>()

    private val borders by lazy { Side.values().map { getBorder(it) }.toSet() }
    private val bordersFlipped by lazy { borders.map { it.reversed() }.toSet() }

    init {
        parseTile(input)
    }

    private fun parseTile(input: List<String>) {
        id = input.first().substring(5).replace(":", "").trim().toInt()
        data = input.drop(1).toMutableList()
    }

    private fun getBorder(side: Side): String =
            when (side) {
                Side.Up -> data.first()
                Side.Down -> data.last()
                Side.Left -> data.map { it.first() }.joinToString("")
                Side.Right -> data.map { it.last() }.joinToString("")
            }


    private fun hasBorder(border: String) = border in borders || border in bordersFlipped

    private fun flip(): Tile {
        data = data.map { it.reversed() }.toMutableList()
        return this
    }

    private fun rotateClockwise(): Tile {
        data = data.mapIndexed { x, row ->
            row.mapIndexed { y, _ ->
                data[y][x]
            }.reversed().joinToString("")
        }.toMutableList()
        return this
    }

    fun allOrientations(): Sequence<Tile> = sequence {
        repeat(2) {
            repeat(4) {
                yield(this@Tile.rotateClockwise())
            }
            this@Tile.flip()
        }
    }

    private fun orientToCorrectBorder(border: String, side: Side) =
            allOrientations().first { it.getBorder(side) == border }

    fun getSharedBorderCount(tiles: List<Tile>): Int =
            borders.sumOf { side ->
                tiles
                        .filterNot { it.id == id }
                        .count { tile -> tile.hasBorder(side) }
            }

    fun isBorderShared(side: Side, tiles: List<Tile>): Boolean =
            tiles
                    .filterNot { it.id == id }
                    .any { tile -> tile.hasBorder(getBorder(side)) }

    fun findNeighbouringTile(side: Side, neighboursSide: Side, tiles: List<Tile>): Tile {
        val border = getBorder(side)
        return tiles
                .filterNot { it.id == id }
                .first { it.hasBorder(border) }
                .also { it.orientToCorrectBorder(border, neighboursSide) }
    }

    fun numRows() = data.size
    fun removeBorderForRow(row: Int): String = data[row].drop(1).dropLast(1)

    fun applyMask(mask: List<Pair<Int, Int>>): Boolean {
        var found = false
        val maxWidth = mask.maxByOrNull { it.second }?.second ?: 0
        val maxHeight = mask.maxByOrNull { it.first }?.first ?: 0
        (0..(data.size - maxHeight)).forEach { x ->
            (0..(data.size - maxWidth)).forEach { y ->
                val location = Pair(x, y)
                val actualSpots = mask.map { Pair(it.first + location.first, it.second + location.second) }
                if (actualSpots.all { data[it.first][it.second] == '#' }) {
                    found = true
                    actualSpots.forEach { data[it.first] = data[it.first].substring(0, it.second) + '0' + data[it.first].substring(it.second + 1) }
                }
            }
        }
        return found
    }

    fun countRoughness(): Int {
        return data.sumBy { row -> row.count { it == '#' } }
    }
}