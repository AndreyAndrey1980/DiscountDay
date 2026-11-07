import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ReportService {
    public void generateReport(List<Order> allOrders, String outputPath, BigDecimal pricePerKg, DiscountCalculator calculator) {
        allOrders.sort(Comparator.comparing(Order::getTime));

        Map<String, BigDecimal> result = new LinkedHashMap<>();
        for (int i = 0; i < allOrders.size(); i++) {
            Order order = allOrders.get(i);
            double discount = calculator.calculateDiscount(i + 1);

            BigDecimal total = BigDecimal.valueOf(order.getAmount()).multiply(pricePerKg);
            BigDecimal discountMultiplier = BigDecimal.valueOf(1 - discount / 100.0);
            BigDecimal finalPrice = total.multiply(discountMultiplier);

            result.merge(order.getName(), finalPrice, BigDecimal::add);
        }

        try {
            List<String> lines = result.entrySet().stream()
                    .map(entry -> entry.getKey() + ":" + entry.getValue().setScale(2, RoundingMode.HALF_UP).toPlainString())
                    .collect(Collectors.toList());
            Files.write(Paths.get(outputPath), lines);
        } catch (IOException e) {
            throw new RuntimeException("Ошибка записи: " + outputPath, e);
        }
    }
}