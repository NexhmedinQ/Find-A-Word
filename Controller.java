import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Controller {
    
    // chose a hashmap with the word as the key and the value as the frequency of the word since the same
    // word can appear more than once. 
    private static Map<String, Long> wordMap;
    public static void main (String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter filepath containing your word list: ");
        String fileName = scanner.nextLine();
        try {
            FileParser.getWordList(fileName).ifPresentOrElse(list -> {
                wordMap = list
                        .stream()
                        .map(word -> word.toLowerCase())
                        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
            }, () -> {
                System.out.println("file does not contain a list of words");
                return;
            });
            Optional<Matrix> matrix = MatrixGenerator.generateMatrix(mapToList());
            if (!matrix.isPresent()) {
                System.out.println("error generating the find a word");
                scanner.close();
                return;
            }
            
            System.out.println(matrix.get().toString());
        } catch (IOException e) {
            System.out.println("Filepath provided is invalid");
        } catch (NoSuchElementException e) {
            System.out.println("Word list provided is empty");
        }
        scanner.close();
    }

    private static List<String> mapToList() {
        return wordMap.entrySet()
                        .stream()
                        .flatMap(entry -> Collections.nCopies(entry.getValue().intValue(), entry.getKey()).stream())
                        .collect(Collectors.toList());
    }
}
