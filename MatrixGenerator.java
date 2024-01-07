import java.util.List;
import java.util.Optional;
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
    private static final int maxPlacementAttempts = 10;

    public static Optional<Matrix> generateMatrix(List<String> words) {
        // remember to change - can't assume function will be used properly so need to handle case where max gives us empty
        // optional.
        int maxLength = words.stream().mapToInt(word -> word.length()).max().getAsInt();
        int matrixSize = Math.max(maxLength * 2, words.size() * 2);
        matrix = new char[matrixSize][matrixSize];
        for (String word : words) {
            Direction placementDir = Direction.getRandomDirection();
            int attemptsMade = 0;
            boolean placementSuccess = false;
            while (attemptsMade < maxPlacementAttempts && !placementSuccess) {
                // randomly generate valid coordinates where the word will fit in the grid
                // check that there is no interference with words already placed

                // in case of interference we go again
                // otherwise place word -> make sure to also change inUse
            }
            if (!placementSuccess) {
                return Optional.empty();
            }
        }
        
        return Optional.of(new Matrix(matrix));
    }

    private boolean isPositionValid(int x, int y, Direction direction, int length) {
        return false;
    }

    // might create a Coordinate type to make it more readable and self explanatory??
    private int[] getRandomPosition(Direction direction, String word) {
        return new int[2];
    }

    private void placeWord(int x, int y, Direction dir, String word) {
        // change word matrix and inUse boolean matrix
    }

}
