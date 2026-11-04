public class Main {
    public static void main(String[] args) {
        ReportService service = new ReportService();

        // 1. Обрабатываем файл .txt (разделитель |)
        OrderParser txtParser = new PipeParser();
        service.generateReport("discount_day.txt", "result_txt.txt", txtParser);

        // 2. Обрабатываем файл без расширения (разделитель #)
        OrderParser hashParser = new HashParserAdapter();
        service.generateReport("discount_day_without_ext", "result_hash.txt", hashParser);
    }
}
