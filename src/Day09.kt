
fun main() {
    val input = readInput("Day09")
    Day09.part1(input).println()
    Day09.part2(input).println()
}

object Day09 {
    fun part1(input: List<String>): Long {
        val diskMap = input[0]
        val processedMemory = diskMap.partition().flatten().fragment()
        return processedMemory.checkSum()
    }

    fun part2(input: List<String>): Long {
        val diskMap = input[0]
        val processedMemory = diskMap.partition().compact().flatten()
        return processedMemory.checkSum()
    }

    fun String.partition(): MutableList<List<Int>> {
        val partitions = mutableListOf<List<Int>>()
        this.forEachIndexed { position, quantity ->
            if(position.isAFilledPartition()) {
                val id = position / 2
                partitions.add(List<Int>(quantity.digitToInt()){id})
            } else {
                partitions.add(List<Int>(quantity.digitToInt()){-1})
            }
        }
        return partitions
    }

    fun Int.isAFilledPartition(): Boolean {
        return this.and(1) == 0
    }


    fun List<Int>.fragment(): List<Int> {
        val memory = this.toMutableList()
        var start = 0
        var end = memory.size - 1

        while(start < end) {
            if(memory[start] != -1) {
                start++
            }
            else if(memory[end] == -1) {
                end--
            }
            else {
                val temp = memory[start]
                memory[start] = memory[end]
                memory[end] = temp
                start++
                end--
            }
        }
        return memory
    }

    fun List<Int>.checkSum(): Long {
        var checkSum: Long = 0
        this.forEachIndexed { index, id ->
            if(id != -1) {
                checkSum += (index * id)
            }
        }
        return checkSum
    }

    fun MutableList<List<Int>>.compact(): MutableList<List<Int>> {
        for(id in this.fetchLastId()downTo 0) {
            val fileIndex = this.indexOfLast { it.isNotEmpty() && it[0] == id }
            var emptyIndex = this.indexOfFirst { it.isNotEmpty() && it[0] == -1 && it.size >= this[fileIndex].size }
            if(emptyIndex != -1 && emptyIndex < fileIndex) {
                //swap
                val gapSize = this[emptyIndex].size - this[fileIndex].size
                val file = this[fileIndex]
                this[fileIndex] = List(file.size){ -1 }
                this[emptyIndex] = file
                if(gapSize > 0) {
                    this.add(emptyIndex+1, List(gapSize){ -1 })
                }
            }
        }
        return this
    }

    fun Int.isEven(): Boolean {
        return this.and(1) == 0
    }

    fun List<List<Int>>.fetchLastId(): Int {
        return if(this.size.isEven()) {
            this[this.size-2][0]
        }
        else {
            this[this.size-1][0]
        }
    }

}




