import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ReportService {
    public void generateReport(String inputPath, String outputPath, OrderParser parser) {
        List<Order> orders = parser.parse(inputPath);

        orders.sort(Comparator.comparing(Order::getTime));

        Map<String, Double> result = new LinkedHashMap<>();

        for (int i = 0; i < orders.size(); i++) {
            Order order = orders.get(i);
            double discount = DiscountCalculator.calculateDiscount(i + 1);
            double finalPrice = order.getPrice() * (1 - discount / 100.0);

            result.merge(order.getName(), finalPrice, Double::sum);
        }

        try {
            List<String> lines = result.entrySet().stream()
                    .map(entry -> entry.getKey() + ":" + entry.getValue())
                    .collect(Collectors.toList());
            Files.write(Paths.get(outputPath), lines);
        } catch (IOException e) {
            throw new RuntimeException("Ошибка записи файла: " + outputPath, e);
        }
    }
}
