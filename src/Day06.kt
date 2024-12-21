
fun main() {
    val input = readInput("Day06")
    Day06.part1(input).println()
    Day06.part2(input).println()
}

object Day06 {
    fun part1(input: List<String>): Int {
        val map = input.toMutableCharMatrix()
        val stepCount = map.traceGuardPath().countGuardSteps()
        return stepCount
    }

    fun part2(input: List<String>): Int {
        return 0
    }

    fun List<String>.toMutableCharMatrix(): MutableList<MutableList<Char>> {
        return this.map{ line -> line.toCharArray().toMutableList()}
            .toMutableList()
    }

    fun MutableList<MutableList<Char>>.getGuardPosition(): List<Int>{
        this.forEachIndexed { i, row ->
            row.forEachIndexed { j, letter ->
                when(letter) {
                    '^','>','v','<' -> return listOf<Int>(i, j)
                }
            }
        }
        return emptyList()
    }

    fun MutableList<MutableList<Char>>.traceGuardPath(): MutableList<MutableList<Char>> {
        val m = this.size
        val n = this[0].size

        val position = this.getGuardPosition()
        var i = position[0]
        var j = position[1]

        while (i in 0..<m && j in 0..<n) {
            val orientation = this[i][j]
            when (orientation) {
                '^' -> {
                    if (i - 1 < 0) {
                        this[i--][j] = 'X'
                    } else if (this[i - 1][j] != '#') {
                        this[i--][j] = 'X'
                        this[i][j] = orientation
                    } else {
                        this[i][j] = '>'
                    }
                }
                '>' -> {
                    if (j + 1 >= n) {
                        this[i][j++] = 'X'
                    } else if (this[i][j + 1] != '#') {
                        this[i][j++] = 'X'
                        this[i][j] = orientation
                    } else {
                        this[i][j] = 'v'
                    }
                }
                'v' -> {
                    if (i + 1 >= m) {
                        this[i++][j] = 'X'
                    } else if (this[i + 1][j] != '#') {
                        this[i++][j] = 'X'
                        this[i][j] = orientation
                    } else {
                        this[i][j] = '<'
                    }
                }
                '<' -> {
                    if (j - 1 < 0) {
                        this[i][j--] = 'X'
                    } else if (this[i][j - 1] != '#') {
                        this[i][j--] = 'X'
                        this[i][j] = orientation
                    } else {
                        this[i][j] = '^'
                    }
                }
                else -> Unit
            }
        }

        return this
    }

    fun List<List<Char>>.countGuardSteps(): Int {
        return this.sumOf { it.count{ it == 'X'} }
    }

}




