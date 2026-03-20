# Prog2: Anagrams

## 📖 Overview

An **anagram** is a word or phrase that is created by rearranging the letters of another word or phrase. For example, the words "eat", "ate", and "tea" are anagrams of one another.

This program finds all sets of anagrams in a text file using a **presorting algorithm** (Transform-and-Conquer) that sorts "letter signatures" to group anagrams in optimal time.

## ⚡ Getting Started

From the **parent directory** of `prog2/`, compile and execute the code:

```bash
javac prog2/*.java

java prog1.Prog2 prog2/words.txt

# Save output to a file
java prog1.Prog2 prog2/words.txt > output.txt
```

> [!Note]
> This project was built and tested using OpenJDK version "21.0.1" 2023-10-17 LTS.
> Compatible with Java 8+

## 📋 Expected Input / Output

### Input Format

When executing, the program expects one command-line argument: the path to an input `.txt` file containing the anagram data. A `test.txt` and `words.txt` file has already been provided. The provided plaintext file should contain one word per line.

### Example Input File + Expected Output

Project assumes properly-formatted input. Output reports detected anagram sets. While words within each set are alphabetically sorted, the sets themselves are not sorted.

- Case-insensitive (`Elvis` == `elvis`)
- Punctuation ignored (`it's` == `its`)
- Accented characters are normalized (`éclair` == `eclair`)
- Duplicate words automatically filtered out

```
eat
tea
ate
Elvis
lives
```

```
Anagram Sets Found:

Set 1 (3 words): ate, eat, tea
Set 2 (2 words): Elvis, lives
```

## 📂 Project Structure

```
prog2/
├── Prog2.java              # Main driver class
├── AnagramFinder.java      # Core algorithm
├── test.txt                # Various test cases (90+ words)
└── words.txt               # Large dictionary (99,000+ words)
```

## 🔤 Presorting Algorithm

### 🎯 Approach

**Transform**: Convert each word into a sorted-letter signature

**Conquer**: Group words with identical signatures with HashMap

```
Input: "eat", "tea", "ate", "hello"

Transform:
  "eat"   → lowercase → "eat"   → sort letters → "aet"
  "tea"   → lowercase → "tea"   → sort letters → "aet"
  "ate"   → lowercase → "ate"   → sort letters → "aet"
  "hello" → lowercase → "hello" → sort letters → "ehllo"

HashMap:
  "aet"   → ["eat", "tea", "ate"]  ← Anagram set!
  "ehllo" → ["hello"]              ← Single word, skip

Output:
  Set 1 (3 words): ate, eat, tea
```

### ⏳ Time Complexity: Θ(n × m log m)

The algorithm processes each of the n words exactly once.

For each word:

1. toLowerCase(): O(m)
2. replaceAll(): O(m)
3. toCharArray(): O(m)
4. Arrays.sort(): O(m log m)
5. new String: O(m)
6. HashMap put/get operations: ~ O(1)

The sorting step dominates: O(m log m)

**TOTAL TIME FOR n WORDS:**

For each of n words, we perform O(m log m) work.

Therefore, the worst-case time complexity is Θ(n × m log m).
