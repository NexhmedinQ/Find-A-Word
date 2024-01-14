import java.io.IOException;
import java.util.HashSet;
import java.util.stream.Collectors;

public class App {

    private static WordSet wordSet;
    private static final String wordFile = "words.txt";
    public static void main (String[] args) {

        try {
            FileParser.getWordList(wordFile).ifPresentOrElse(list -> {
                wordSet = new WordSet((HashSet<String>) list.stream().collect(Collectors.toSet()));
            }, () -> {
                System.out.println("file does not contain a list of words");
                return;
            });
            MatrixGenerator generator = new MatrixGenerator();
            Main mainExecution = new Main(wordSet, generator);
            mainExecution.execute();
        } catch (IOException e) {
            System.out.println("Filepath provided is invalid");
        } 
    }
}