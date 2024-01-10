import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Random;

public class MatrixGenerator {

    // would it have been better if I had only the matrix and just filled it with '\0' instead of using double the
    // memory for a boolean matrix
    private static boolean[][] isInUse;

    private static char[][] matrix;

    private static int maxPlacementAttempts;

    public static Optional<Matrix> generateMatrix(List<String> words) throws NoSuchElementException {
        // what would be the best practise with the following line when I'm getting the max length of the word list
        // In my main function I make sure the list isn't empty so the optional will never be empty but 
        // it's not good style to rely on other functions doing the right thing so I threw an error if the optional wasy empty
        // is there a better way to handle this?
        int maxLength = words.stream().mapToInt(word -> word.length()).max().orElseThrow(() -> new NoSuchElementException());
        int matrixSize = Math.max(maxLength * 2, words.size() * 2);
        maxPlacementAttempts = matrixSize * 2;
        matrix = new char[matrixSize][matrixSize];
        isInUse = new boolean[matrixSize][matrixSize];
        for (String word : words) {
            Direction placementDir = Direction.getRandomDirection();
            int attemptsMade = 0;
            boolean placementSuccess = false;
            while (attemptsMade < maxPlacementAttempts && !placementSuccess) {
                // randomly generate valid coordinates where the word will fit in the grid
                Coordinate startPos = getRandomPosition(placementDir, word);
                // check that there is no interference with words already placed
                if (isPositionValid(startPos, placementDir, word.length())) {
                    placeWord(startPos, placementDir, word);
                    placementSuccess = true;
                }
                attemptsMade++;
            }
            if (!placementSuccess) {
                return Optional.empty();
            }
        }
        placeRandomLetters();
        
        return Optional.of(new Matrix(matrix));
    }

    // makes sure none of the coordinates the word will fall on are already in use
    private static boolean isPositionValid(Coordinate start, Direction direction, int length) {
        int xPos = start.getX();
        int yPos = start.getY();
        while (length > 0) {
            if (isInUse[xPos][yPos]) return false;
            xPos += direction.getX();
            yPos += direction.getY();
            length--;
        }
        return true;
    }

    // given the x and y bounds get a random coordinate where we can place beginning of the word
    private static Coordinate getRandomPosition(Direction direction, String word) {
        Random random = new Random();
        int[] xBound = getRangeBound(word.length() * direction.getX());
        int[] yBound = getRangeBound(word.length() * direction.getY());
        int xOrd = random.nextInt(Math.max(xBound[0], xBound[1]) - Math.min(xBound[0], xBound[1])) + Math.min(xBound[0], xBound[1]);
        int yOrd = random.nextInt(Math.max(yBound[0], yBound[1]) - Math.min(yBound[0], yBound[1])) + Math.min(yBound[0], yBound[1]);
        return new Coordinate(xOrd, yOrd);
    }

    // places word in the matrix
    private static void placeWord(Coordinate start, Direction dir, String word) {
        int xPos = start.getX();
        int yPos = start.getY();
        for (char ch : word.toCharArray()) {
            matrix[xPos][yPos] = ch;
            isInUse[xPos][yPos] = true;
            xPos += dir.getX();
            yPos += dir.getY();
        }
    }

    // gets the bounds between for both x and y between which we can fit the word
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

    // places random letters at all the remaining coordinates
    private static void placeRandomLetters() {
        Random random = new Random();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if (!isInUse[i][j])  matrix[i][j] = (char) ('a' + random.nextInt(26));
            }
        }
    }
}
