
fun main() {
    val input = readInput("Day08")
    Day08.part1(input).println()
    Day08.part2(input).println()
}

object Day08 {
    fun part1(input: List<String>): Int {
        val map = input.toMutableCharMatrix()
        val antennas = map.getAntennaCoordinates().removeLoneAntennas()
        val antinodes = antennas.getUniqueAntinodeCoordinates().filterByBounds(map.size, map[0].size)
        return antinodes.size
    }

    fun part2(input: List<String>): Int {
        val map = input.toMutableCharMatrix()
        val antennas = map.getAntennaCoordinates().removeLoneAntennas()
        val antinodes = antennas.getUniqueResonantAntinodeCoordinatesBoundedBy(map.size, map[0].size)
        return antinodes.size
    }

    fun List<String>.toMutableCharMatrix(): MutableList<MutableList<Char>> {
        return this.map{ line -> line.toCharArray().toMutableList()}
            .toMutableList()
    }

    fun List<List<Char>>.getAntennaCoordinates() : Map<Char, List<List<Int>>> {
        val mapOfAntennas = HashMap<Char, MutableList<List<Int>>>()
        this.forEachIndexed { i, line ->
            line.forEachIndexed { j, letter ->
                if(letter != '.') {
                    val coordinateList = mapOfAntennas.getOrDefault(letter, mutableListOf<List<Int>>())
                    coordinateList.add(listOf<Int>(i,j))
                    mapOfAntennas[letter] = coordinateList
                }
            }
        }
        return mapOfAntennas
    }

    fun Map<Char, List<List<Int>>>.removeLoneAntennas(): Map<Char, List<List<Int>>> {
        return this.filter { entry -> entry.value.size > 1 }
    }

    fun Map<Char, List<List<Int>>>.getUniqueAntinodeCoordinates(): Set<List<Int>> {
        val setOfAntinodes = hashSetOf<List<Int>>()
        this.forEach { antennaType, listOfCoordinates ->
            listOfCoordinates.forEach { current ->
                listOfCoordinates.filter { it != current }.forEach { pair ->
                    val i = 2*pair[0]-current[0]
                    val j = 2*pair[1]-current[1]
                    setOfAntinodes.add(listOf<Int>(i,j))
                }
            }
        }
        return setOfAntinodes
    }

    fun Set<List<Int>>.filterByBounds(m: Int, n: Int): List<List<Int>> {
        return this.filter { coordinate ->
            (coordinate[0] in 0..<m) && (coordinate[1] in 0..<n)
        }
    }

    fun Map<Char, List<List<Int>>>.getUniqueResonantAntinodeCoordinatesBoundedBy(m: Int, n: Int): Set<List<Int>> {
        val setOfAntinodes = hashSetOf<List<Int>>()
        this.forEach { antennaType, listOfCoordinates ->
            listOfCoordinates.forEach { current ->
                listOfCoordinates.filter { it != current }.forEach { pair ->
                    var i = pair[0]
                    var j = pair[1]
                    while((i in 0..<m) && (j in 0..<n)) {
                        setOfAntinodes.add(listOf<Int>(i,j))
                        i += (pair[0]-current[0])
                        j += (pair[1]-current[1])
                    }
                }
            }
        }
        return setOfAntinodes
    }
}




