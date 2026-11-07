public class DiscountCalculator {
    private final double startDiscount;
    private final double stepDiscount;

    public DiscountCalculator(double startDiscount, double stepDiscount) {
        this.startDiscount = startDiscount;
        this.stepDiscount = stepDiscount;
    }

    public double calculateDiscount(int orderNumber) {
        double discount = startDiscount - (orderNumber - 1) * stepDiscount;
        return Math.max(0.0, discount);
    }
}