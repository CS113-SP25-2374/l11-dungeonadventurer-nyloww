public class DungeonMap {
    public static final char WALL = '#';
    public static final char OPEN = '.';

    public static final char ENTRANCE = 'E';
    public static final char CHEST = 'C';
    public static final char STATUE = 'S';
    public static final char GEM = 'G';
    public static final char EXIT = 'X';

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
}