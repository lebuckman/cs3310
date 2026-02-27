# Prog1: DFS Traversal on Undirected Graphs

## Overview

A Java-based implementation of **Depth First Search** to traverse a given undirected graph, identifying all connected components and detecting any cycles. [View project repository with commit history on GitHub](https://github.com/lebuckman/cs3310/tree/main/prog1).

**Author**: Liam Buckman | **BroncoName**: lebuckman | **BroncoID**: 016916341

**Course**: Spring 2026 CS3310.01


## Getting Started

From the **parent directory** of `prog1/`, compile and execute the code:

```bash
javac prog1/*.java

java prog1.Prog1 prog1/test.txt
```

> _This project was built and tested using OpenJDK version "21.0.1" 2023-10-17 LTS._
> _Compatible with Java 8+_

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
