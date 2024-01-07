import java.util.List;
import java.util.Random;

public class MatrixGenerator {

    private enum Direction {
        EAST(1, 0), SOUTH(0, 1), SOUTH_EAST(1, 1), WEST(-1, 0),
        NORTH(0, -1), NORTH_WEST(-1, -1), NORTH_EAST(1, -1),
        SOUTH_WEST(-1, 1);
    
        private final int x;
        private final int y;
    
        Direction(int x, int y) {
            this.x = x;
            this.y = y;
        }
    
        public int getX() {
            return x;
        }
    
        public int getY() {
            return y;
        }
    
        private static final Random random = new Random();

        public static Direction getRandomDirection() {
            return values()[random.nextInt(values().length)];
        }
    }

    private boolean[][] isInUse;
    private static char[][] matrix;

    public static Matrix generateMatrix(List<String> words) {
        // remember to change - can't assume function will be used properly so need to handle case where max gives us empty
        // optional.
        int maxLength = words.stream().mapToInt(word -> word.length()).max().getAsInt();
        int matrixSize = Math.max(maxLength * 2, words.size() * 2);
        matrix = new char[matrixSize][matrixSize];
        Direction placementDir = Direction.getRandomDirection();
        return new Matrix(matrix);
    }

}
