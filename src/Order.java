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

    public void addToPrice(double some) {
        totalPrice += some;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void addToDiscount(double some) {
        discount += some;
    }

    public double getDiscount() {
        return discount;
    }

    public List<Pizza> getItems() {
        return items;
    }
}
