public class Matrix {

    private char[][] matrix;

    public Matrix(char[][] matrix) {
        this.matrix = matrix;
    }

    @Override
    public String toString() {
        StringBuilder resultString = new StringBuilder();

        resultString.append("     ");
        for (int col = 0; col < matrix[0].length; col++) {
            resultString.append(String.format("%3d", col));
        }
        resultString.append("\n");

        for (int row = 0; row < matrix.length; row++) {
            resultString.append(String.format("%3d |", row));
            for (int col = 0; col < matrix[row].length; col++) {
                resultString.append(String.format("%3c", matrix[row][col]));
            }
            resultString.append("\n");
        }

        return resultString.toString();
    }

    public boolean wordSearch(Direction direction, String word, Coordinate start) {
        // in our example the generator creates a square matrix so xLen and yLen are equal
        // but that relies on knowledge of MatrixGenerator so I decided to check len of both sides in Matrix class
        int yLen = matrix.length;
        if (yLen == 0) {
            return false;
        }
        int xLen = matrix[0].length;
        int currentX = start.xOrdinate();
        int currentY = start.yOrdinate();
        int strPosition = 0;
        while (currentX > -1 && currentX < xLen && currentY > -1 && currentY < yLen && strPosition < word.length()) {
            if (word.charAt(strPosition) != matrix[currentY][currentX]) {
                return false;
            }
            strPosition += 1;
            currentX += direction.getX();
            currentY += direction.getY();
        }
        return strPosition == word.length();
    }
    

}
