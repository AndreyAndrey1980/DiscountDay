public class DiscountCalculator {
    public static double calculateDiscount(int orderNumber) {
        if (orderNumber == 1 || orderNumber == 2 || orderNumber == 3) {
            return 50.0;
        }

        double discount = 50.0 - (orderNumber - 3) * 5.0;
        return Math.max(0.0, discount);
    }
}
