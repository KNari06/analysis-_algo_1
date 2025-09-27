# Algorithms Assignment 1

## How to run
Open project in IntelliJ IDEA and run `algorithms.Main`.  
Optionally run with program argument `bench` to generate `bench_results.csv`.

## Implementations
- **MergeSort**: reusable buffer, cutoff = 16.
- **QuickSort**: randomized pivot, recurse on smaller partition.
- **Deterministic Select**: median-of-medians (groups of 5).
- **Closest Pair of Points**: divide-and-conquer, strip scan.

## Recurrence analysis
- MergeSort: Θ(n log n) — Master Theorem, Case 2.
- QuickSort: average Θ(n log n), worst Θ(n²) — recurse smaller side, bounded depth O(log n).
- Deterministic Select: Θ(n) — group of 5, recurse only one side.
- Closest Pair: Θ(n log n) — divide-and-conquer + strip check.

## Benchmarks
Run `Main` normally to see metrics output in console.  
Example run:

## Notes
- Metrics tracked: comparisons, allocations, recursion depth, runtime.
- Branches and commits follow assignment storyline.
- Final submission requires GitHub repository link (attach in Moodle).
