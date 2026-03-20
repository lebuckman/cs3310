# Prog1: DFS Traversal on Undirected Graphs

## Overview

**Depth First Search** (**DFS**) is a tree/graph traversal algorithm that explores as far as possible down each branch before backtracking.

Given undirected graph data via a text file, this program identifies all connected components and detects any cycles.

## Getting Started

From the **parent directory** of `prog1/`, compile and execute the code:

```bash
javac prog1/*.java

java prog1.Prog1 prog1/test.txt
```

> [!Note]
> This project was built and tested using OpenJDK version "21.0.1" 2023-10-17 LTS.
> Compatible with Java 8+

## Expected Input / Output

### Input Format

When executing, the program expects one command-line argument: the path to an input `.txt` file containing the graph data. A `test.txt` file has already been provided.

Each line in the input file represents one graph and must match the following format:

```plaintext
n (v1,v2) (v3,v4) (v5,v6)
```

Where:

- `n` = number of vertices in the graph
- `(vi,vj)` = undirected edge between vertices `vi` and `vj`
- Vertices are numbered starting from `1`

### Example Input File + Expected Output

Project assumes properly-formatted input. Output reports only the first cycle detected.

```
5 (1,2) (3,4) (3,5) (4,5)
4 (1,2) (2,3) (1,4)
```

```
Graph1:
2 connected components: {1 2} {3 4 5}
Cycle detected: 3 - 4 - 5 - 3

Graph2:
1 connected component: {1 2 3 4}
The graph is acyclic.
```

## Project Structure

```
prog1/
├── Prog1.java              # Main driver class
├── GraphFileReader.java    # File I/O and input parsing
├── Graph.java              # Graph and DFS algorithms
└── test.txt                # Test cases (3+ graphs / 7+ nodes each)
```

## Depth-First Search (DFS) Algorithm

### Approach

**Explore**: Recursively visit all reachable vertices from a starting vertex

**Track**: Maintain parent pointers and visited set during traversal

**Detect**: Identify back edges (edges to already-visited non-parent vertices) as cycles

```
Input Graph: 4 vertices with edges (1,2), (2,3), (3,4), (4,1)

DFS Traversal from vertex 1:
  Visit 1 → mark visited, parent[1] = null
  Visit 2 → mark visited, parent[2] = 1
  Visit 3 → mark visited, parent[3] = 2
  Visit 4 → mark visited, parent[4] = 3
  Check edge (4,1) → 1 is visited AND not parent → BACK EDGE

Cycle Detection:
  Back edge found: 4 → 1
  Trace parents: 4 → 3 → 2 → 1
  Reverse path: 1 → 2 → 3 → 4 → 1 (cycle ✔︎)

Output:
  1 connected component: {1 2 3 4}
  Cycle detected: 1 - 2 - 3 - 4 - 1
```

### Time Complexity: Θ(|V| + |E|)

The algorithm visits each vertex once and examines each edge twice (once from each endpoint).

**For each vertex v:**

1. Mark as visited: O(1)
2. Add to component set: O(1)
3. For each neighbor of v:
   - Check if visited (HashSet lookup): O(1)
   - Recursive DFS call OR detect back edge: O(1)

**TOTAL TIME FOR ENTIRE GRAPH:**

- Visit all V vertices: O(V)
- Examine all E edges (twice each in undirected graph): O(E)

Therefore, the worst-case time complexity is **Θ(|V| + |E|)**.

### DFS vs BFS

While both traversals share similar complexities, DFS is more suitable for this problem:

- **Component identification** - Each DFS traversal discovers exactly one connected component

- **Efficient cycle detection** - Back edges are easily identified during recursive traversal

- **Path reconstruction** - Parent pointers naturally form the cycle path for reconstruction
