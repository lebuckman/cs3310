# Prog3: Canoe Trip Cost Optimizer

## 📖 Overview

Given a sequence of trading posts along a river and rental costs between every pair of posts, this program finds the **minimum cost route** from the first post to the last. Canoes may be exchanged at any intermediate post at no extra charge, so the optimal route may involve multiple rentals.

The program uses a **bottom-up dynamic programming** algorithm closely mirroring Floyd's Algorithm for all-pairs shortest paths.

## ⚡ Getting Started

From the **parent directory** of `prog3/`, compile and execute the code:

```bash
javac prog3/*.java

java prog3.Prog3 prog3/inputA
```

> [!Note]
> This project was built and tested using OpenJDK version "21.0.1" 2023-10-17 LTS.
> Compatible with Java 8+

## 📋 Expected Input / Output

### Input Format

When executing, the program expects one command-line argument: the path to an input file containing the cost matrix data. The file should be formatted as follows:

- Line 1: a single integer `n` (number of trading posts)
- Lines 2 to n: the upper triangle of the cost matrix, row by row, where row `i` contains rental costs from post `i` to posts `i+1 .. n-1`

### Example Input File + Expected Output

Project assumes properly-formatted input. All costs are positive integers and whitespace between values is not significant.

```
4
10 15 50
   40 20
      35
```

```
Optimal cost matrix:
           0     1     2     3
      ------------------------
   0 |    --    10    15    30
   1 |    --    --    40    20
   2 |    --    --    --    35
   3 |    --    --    --    --

Optimal rental sequence (post 0 to post 3):
  rent from post 0 to post 1  (cost: 10)
  rent from post 1 to post 3  (cost: 20)

Total optimal cost: 30
```

## 📂 Project Structure

```
prog3/
├── Prog3.java          # Main driver class
├── CanoeSolver.java    # Core DP algorithm
├── inputA              # Ordinary data (mixed stops)
├── inputB              # Direct route cheapest
└── inputC              # Every stop cheapest
```

## 🚣 Dynamic Programming Algorithm

### 🎯 Approach

**Recurrence**: For each pair of posts (i, j), the optimal cost is either the direct rental or the best split at some intermediate post k

**Bottom-up**: Fill the table by increasing gap (j - i), so all sub-problems are solved before they are needed

**Reconstruct**: Follow split points recorded in `next[i][j]` to recover the actual rental sequence

### Recurrence Formula

```
Base case:   opt[i][i+1] = C[i][i+1]

Recursive:   opt[i][j] = min( C[i][j],  min{ opt[i][k] + opt[k][j] : i < k < j } )
```

### Example Walkthrough (n = 4)

```
Direct costs C[i][j]:
  C[0][1]=10  C[0][2]=15  C[0][3]=50
              C[1][2]=40  C[1][3]=20
                          C[2][3]=35

Gap = 1 (base cases — only one option, the direct rental):
  opt[0][1] = 10
  opt[1][2] = 40
  opt[2][3] = 35

Gap = 2 (try one intermediate stop):
  opt[0][2]: direct=15, try k=1: 10+40=50 → keep 15
  opt[1][3]: direct=20, try k=2: 40+35=75 → keep 20

Gap = 3 (try all intermediate stops):
  opt[0][3]: direct=50
    try k=1: opt[0][1] + opt[1][3] = 10 + 20 = 30 ✔ update!
    try k=2: opt[0][2] + opt[2][3] = 15 + 35 = 50 ✘
  opt[0][3] = 30  →  next[0][3] = 1

Reconstruct from next[0][3] = 1:
  rent from post 0 to post 1  (cost: 10)
  rent from post 1 to post 3  (cost: 20)

Total: 30
```

### ⏳ Time Complexity: Θ(n³)

Let **n** = number of trading posts.

The algorithm uses three nested loops — one for gap size, one for the starting post `i`, and one for the intermediate stop `k` — each performing O(1) work per iteration. This structure mirrors Floyd's Algorithm for all-pairs shortest paths, and yields the same **Θ(n³)** runtime.
