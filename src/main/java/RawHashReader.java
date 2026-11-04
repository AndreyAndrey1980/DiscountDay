import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class RawHashReader {
    public List<String> readRaw(String filePath) throws IOException {
        return Files.readAllLines(Paths.get(filePath));
    }
}