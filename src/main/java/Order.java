import java.time.LocalDateTime;

public class Order {
    private final LocalDateTime time;
    private final String name;
    private final double price;

    public Order(LocalDateTime time, String name, double price) {
        this.time = time;
        this.name = name;
        this.price = price;
    }

    public LocalDateTime getTime() { return time; }
    public String getName() { return name; }
    public double getPrice() { return price; }
}
