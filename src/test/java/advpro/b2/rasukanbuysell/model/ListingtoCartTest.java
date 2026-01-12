package advpro.b2.rasukanbuysell.model;

import advpro.b2.rasukanbuysell.model.Cart;
import advpro.b2.rasukanbuysell.model.Listing;
import advpro.b2.rasukanbuysell.model.ListingtoCart;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ListingtoCartTest {

    @Test
    public void testListingToCartConstructor() {
        Listing listing = new Listing();
        Cart cart = new Cart();
        int quantity = 5;
        ListingtoCart listingToCart = new ListingtoCart();
        listingToCart.setListing(listing);
        listingToCart.setCart(cart);
        listingToCart.setQuantity(quantity);
        assertEquals(listing, listingToCart.getListing());
        assertEquals(cart, listingToCart.getCart());
        assertEquals(quantity, listingToCart.getQuantity());
    }

    @Test
    public void testListingToCartToString() {
        ListingtoCart listingToCart = new ListingtoCart();
        String expectedString = "{\"listingInCartId\":null,\"listing\":null,\"cart\":null,\"quantity\":0}";
        assertEquals(expectedString, listingToCart.toString());
    }

    @Test
    public void testToString_JsonProcessingException() {
        // Arrange
        ObjectMapper mockMapper = mock(ObjectMapper.class);
        ListingtoCart listingtoCart = new ListingtoCart();
        listingtoCart.setListing(new Listing());
        listingtoCart.setCart(new Cart());
        listingtoCart.setQuantity(1);

        try {
            when(mockMapper.writeValueAsString(any())).thenThrow(JsonProcessingException.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
