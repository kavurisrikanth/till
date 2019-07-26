public class XForYRule extends PricingRules {
    private int x, y;

    public XForYRule(int x, int y, PizzaSize on) {
        this.x = x;
        this.y = y;
        this.onSize = on;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
