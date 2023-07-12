# aMazeGame

A game that randomly generates a maze using different maze generation algorithms that the user needs to solve

# Concepts Used and Learnt

Designing a maze requires the use of different algorithms
and data structures inorder to make the algorithm work

# Algorithms

## Depth First Search

Is used for graph traversing and searching of graphs.It starts
at any selected point and checks as far as it can along the
selected point. When its reaches an end it backtracks to find
a previously visited point that still has available neighbors.

Data structures used: Stacks and ArrayLists

Notes: Tends to make long corridors in mazes.

## Randomised Kruskal Algorithm

It selects an edge (wall) at random and finds the 2 vertex (cells) that are connected by this
wall.
It then checks if these 2 cells belong to 2 separate sets:
if they do then the algorithm connects these 2 sets together and breaks the edge (wall) between them.
if they belong to the same set then the algorithm doesn't join them, this prevents cycles.

Data structures used: Disjoint-set data structure

Note: Tends to produce regular patterns, making it easier to solve

## Randomised Prims Algorithm

Stylistically the mazes tend to be similar to kruskal's algorithm but without the use of the
disjoint-set data structure.

Randomly select a starting cell, add the list of walls the cell has to a 'frontierList'.
from this list randomly select a wall and join the 2 cells this wall divides. From these 2 cells
add the walls of the unvisited cell to the frontierList.

Data structures used: ArraysLists

Notes: Stylistically can be similar to Kruskal's

## Aldous-Broder Algorithm

A simple algorithm. Starts by selecting a random cell and joins this cell to a randomly selected
 neighbor of it that hasn't been looked at yet and then makes this neighbor the next cell to look at.

If a cell doesn't have any unvisited neighbors, randomly select a new cell that does.

Data structures used: ArraysLists

Notes: An inefficient algorithm