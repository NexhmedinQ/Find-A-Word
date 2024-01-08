import java.util.List;
import java.util.Optional;
import java.util.Random;

public class MatrixGenerator {

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
                Coordinate startPos = getRandomPosition(placementDir, word);
                // check that there is no interference with words already placed
                if (isPositionValid(startPos, placementDir, maxLength)) {
                    placeWord(startPos, placementDir, word);
                    placementSuccess = true;
                }
                attemptsMade++;
            }
            if (!placementSuccess) {
                return Optional.empty();
            }
        }
        
        return Optional.of(new Matrix(matrix));
    }

    private static boolean isPositionValid(Coordinate start, Direction direction, int length) {
        return false;
    }

    private static Coordinate getRandomPosition(Direction direction, String word) {
        Random random = new Random();
        int[] xBound = getRangeBound(word.length() * direction.getX());
        int[] yBound = getRangeBound(word.length() * direction.getY());
        int xOrd = random.nextInt(Math.max(xBound[0], xBound[1]) - Math.min(xBound[0], xBound[1])) + Math.min(xBound[0], xBound[1]);
        int yOrd = random.nextInt(Math.max(yBound[0], yBound[1]) - Math.min(yBound[0], yBound[1])) + Math.min(yBound[0], yBound[1]);
        return new Coordinate(xOrd, yOrd);
    }

    private static void placeWord(Coordinate start, Direction dir, String word) {
        // change word matrix and inUse boolean matrix
    }

    private static int[] getRangeBound(int bound) {
        int[] ret = new int[2];
        if (bound < 0) {
            ret[0] = matrix.length;
            ret[1] = bound * -1;
        } else {
            ret[0] = 0;
            ret[1] = matrix.length - bound + 1;
        }
        return ret;
    }

}
