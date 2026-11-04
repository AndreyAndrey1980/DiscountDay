import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class HashParserAdapter implements OrderParser {
    private final RawHashReader rawReader = new RawHashReader();

    @Override
    public List<Order> parse(String filePath) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
            return rawReader.readRaw(filePath).stream()
                    .map(line -> {
                        String[] parts = line.split("#");
                        LocalDateTime time = LocalDateTime.parse(parts[0], formatter);
                        return new Order(time, parts[1], Double.parseDouble(parts[2]));
                    })
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Ошибка чтения файла: " + filePath, e);
        }
    }
}