import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class User {
    private static class Cart {
        private Order order;

        void addOrder(Order order) {
            this.order = order;
        }

        double checkout(ArrayList<PricingRules> rules) {
            calculateDiscount(rules);
            return (order.getTotalPrice() - order.getDiscount());
        }

        private void calculateDiscount(ArrayList<PricingRules> rules) {
            if (rules == null || rules.isEmpty()) return;
            for (PricingRules rule: rules) {
                if (!order.getCounts().containsKey(rule.onSize)) continue;

                if (rule instanceof XForYRule) {
                    int numPizzas = order.getCounts().get(rule.onSize);
                    int X = ((XForYRule) rule).getX();
                    int x = numPizzas/X;
                    if (x == 0) continue;
                    int Y = ((XForYRule) rule).getY();
                    order.addToDiscount(x * (X - Y) * PizzaDetails.getPriceBySize(rule.onSize));
                } else if (rule instanceof FlatDiscountRule) {
                    int numPizzas = order.getCounts().get(rule.onSize);
                    order.addToDiscount(numPizzas * (PizzaDetails.getPriceBySize(rule.onSize) - ((FlatDiscountRule) rule).getNewPrice()));
                }
            }
        }
    }

    private static int id = 0;

    private int userId;
    private String name;
    private ArrayList<Order> orders;
    private ArrayList<PricingRules> rules;
    private Cart myCart;

    public User(String name) {
        userId = id++;
        this.name = name;
        myCart = new Cart();
        orders = new ArrayList<>();
        rules = new ArrayList<>();
    }

    public void newRule(PricingRules rule) {
        if (rule == null) return;
        rules.add(rule);
    }

    public void newRules(List<PricingRules> rules) {
        for (PricingRules rule: rules)
            newRule(rule);
    }

    public void placeOrder(List<Pizza> items) {
        Order order = new Order();
        items.forEach(order::add);
        myCart.addOrder(order);
        Double finalPrice = myCart.checkout(rules);
        System.out.println("Total: $" + String.format(finalPrice.toString(), new DecimalFormat("#.##")));
        orders.add(order);
    }

    public String getName() {
        return name;
    }

    public ArrayList<PricingRules> getRules() {
        return rules;
    }

    public Order getMostRecentOrder() {
        if (orders == null || orders.isEmpty()) return null;
        return orders.get(orders.size() - 1);
    }
}
