fun main() {
    val input = readInput("Day12")
    Day12.part1(input).println()
    Day12.part2(input).println()
}

object Day12 {
    fun part1(input: List<String>): Long {
        val map = input.toCharMatrix()
        val areas = map.getRegionAreas()
        val perimeters = map.getRegionPerimeters()
        var price = 0L
        areas.forEach{ type, areaList ->
            for(i in areaList.indices) {
                price += (areas[type]!![i] * perimeters[type]!![i])
            }
        }
        return price
    }

    fun part2(input: List<String>): Long {
        return 0
    }

    fun List<String>.toCharMatrix(): List<List<Char>> {
        return this.map{ line ->
            line.toCharArray().toList()
        }
    }

    fun List<List<Char>>.getCoordinatesByType() : Map<Char, Set<List<Int>>> {
        val mapOfTypes = HashMap<Char, HashSet<List<Int>>>()
        this.forEachIndexed { i, line ->
            line.forEachIndexed { j, letter ->
                val coordinateSet = mapOfTypes.getOrPut(letter){ HashSet<List<Int>>() }
                coordinateSet.add(listOf<Int>(i,j))
            }
        }
        return mapOfTypes
    }

    fun Map<Char, Set<List<Int>>>.splitIntoRegions(): Map<Char, List<Set<List<Int>>>> {
        val mapOfRegions = HashMap<Char, List<Set<List<Int>>>>()
        this.forEach { (type, coordSet) ->
            mapOfRegions[type] = coordSet.splitSets()
        }
        return mapOfRegions
    }

    fun Set<List<Int>>.splitSets(): List<Set<List<Int>>> {
        val setList = mutableListOf<Set<List<Int>>>()
        val superset = this.toMutableSet()
        val queue = ArrayDeque<List<Int>>()

        while(superset.isNotEmpty()) {
            var current = superset.first()
            superset.remove(current)
            val subset = HashSet<List<Int>>().also { it.add(current) }
            queue.addLast(current)

            while(queue.isNotEmpty()) {
                current = queue.removeFirst()
                checkNeighbor(current[0], current[1]+1, superset, subset, queue)
                checkNeighbor(current[0], current[1]-1, superset, subset, queue)
                checkNeighbor(current[0]+1, current[1], superset, subset, queue)
                checkNeighbor(current[0]-1, current[1], superset, subset, queue)
            }
            setList.add(subset)
        }
        return setList
    }

    fun checkNeighbor(i: Int, j:Int, superset: MutableSet<List<Int>>, subset: MutableSet<List<Int>>, queue: ArrayDeque<List<Int>>) {
        val neighbor = listOf(i,j)
        if(superset.contains(neighbor)) {
            superset.remove(neighbor)
            subset.add(neighbor)
            queue.addLast(neighbor)
        }
    }


    fun List<List<Char>>.getRegionAreas() : Map<Char, List<Int>> {
        val areas = HashMap<Char, List<Int>>()
        this.getCoordinatesByType().splitIntoRegions().forEach { (type, regions) ->
            areas[type] = regions.map { it.size }
        }
        return areas
    }

    fun List<List<Char>>.getRegionPerimeters() : Map<Char, List<Int>> {
        val perimeters = HashMap<Char, List<Int>>()
        this.getCoordinatesByType().splitIntoRegions().forEach { (type, regions) ->
            perimeters[type] = regions.map {
                it.sumOf { point ->
                    perimeterAtCoordinate(point[0],point[1])
                }
            }
        }
        return perimeters
    }

    fun List<List<Char>>.perimeterAtCoordinate(i: Int, j: Int): Int{
        var perimeter = 0
        val region = this[i][j]
        if(i-1 < 0 || this[i-1][j] != region)
            perimeter++
        if(i+1 >= this.size || this[i+1][j] != region)
            perimeter++
        if(j-1 < 0 || this[i][j-1] != region)
            perimeter++
        if(j+1 >= this[0].size || this[i][j+1] != region)
            perimeter++
        return perimeter
    }
}






