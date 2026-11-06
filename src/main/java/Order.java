import java.time.LocalDateTime;

public class Order {
    private final LocalDateTime time;
    private final String name;
    private final double amount;

    public Order(LocalDateTime time, String name, double amount) {
        this.time = time;
        this.name = name;
        this.amount = amount;
    }

    public LocalDateTime getTime() { return time; }
    public String getName() { return name; }
    public double getAmount() { return amount; }
}