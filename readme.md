# L11 - Dungeon Adventurer

## We're going to traverse this fancy dungeon:

    public static final char[][] MAP = {
            {'#','#','#','#','#','#','#','#','#','#'},
            {'#','E','.','.','.','.','.','.','X','#'},
            {'#','.','.','.','#','G','.','.','.','#'},
            {'#','.','.','.','#','.','.','.','.','#'},
            {'#','.','.','.','#','.','.','.','.','#'},
            {'#','.','#','.','#','.','#','#','.','#'},
            {'#','C','#','.','#','.','#','S','.','#'},
            {'#','.','.','.','.','.','.','.','.','#'},
            {'#','#','#','#','#','#','#','#','#','#'},
    };

The ultimate goal is to find the shortest path through the dungeon that visits all of the treasures and reaches the exit!

Returning to previous location is okay (but not necessary).

Steps to accomplish this:

1) Build a list of locations: scan the map and create a list of the five key locations. You'll need to create a helper class to represent them!
2) Find the shortest possible distance between each location. Dijkstra's is a good starting place! Stretch goal: use the hyristics of A*!
3) Now that you have a list of locations, and a list of distances between them...you have everything you need to make a graph!
4) To ensure that we have the Minimum Spanning Tree (MST), we can use Prim's algorithm to prune away the excess!
5) Finally, output the optimized route to the console!
