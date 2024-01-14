import java.util.HashSet;
import java.util.List;

public class WordSet {
    private final HashSet<String> wordSet;

    public WordSet(HashSet<String> words) {
        this.wordSet = words; 
    }

    public List<String> setToList() {
        return wordSet.stream().toList();
    }

    public boolean containsWord(String word) {
        return wordSet.contains(word);
    }

    public HashSet<String> getWordSet() {
        return new HashSet<>(wordSet);
    }

    @Override
    public String toString() {
        List<String> wordList = setToList();
        return String.join(" ", wordList);
    }
}
