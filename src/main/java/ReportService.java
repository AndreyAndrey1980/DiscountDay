import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ReportService {
    public void generateReport(List<Order> allOrders, String outputPath, double pricePerKg, DiscountCalculator calculator) {
        allOrders.sort(Comparator.comparing(Order::getTime));

        Map<String, Double> result = new LinkedHashMap<>();
        for (int i = 0; i < allOrders.size(); i++) {
            Order order = allOrders.get(i);
            double discount = calculator.calculateDiscount(i + 1);
            double finalPrice = order.getAmount() * pricePerKg * (1 - discount / 100.0);
            result.merge(order.getName(), finalPrice, Double::sum);
        }

        try {
            List<String> lines = result.entrySet().stream()
                    .map(entry -> entry.getKey() + ":" + entry.getValue())
                    .collect(Collectors.toList());
            Files.write(Paths.get(outputPath), lines);
        } catch (IOException e) {
            throw new RuntimeException("Ошибка записи: " + outputPath, e);
        }
    }
}
