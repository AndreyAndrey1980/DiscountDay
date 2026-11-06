import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        double pricePerKg = 10.0;
        DiscountCalculator calculator = new DiscountCalculator(50.0, 5.0);

        OrderParser txtParser = new PipeParser();
        OrderParser hashParser = new HashParserAdapter();

        List<Order> allOrders = new ArrayList<>();
        allOrders.addAll(txtParser.parse("discount_day.txt"));
        allOrders.addAll(hashParser.parse("discount_day_without_ext"));

        ReportService service = new ReportService();
        service.generateReport(allOrders, "result_total.txt", pricePerKg, calculator);
    }
}