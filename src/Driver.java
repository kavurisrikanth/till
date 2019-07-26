import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Driver {
    private List<User> users;

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

    public User getUserByName(String name) {
        if (!containsUser(name)) return null;
        return users.stream().filter(u -> u.getName().equals(name)).findFirst().get();
    }

    private boolean checkContains(String name) {
        if (users == null) users = new ArrayList<>();
        return containsUser(name);
    }

    private boolean containsUser(String name) {
        return users.stream().anyMatch(u -> u.getName().equals(name));
    }
}
