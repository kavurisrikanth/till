public class Pizza {
    private enum PizzaSize {
        SMALL,
        MEDIUM,
        LARGE
    }

    private PizzaSize size;
    private double price;
    private String description;

    public Pizza(PizzaSize size, String description, double price) {
        this.size = size;
        this.description = description;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public PizzaSize getSize() {
        return size;
    }

    public String getDescription() {
        return description;
    }
}
