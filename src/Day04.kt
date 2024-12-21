
fun main() {
    val input = readInput("Day04")
    Day04.part1(input).println()
    Day04.part2(input).println()
}

object Day04 {
    fun part1(input: List<String>): Int {
        val crossword = input.toCharMatrix()
        var total = 0
        for(i in 0..<crossword.size) {
            for(j in 0..<crossword[0].size) {
                if(crossword[i][j] == 'X') {
                    total += crossword.numberOfMatches(i,j)
                }
            }
        }
        return total
    }

    fun part2(input: List<String>): Int {
        val crossword = input.toCharMatrix()
        var total = 0
        for(i in 0..<crossword.size) {
            for(j in 0..<crossword[0].size) {
                if(crossword[i][j] == 'A' && crossword.xMasMatch(i,j)) {
                    total++
                }
            }
        }
        return total
    }

    fun List<String>.toCharMatrix(): List<List<Char>> {
        return this.map{ line ->
            line.toCharArray().toList()
        }
    }

    fun List<List<Char>>.numberOfMatches(i: Int, j: Int): Int {
        var count = 0
        if(i+3 < this.size && this[i+1][j] == 'M' && this[i+2][j] == 'A' && this[i+3][j] == 'S')
            count++
        if(i-3 >= 0 && this[i-1][j] == 'M' && this[i-2][j] == 'A' && this[i-3][j] == 'S')
            count++
        if(j+3 < this[0].size && this[i][j+1] == 'M' && this[i][j+2] == 'A' && this[i][j+3] == 'S')
            count++
        if(j-3 >= 0 && this[i][j-1] == 'M' && this[i][j-2] == 'A' && this[i][j-3] == 'S')
            count++

        if(i+3 < this.size && j+3 < this[0].size && this[i+1][j+1] == 'M' && this[i+2][j+2] == 'A' && this[i+3][j+3] == 'S')
            count++
        if(i-3 >= 0 && j-3 >= 0 && this[i-1][j-1] == 'M' && this[i-2][j-2] == 'A' && this[i-3][j-3] == 'S')
            count++
        if(i-3 >= 0 && j+3 < this[0].size && this[i-1][j+1] == 'M' && this[i-2][j+2] == 'A' && this[i-3][j+3] == 'S')
            count++
        if(i+3 < this.size && j-3 >= 0 && this[i+1][j-1] == 'M' && this[i+2][j-2] == 'A' && this[i+3][j-3] == 'S')
            count++
        return count
    }

    fun List<List<Char>>.xMasMatch(i: Int, j: Int): Boolean {
        if(i-1 >= 0 && i+1 < this.size && j-1 >= 0 && j+1 < this[0].size) {
            if (this[i - 1][j - 1] == 'M' && this[i + 1][j + 1] == 'S' && this[i + 1][j - 1] == 'M' && this[i - 1][j + 1] == 'S')
                return true
            if (this[i - 1][j - 1] == 'M' && this[i + 1][j + 1] == 'S' && this[i + 1][j - 1] == 'S' && this[i - 1][j + 1] == 'M')
                return true
            if (this[i - 1][j - 1] == 'S' && this[i + 1][j + 1] == 'M' && this[i + 1][j - 1] == 'M' && this[i - 1][j + 1] == 'S')
                return true
            if (this[i - 1][j - 1] == 'S' && this[i + 1][j + 1] == 'M' && this[i + 1][j - 1] == 'S' && this[i - 1][j + 1] == 'M')
                return true

        }
        return false
    }
    //1014 is too low


}




