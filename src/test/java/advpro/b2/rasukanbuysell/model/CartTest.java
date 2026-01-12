package advpro.b2.rasukanbuysell.model;

import advpro.b2.rasukanbuysell.model.Cart;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CartTest {

    @Test
    public void testCartConstructor() {
        String ownerId = "123";
        Cart cart = new Cart(ownerId);
        assertEquals(ownerId, cart.getOwnerId());
    }

    @Test
    public void testCartToString() {
        Cart cart = new Cart("123");
        String expectedString = "{\"ownerId\":\"123\"}";
        assertEquals(expectedString, cart.toString());
    }
}
