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