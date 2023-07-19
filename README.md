# aMazeGame

A game that generates a maze using different maze generation algorithms that the user needs to solve (reach the endpoint).
The user can select from a list of algorithms or let the game randomly select an algorithm to use after each maze is solved.

The game gives the player 30 seconds to solve as many mazes as possible. When the time runs out, the users Score (number
of mazes solved) and the users name will be saved to a database if the users score is within the top 5 scores.

# Game Play

The player controls the purple square (know as the runner, which is randomly positioned within the maze) around the maze
with the goal of reaching the blue square (know as the end point). The runner is controlled by using the arrow keys on 
the keyboard and when the player reaches the
end point ,and if there is still time,the game generates a new maze with a new starting point for the player.

Once the timer runs out the game displays the current leaderboard and allows the user to quit or play again.

# Requires
1) JavaFx
2) SQLite
# Concepts Used and Learnt

Designing a maze requires the use of different algorithms and data structures inorder to make the algorithm work 
and then create a maze

Learnt to use a GUI : This project uses javafx for its scenes and stages. 

Learnt the basics of SQL : This project makes use of SQLite.

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

# JavaFx

Learnt: Stages,Scenes,buttons and tableview.

Was able to switch between scenes to allow for the different parts of the game to work together.
# SQLite
SQLite was used to write the players name and scores to a score board/ leaderboard which is then displayed to the current player.
The player score is added only if their score is within the top 5 and their score is greater than 0.

Used SQLite commands to create a database, create tables within the database, read from the table, write to the table
and delete records from a table.

# Improvements
1) Allow the user to select how long the timer goes for. e.g. 30 sec, 45 sec or 1 minute etc.
2) Allow the user to select the number of leaderboard entries to see e.g. top 5 or top 10
3) Make the database configurable in its display e.g. see the top 5 for a specific algorithm.

# Known Bugs
1) Wasn't able to find a way to incorporate the timer stage into the maze scene so that the user can see the time ticking away
from within the maze scene.
Currently, the timer shows up on a separate stage behind the maze scene (requiring the player to move the maze stage away
in order to see the timer.)
2) The runner (maze runner) tends to shift of the centre of the pathway. Still adheres to boundary rules.

