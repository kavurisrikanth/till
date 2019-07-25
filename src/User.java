import java.util.ArrayList;
import java.util.List;

public class User {
    private class Cart {
        private Order order;

        void addOrder(Order order) {
            this.order = order;
        }

        double checkout(ArrayList<String> rules) {
            rules.forEach(rule -> order.addToDiscount(parseRule(rule)));
            return (order.getTotalPrice() - order.getDiscount());
        }

        private double parseRule(String rule) {
            
        }
    }

    private static int id = 0;

    private int userId;
    private String name;
    private ArrayList<Order> orders;
    private ArrayList<String> rules;
    private Cart myCart;

    public User(String name) {
        userId = id++;
        this.name = name;
        myCart = new Cart();
        orders = new ArrayList<>();
        rules = new ArrayList<>();
    }

    public void placeOrder(List<Pizza> items) {
        Order order = new Order();
        items.forEach(order::add);
        myCart.addOrder(order);
        System.out.println("Total: " + myCart.checkout(rules));
    }
}
