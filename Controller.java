import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Controller {
    public static void main (String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter filepath containing your word list: ");
        String fileName = scanner.nextLine();
        try {
            FileParser.getWordList(fileName).ifPresentOrElse(list -> {}, () -> {});
        } catch (IOException e) {
            System.out.println("Filepath provided is invalid");
            e.printStackTrace();
        }
    }
}