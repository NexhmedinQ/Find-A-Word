import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Controller {
    private static Map<String, Long> wordMap;
    public static void main (String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter filepath containing your word list: ");
        String fileName = scanner.nextLine();
        try {
            FileParser.getWordList(fileName).ifPresentOrElse(list -> {
                wordMap = list
                                .stream()
                                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
            }, () -> {});
        } catch (IOException e) {
            System.out.println("Filepath provided is invalid");
            e.printStackTrace();
        }
    }
}