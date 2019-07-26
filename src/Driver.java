import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Driver {
    private List<User> users;

    /*
        Add users
     */
    public void addUser(String name) {
        if (checkContains(name)) return;
        users.add(new User(name));
    }

    public void addUser(String name, List<PricingRules> rules) {
        if (checkContains(name)) return;

        User newUser = new User(name);
        if (rules != null && !rules.isEmpty())
            newUser.newRules(rules);
        users.add(newUser);
    }

    /*
        Place an Order for a User.
     */
    public void placeOrder(String name, List<Pizza> items) {
        if (name == null || !containsUser(name))
            throw new IllegalArgumentException("User with name \"" + name + "\" does not exist.");
        getUserByName(name).placeOrder(items);
    }

    /*
        Get the most recent total for a User.
     */
    public void total(String name) {
        if (name == null || name.isEmpty()) return;
        if (!containsUser(name)) return;

        User current = getUserByName(name);
        Order mostRecent = current.getMostRecentOrder();
        if (mostRecent == null) {
            System.out.println("No orders found");
            return;
        }

        Double totalPrice = mostRecent.getTotalPrice(),
                discount = mostRecent.getDiscount(),
                payable = totalPrice - discount;

        System.out.println("Total: $" + String.format(totalPrice.toString(), new DecimalFormat("#.##")));
        System.out.println("Discount: $" + String.format(discount.toString(), new DecimalFormat("#.##")));
        System.out.println("Total Payable: $" + String.format(payable.toString(), new DecimalFormat("#.##")));
    }

    /*
        Search for a User by name.
     */
    public User getUserByName(String name) {
        if (!containsUser(name)) return null;
        return users.stream().filter(u -> u.getName().equals(name)).findFirst().get();
    }

    /*
        Check if a User with a given name exists.
     */
    private boolean checkContains(String name) {
        if (users == null) users = new ArrayList<>();
        return containsUser(name);
    }

    private boolean containsUser(String name) {
        return users.stream().anyMatch(u -> u.getName().equals(name));
    }
}
