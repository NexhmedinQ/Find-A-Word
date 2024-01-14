import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    WordSet wordSet;
    MatrixGenerator generator;

    public Main(WordSet wordList, MatrixGenerator generator) {
        this.generator = generator;
        this.wordSet = wordList;
    }

    public void execute() {
        try (Scanner scanner = new Scanner(System.in)) {
            Optional<Matrix> matrix = generator.generateMatrix(wordSet.setToList());
            matrix.ifPresentOrElse(puzzle -> mainGameLoop(puzzle, scanner), () -> {
                System.out.println("error generating the find a word");
                return;
            });
        } catch (NoSuchElementException e) {
            System.out.println("Word list provided is empty");
        }
    }

    private void mainGameLoop(Matrix matrix, Scanner scanner) {
        gameRules();
        System.out.println(matrix.toString());
        System.out.println(wordSet.toString());
        HashSet<String> words = wordSet.getWordSet();
        while (!words.isEmpty()) {
            System.out.print("Please search for a word: ");
            String input = scanner.nextLine();
            String[] eachInput = input.split(":|,");
            if (!isInputValid(input)) {
                System.out.println("Please provide valid input.");
                continue;
            }
            Direction.getDirection(eachInput[2]).ifPresentOrElse(dir -> {
                String wordToSearch = eachInput[3];
                if (matrix.wordSearch(dir, wordToSearch, new Coordinate(Integer.parseInt(eachInput[0]), Integer.parseInt(eachInput[1])))) {
                    if (words.contains(wordToSearch)) {
                        System.out.println("You have found word: " + wordToSearch);
                        words.remove(wordToSearch);
                    } else {
                        System.out.println("Word specified was found however it is not in the list of remaining words to find");
                    }
                } else {
                    System.out.println("Word not found in that position and direction");
                };
            }, () -> System.out.println("Direction provided was not valid"));

            
        }
        System.out.println("Congrats! You've found every word and won the game.");
    }

    private void gameRules() {
        System.out.println("When you have identified a word in the matrix please provide input in order to find the word in the following format: ");
        System.out.println("<x co-ordinate>,<y co-ordinate>:<Direction>:<word> where <> are not part of the string (they just signify the variables).");
        System.out.println("Direction is given by the following: NORTH, EAST, SOUTH, WEST, SOUTH_WEST, SOUTH_EAST, NORTH_EAST, NORTH_WEST");
    }

    private boolean isInputValid(String input) {
        String regex = "\\d+,\\d+:[A-Z]+:[A-Za-z]+";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }
}
