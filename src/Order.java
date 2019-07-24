import java.util.ArrayList;
import java.util.List;

public class Order {
    private List<Pizza> items;
    private double totalPrice, discount;

    public Order() {
        items = new ArrayList<>();
        totalPrice = 0;
        discount = 0;
    }

    public Order(Pizza item) {
        this();
        items.add(item);
    }

    public void add(Pizza item) {
        items.add(item);
        totalPrice += item.getPrice();
    }

    public void checkout() {
        System.out.println("Total: " + totalPrice);
    }
}
