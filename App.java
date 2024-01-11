import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class App {

    private static HashSet<String> wordSet;
    private static final String wordFile = "words.txt";
    public static void main (String[] args) {

        final Scanner scanner = new Scanner(System.in);
        System.out.print("Enter filepath containing your word list: ");
        String fileName = scanner.nextLine();

        try {
            FileParser.getWordList(wordFile).ifPresentOrElse(list -> {
                wordSet = (HashSet<String>) list.stream().collect(Collectors.toSet());
            }, () -> {
                System.out.println("file does not contain a list of words");
                return;
            });
            MatrixGenerator generator = new MatrixGenerator();
            Optional<Matrix> matrix = generator.generateMatrix(setToList());
            if (!matrix.isPresent()) {
                System.out.println("error generating the find a word");
                return;
            }
            
            System.out.println(matrix.get().toString());
        } catch (IOException e) {
            System.out.println("Filepath provided is invalid");
        } catch (NoSuchElementException e) {
            System.out.println("Word list provided is empty");
        } finally {
            scanner.close();
        }
        // scanner.close();
    }

    private static List<String> setToList() {
        return wordSet.stream().toList();
    }
}
