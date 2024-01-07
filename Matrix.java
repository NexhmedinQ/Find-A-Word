public class Matrix {

    private char[][] matrix;

    public Matrix(char[][] matrix) {

    }

    @Override
    public String toString() {
        StringBuilder resultString = new StringBuilder();

        // Add column headings
        resultString.append("    ");
        for (int col = 0; col < matrix[0].length; col++) {
            resultString.append(String.format("%3d", col + 1));
        }
        resultString.append("\n");

        for (int row = 0; row < matrix.length; row++) {
            resultString.append(String.format("%3d |", row + 1));
            for (int col = 0; col < matrix[row].length; col++) {
                resultString.append(String.format("%3c", matrix[row][col]));
            }
            resultString.append("\n");
        }

        return resultString.toString();
    }
    

}
