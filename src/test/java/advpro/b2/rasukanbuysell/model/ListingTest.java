package advpro.b2.rasukanbuysell.model;

import advpro.b2.rasukanbuysell.model.Listing;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;

public class ListingTest {

    @Test
    public void testListingConstructor() {
        String name = "Test Listing";
        int stock = 10;
        int price = 100;
        String seller = "seller123";
        Listing listing = new Listing(name, stock, price, seller);
        assertEquals(name, listing.getName());
        assertEquals(stock, listing.getStock());
        assertEquals(price, listing.getPrice());
        assertEquals(seller, listing.getSeller());
        assertEquals(0, listing.getOrderCounter());
    }

    @Test
    public void testListingToString() {
        Listing listing = new Listing("Test Listing", 10, 100, "seller123");
        String expectedString = "{\"listingId\":null,\"name\":\"Test Listing\",\"price\":100,\"stock\":10,\"seller\":\"seller123\",\"orderCounter\":0}";
        assertEquals(expectedString, listing.toString());
    }

    @Test
    public void testListingToString_Exception() throws JsonProcessingException {
        Listing listing = new Listing("Test Listing", 10, 100, "seller123");
        ObjectMapper mockMapper = Mockito.spy(new ObjectMapper());

        doThrow(JsonProcessingException.class).when(mockMapper).writeValueAsString(Mockito.any());

    }

    @Test
    public void testListingConstructorWithId() {
        String listingId = "listing123";
        String name = "Test Listing";
        int stock = 10;
        int price = 100;
        String seller = "seller123";
        Listing listing = new Listing(listingId, name, price, stock, seller);
        assertEquals(listingId, listing.getListingId());
        assertEquals(name, listing.getName());
        assertEquals(stock, listing.getStock());
        assertEquals(price, listing.getPrice());
        assertEquals(seller, listing.getSeller());
        assertEquals(0, listing.getOrderCounter());
    }
}
