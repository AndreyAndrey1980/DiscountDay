import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class PipeParser implements OrderParser {
    @Override
    public List<Order> parse(String filePath) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        try {
            return Files.lines(Paths.get(filePath))
                    .map(line -> {
                        String[] parts = line.split("\\|");
                        LocalDateTime time = LocalDateTime.parse(parts[0], formatter);
                        return new Order(time, parts[1], Double.parseDouble(parts[2]));
                    })
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException("Ошибка чтения файла: " + filePath, e);
        }
    }
}