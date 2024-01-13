import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class FileParser {
    public static Optional<List<String>> getWordList(String filePath) throws IOException {
        if (filePath == null) {
            return Optional.empty();
        }
        Path path = Paths.get(filePath);
        List<String> wordList = Files.lines(path)
            .flatMap(line -> List.of(line.split("\\s+")).stream())
            .collect(Collectors.toList());
        return wordList.size() > 0 ? Optional.of(wordList) : Optional.empty();
    }
}
