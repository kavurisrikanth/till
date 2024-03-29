import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DriverTest {
    private Driver driver;

    @BeforeEach
    void setup() {
        driver = new Driver();
    }

    @Test
    void testAddUser() {
        String name = "Facebook";
        driver.addUser(name);
        assertNotNull(driver.getUserByName(name));
        assertNotNull(driver.getUserByName(name).getRules());
    }

    @Test
    void testAddUserWithRules() {
        String name = "Facebook";
        List<PricingRules> rules = new ArrayList<>();
        rules.add(new FlatDiscountRule(200, PizzaSize.MEDIUM));
        rules.add(new XForYRule(3, 2, PizzaSize.SMALL));
        driver.addUser(name, rules);
        assertNotNull(driver.getUserByName(name));

        List<PricingRules> addedRules = driver.getUserByName(name).getRules();
        assertNotNull(addedRules);
        assertNotEquals(addedRules.size(), 0);
        assertEquals(addedRules.size(), 2);

        for (PricingRules rule: addedRules) {
            if (rule instanceof XForYRule) {
                assertEquals(((XForYRule) rule).getX(), 3);
                assertEquals(((XForYRule) rule).getY(), 2);
            } else {
                assertEquals(((FlatDiscountRule) rule).getNewPrice(), 200);
            }
        }
    }

    @Test
    void testTotalWithoutRules() {
        String name = "Facebook";
        driver.addUser(name);
        User current = driver.getUserByName(name);

        List<Pizza> orderItems = new ArrayList<>();
        orderItems.add(new Pizza(PizzaSize.SMALL));
        orderItems.add(new Pizza(PizzaSize.MEDIUM));
        orderItems.add(new Pizza(PizzaSize.LARGE));

        current.placeOrder(orderItems);
        assertEquals(269.99 + 322.99 + 394.99, current.getMostRecentOrder().getTotalPrice());
        assertEquals(0, current.getMostRecentOrder().getDiscount());
    }

    @Test
    void testInfosysWithRules() {
        String name = "Infosys";
        List<PricingRules> rules = addRules(name);

        driver.addUser(name, rules);
        assertNotNull(driver.getUserByName(name));

        List<Pizza> orderItems = new ArrayList<>();
        orderItems.add(new Pizza(PizzaSize.SMALL));
        orderItems.add(new Pizza(PizzaSize.SMALL));
        orderItems.add(new Pizza(PizzaSize.SMALL));
        orderItems.add(new Pizza(PizzaSize.LARGE));

        driver.placeOrder(name, orderItems);
        driver.total(name);
    }

    @Test
    void testAmazonWithRules() {
        String name = "Amazon";
        List<PricingRules> rules = addRules(name);

        driver.addUser(name, rules);
        assertNotNull(driver.getUserByName(name));

        List<Pizza> orderItems = new ArrayList<>();
        orderItems.add(new Pizza(PizzaSize.MEDIUM));
        orderItems.add(new Pizza(PizzaSize.MEDIUM));
        orderItems.add(new Pizza(PizzaSize.MEDIUM));
        orderItems.add(new Pizza(PizzaSize.LARGE));

        driver.placeOrder(name, orderItems);
        driver.total(name);
    }

    private List<PricingRules> addRules(String name) {
        List<PricingRules> rules = new ArrayList<>();
        if (name.toLowerCase().equals("infosys")) {
            rules.add(new XForYRule(3, 2, PizzaSize.SMALL));
        }

        if (name.toLowerCase().equals("amazon")) {
            rules.add(new FlatDiscountRule(299.99, PizzaSize.LARGE));
        }

        if (name.toLowerCase().equals("facebook")) {
            rules.add(new XForYRule(5, 4, PizzaSize.MEDIUM));
            rules.add(new FlatDiscountRule(389.99, PizzaSize.LARGE));
        }

        return rules;
    }
}
