import java.util.*;

public class Explorer {
    // The 2D dungeon map
    char[][] map;

    // List of key locations (items) in the dungeon
    LinkedList<Node> keyLocations = new LinkedList<>();

    // List of all paths between key locations
    LinkedList<LinkedList<Node>> totalPaths = new LinkedList<>();

    public Explorer() {}

    // Scans the map to identify all key locations (non-wall, non-open spaces)
    public void scanner(char[][] map) {
        this.map = map;

        for(int y = 0; y < map.length; y++) {
            for(int x = 0; x < map[y].length; x++) {
                char c = map[y][x];
                if (c != DungeonMap.OPEN && c != DungeonMap.WALL) {
                    // Create a Node for each item found
                    Node n = new Node(x, y, c);
                    keyLocations.add(n);
                }
            }
        }
        System.out.println(keyLocations);
    }

    // Directions for movement (up, down, left, right)
    int[][] moves = { {-1, 0}, {1, 0}, {0, -1}, {0, 1} };

    // Finds paths between all key locations using A* algorithm
    public void findPaths() {
        totalPaths.clear();
        for (Node from : keyLocations) {
            for (Node to : keyLocations) {
                if (!from.equals(to)) {
                    LinkedList<Node> path = astar(from, to);
                    if (path != null) totalPaths.add(path);
                }
            }
        }
        System.out.println(totalPaths);
    }

    // A* search algorithm to find the shortest path between two points
    public LinkedList<Node> astar(Node start, Node end) {
        boolean[][] visited = new boolean[map.length][map[0].length];
        PriorityQueue<Node> openSet = new PriorityQueue<>();

        start.g = 0;
        start.calcH(end);
        openSet.add(start);

        while (!openSet.isEmpty()) {
            Node current = openSet.poll();
            if (current.equals(end)) return reconstructPath(current);

            for (int[] move : moves) {
                int nextX = current.x + move[0];
                int nextY = current.y + move[1];
                if (isValid(nextX, nextY, visited)) {
                    Node neighbor = new Node(nextX, nextY, map[nextY][nextX]);
                    neighbor.g = current.g + 1;
                    neighbor.calcH(end);
                    neighbor.parent = current;
                    openSet.add(neighbor);
                    visited[nextY][nextX] = true;
                }
            }
        }
        return null;
    }

    // Validates if the next position is within bounds and not a wall
    private boolean isValid(int x, int y, boolean[][] visited) {
        return x >= 0 && y >= 0 && y < map.length && x < map[0].length && map[y][x] != DungeonMap.WALL && !visited[y][x];
    }

    // Reconstructs the path from end to start using parent nodes
    private LinkedList<Node> reconstructPath(Node current) {
        LinkedList<Node> path = new LinkedList<>();
        while (current != null) {
            path.addFirst(current);
            current = current.parent;
        }
        return path;
    }

    // Uses Prim's algorithm to find the Minimum Spanning Tree (MST)
    public void findMST() {
        Set<Character> visited = new HashSet<>();
        Map<String, Edge> mst = new HashMap<>();
        PriorityQueue<Edge> pq = new PriorityQueue<>();

        // Start with the first key location
        Node start = keyLocations.getFirst();
        visited.add(start.item);

        // Add all paths to the priority queue
        for (LinkedList<Node> path : totalPaths) {
            pq.add(new Edge(path));
        }

        // Build MST using Prim's Algorithm
        while (!pq.isEmpty() && visited.size() < keyLocations.size()) {
            Edge edge = pq.poll();
            char from = edge.path.peekFirst().item;
            char to = edge.path.peekLast().item;

            if (visited.contains(to)) continue;

            visited.add(to);
            mst.put(edge.key(), edge);

            // Add new edges connected to the newly visited node
            for (LinkedList<Node> path : totalPaths) {
                if (path.peekFirst().item == to || path.peekLast().item == to) {
                    pq.add(new Edge(path));
                }
            }
        }

        System.out.println(mst);
    }

    // Represents an edge between two key locations
    static class Edge implements Comparable<Edge> {
        LinkedList<Node> path;

        public Edge(LinkedList<Node> path) { this.path = path; }

        // Creates a unique key for the edge
        public String key() {
            return path.peekFirst().item + "->" + path.peekLast().item;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.path.size(), o.path.size());
        }

        @Override
        public String toString() {
            return key() + " [" + path.size() + "]";
        }
    }
}
