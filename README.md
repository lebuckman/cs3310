# CS3310 — Design and Analysis of Algorithms

A personal repo for coursework that implements and analyzes fundamental algorithms and data structures for **CS3310: Design and Analysis of Algorithms** (Spring 2026).

**Textbook:** _Introduction to the Design and Analysis of Algorithms_ by Anita Levitin

- **Algorithm Design Techniques:** Divide-and-conquer, transform-and-conquer, greedy technique, dynamic programming, etc.
- **Time Complexity Analysis:** Big-O, Θ, and Ω notation
- **Data Structures:** Graphs, trees, heaps, hash tables, etc. and their applications
- **Problem-Solving:** Developing efficient solutions to computational problems

## Repository Structure

```
class_repo/
├── 💻 prog1/              # Cycle Detection w/ DFS
├── 💻 prog2/              # Anagram Detection w/ Presorting
├── 💻 prog3/              # Canoe Trip Cost Optimizer w/ DP
└── 📄 STYLE_GUIDE.pdf     # Class Coding Style Requirements
```

## Assignments

Each programming assignment is self-contained in its own directory with documentation and algorithm explanation. To ensure comparability and consistency across submissions, all programming assignments are required to be in Java. Refer to the [CS3310 Style Guide](./STYLE_GUIDE.pdf) for code styling requirements.

### 💻 [Prog1: Graph Traversal & Cycle Detection](./prog1/)

Analyzes undirected graphs to identify connected components and detect cycles using DFS.

- **Key Concepts:** Graph theory, DFS traversal, component analysis, tree vs. back edges
- **Time Complexity**: Θ(|V| + |E|)

### 💻 [Prog2: Anagram Finder](./prog2/)

Finds all anagram sets in large word files using a presorting technique.

- **Key Concepts:** Transform-and-conquer, presorting, hash tables, algorithmic efficiency
- **Time Complexity**: Θ(n × m log m)

### 💻 [Prog3: Canoe Trip Cost Optimizer](./prog3/)

Finds the minimum cost sequence of canoe rentals across a series of trading posts using dynamic programming.

- **Key Concepts:** Dynamic programming, all-pairs optimization, path reconstruction
- **Time Complexity**: Θ(n³)
