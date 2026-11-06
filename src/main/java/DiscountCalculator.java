public class DiscountCalculator {
    private final double startDiscount;
    private final double stepDiscount;

    public DiscountCalculator(double startDiscount, double stepDiscount) {
        this.startDiscount = startDiscount;
        this.stepDiscount = stepDiscount;
    }

    public double calculateDiscount(int orderNumber) {
        if (orderNumber == 1 || orderNumber == 2 || orderNumber == 3) {
            return startDiscount;
        }
        double discount = startDiscount - (orderNumber - 3) * stepDiscount;
        return Math.max(0.0, discount);
    }
}
