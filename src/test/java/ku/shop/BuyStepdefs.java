package ku.shop;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BuyStepdefs {

    private ProductCatalog catalog;
    private Order order;

    @Before
    public void setup() {
        catalog = new ProductCatalog();
        order = new Order();
    }

    @Given("a product {string} with price {float} exists")
    public void a_product_with_price_exists(String name, double price) {
        catalog.addProduct(name, price);
    }

    @When("I buy {string} with quantity {int}")
    public void i_buy_with_quantity(String name, int quantity) throws NotEnoughException {
        Product prod = catalog.getProduct(name);
        if (prod.getQuantity() >= quantity) {
            order.addItem(prod, quantity);
        }else {
            assertThrows(NotEnoughException.class, () -> order.addItem(prod, quantity));
        }
    }

    @When("I buy {string} more than in stock")
    public void i_buy_more(String name) throws NotEnoughException {
        Product prod = catalog.getProduct(name);
        int quantity = prod.getQuantity() + 1;
        assertThrows(NotEnoughException.class, () -> order.addItem(prod,quantity));
    }

    @Then("total should be {float}")
    public void total_should_be(double total) {
        assertEquals(total, order.getTotal());
    }
}