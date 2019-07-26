public class FlatDiscountRule extends PricingRules {
    private double newPrice;

    public FlatDiscountRule(double newPrice, PizzaSize on) {
        this.newPrice = newPrice;
        this.onSize = on;
    }

    public double getNewPrice() {
        return newPrice;
    }
}
