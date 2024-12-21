
fun main() {
    val input = readInput("Day05")
    Day05.part1(input).println()
    Day05.part2(input).println()
}

object Day05 {
    fun part1(input: List<String>): Int {
        val separatorIdx = input.indexOf("")
        val constraintLines = input.subList(0,separatorIdx)
        val constraintMap = constraintLines.convertToMap()

        val  updateLines = input.subList(separatorIdx+1, input.size)
        val medianSum = updateLines
            .map { it.toIntList() }
            .filter { it.isValid(constraintMap) }
            .sumOf { it.median() }
        return medianSum
    }

    fun part2(input: List<String>): Int {
        val separatorIdx = input.indexOf("")
        val constraintLines = input.subList(0,separatorIdx)
        val constraintMap = constraintLines.convertToMap()

        val  updateLines = input.subList(separatorIdx+1, input.size)
        val medianSum = updateLines
            .map { it.toIntList() }
            .filter { !it.isValid(constraintMap) }
            .map { it.sortByConstraints(constraintMap) }
            .sumOf { it.median() }
        return medianSum
    }


    fun List<String>.convertToMap(): Map<Int, List<Int>> {
        val constraintMap = hashMapOf<Int, ArrayList<Int>>()
        this.forEach { line ->
            val constraint = line.split("|")
            val predecessor: Int = constraint[0].toInt()
            val current: Int = constraint[1].toInt()

            val precedenceList = constraintMap.getOrDefault(current, arrayListOf<Int>())
            precedenceList.add(predecessor)
            constraintMap[current] = precedenceList
        }
        return constraintMap
    }

    fun String.toIntList(): List<Int> {
        return this.split(",").map{ it.toInt() }
    }
    fun List<Int>.isValid(constraintMap: Map<Int, List<Int>>): Boolean {
        val blacklist = hashSetOf<Int>()
        this.forEach { num ->
            if(num in blacklist)
                return false
            constraintMap[num]?.let{ results ->
                blacklist.addAll(results)
            }
        }
        return true
    }

    fun List<Int>.median(): Int = this[this.size/2]

    fun List<Int>.sortByConstraints(map: Map<Int, List<Int>>): List<Int> {
        // a-b, a>b, positive number if b precedes a
        val comparator = Comparator<Int> { a, b ->
            if(map[a]?.contains(b) == true) 1
            else if(map[b]?.contains(a) == true) -1
            else 0
        }
        return this.toMutableList().also { it.sortWith(comparator) }
    }
    


}




