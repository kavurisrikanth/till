import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Order {
    private List<Pizza> items;
    private double totalPrice, discount;
    private HashMap<PizzaSize, Integer> counts;

    public Order() {
        items = new ArrayList<>();
        totalPrice = 0;
        discount = 0;
        counts = new HashMap<>();
    }

    public Order(Pizza item) {
        this();
        items.add(item);
    }

    public void add(Pizza item) {
        items.add(item);
        totalPrice += item.getPrice();
        int existingCount = counts.getOrDefault(item.getSize(), 0);
        counts.put(item.getSize(), existingCount + 1);
    }

    public void addToPrice(double some) {
        totalPrice += some;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public HashMap<PizzaSize, Integer> getCounts() {
        return counts;
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
