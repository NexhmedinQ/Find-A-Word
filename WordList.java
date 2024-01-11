import java.util.HashSet;
import java.util.List;

public class WordList {
    private HashSet<String> words;
    public WordList(HashSet<String> words) {
        this.words = words; 
    }

    public List<String> setToList() {
        return words.stream().toList();
    }

    public boolean containsWord(String word) {
        return words.contains(word);
    }
}
