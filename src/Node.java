public class Node implements Comparable<Node>{
    // Coordinates of the node in the dungeon map
    public int x, y, g, h;

    // Character representing the item at this node
    public char item;

    // Tracks if the node has been visited
    public boolean visited;

    // Parent node for path reconstruction (A* algorithm)
    public Node parent;

    // Constructor to initialize node with coordinates and item
    public Node(int x, int y, char item) {
        this.x = x;
        this.y = y;
        this.item = item;
        this.visited = false;
    }

    // Calculates Manhattan distance (H value) to the goal node
    public void calcH(Node goal) {
        h = Math.abs(this.x - goal.x) + Math.abs(this.y - goal.y);
    }

    // Returns the total cost (F value) of the node (G + H)
    public int f(){ return g + h;}

    // String representation for debugging
    public String toString() {
        return item + "(" + x + "," + y + ")";
    }

    // Comparison based on F value (for priority queue in A*)
    @Override
    public int compareTo(Node o){
        return Integer.compare(this.f(), o.f());
    }

    // Equality check based on coordinates
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Node other)) return false;
        return this.x == other.x && this.y == other.y;
    }

    // Hash code based on coordinates and cost values
    @Override
    public int hashCode() {
        return x * y + h + g;
    }

}
