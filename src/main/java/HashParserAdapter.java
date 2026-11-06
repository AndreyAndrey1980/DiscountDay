import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class HashParserAdapter implements OrderParser {
    private final RawHashReader rawReader = new RawHashReader();

    @Override
    public List<Order> parse(String filePath) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

        try (Stream<String> lines = rawReader.readRaw(filePath)) {
            return lines
                    .map(line -> {
                        String[] parts = line.split("#");
                        if (parts.length != 3) {
                            throw new IllegalArgumentException("Неверный формат строки: " + line);
                        }
                        LocalDateTime time = LocalDateTime.parse(parts[0], formatter);
                        return new Order(time, parts[1], Double.parseDouble(parts[2]));
                    })
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Ошибка чтения файла: " + filePath, e);
        }
    }
}