public class Pizza {
    private PizzaSize size;
    private double price;
    private String description;

    public Pizza(PizzaSize size) {
        this.size = size;
        this.price = PizzaDetails.getPriceBySize(size);
        this.description = PizzaDetails.getDescriptionBySize(size);
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

    public void setPrice(double price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
