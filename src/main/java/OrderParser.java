import java.util.List;

public interface OrderParser {
    List<Order> parse(String filePath);
}
