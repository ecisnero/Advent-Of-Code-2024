
fun main() {
    val input = readInput("Day10")
    Day10.part1(input).println()
    Day10.part2(input).println()
}

object Day10 {
    fun part1(input: List<String>): Int {
        val map = input.toMutableCharMatrix()
        return map.getTrailScores().sum()
    }

    fun part2(input: List<String>): Int {
        val map = input.toMutableCharMatrix()
        return map.getTrailRatings().sum()
    }

    fun List<String>.toMutableCharMatrix(): MutableList<MutableList<Char>> {
        return this.map{ line -> line.toCharArray().toMutableList() }
            .toMutableList()
    }

    fun List<List<Char>>.getTrailHeads(): List<List<Int>> {
        val listOfZeros = mutableListOf<List<Int>>()
        this.forEachIndexed { i, line ->
            line.forEachIndexed { j, letter ->
                if(letter == '0') {
                    listOfZeros.add(listOf<Int>(i,j))
                }
            }
        }
        return listOfZeros
    }

    fun List<List<Char>>.getTrailScores(): List<Int> {
        return this.getTrailHeads().map { head ->
            this.getTrailsFor(head[0], head[1]).toSet().size
        }
    }

    fun List<List<Char>>.getTrailRatings(): List<Int> {
        return this.getTrailHeads().map { head ->
            this.getTrailsFor(head[0], head[1]).size
        }
    }

    fun List<List<Char>>.getTrailsFor(i: Int, j: Int): List<List<Int>>  {
        val trailEnds = ArrayList<List<Int>>()
        mapTrail(this, trailEnds, i, j, '0')
        return trailEnds
    }

    fun mapTrail(map: List<List<Char>>, trails: MutableList<List<Int>>, i: Int, j:Int, search: Char) {
        //Base Case: Out of bounds or breaks constraint
        if(i !in map.indices || j !in map[0].indices || map[i][j] != search) {
            return
        }
        //Base Case: Reached a trail end
        if(search == '9') {
            trails.add(listOf(i,j))
            return
        }
        // Recursive Case
        mapTrail(map, trails, i+1,j, search+1)
        mapTrail(map, trails,i-1,j, search+1)
        mapTrail(map, trails,i,j+1, search+1)
        mapTrail(map, trails,i,j-1, search+1)
    }
}




