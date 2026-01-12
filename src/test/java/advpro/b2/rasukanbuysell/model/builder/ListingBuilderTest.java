// ListingBuilderTest.java
package advpro.b2.rasukanbuysell.model.Builder;

import advpro.b2.rasukanbuysell.model.Listing;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ListingBuilderTest {

    @Test
    public void testListingBuilder() {
        String listingId = UUID.randomUUID().toString();
        String name = "testName";
        int price = 100;
        int stock = 10;
        String seller = "testSeller";
        ListingBuilder listingBuilder = new ListingBuilder();

        Listing listing = listingBuilder.addId(UUID.fromString(listingId))
                .addName(name)
                .addPrice(price)
                .addStock(stock)
                .addSeller(seller)
                .build();

        assertEquals(listingId, listing.getListingId(), "Listing ID should match the one set in the builder");
        assertEquals(name, listing.getName(), "Name should match the one set in the builder");
        assertEquals(price, listing.getPrice(), "Price should match the one set in the builder");
        assertEquals(stock, listing.getStock(), "Stock should match the one set in the builder");
        assertEquals(seller, listing.getSeller(), "Seller should match the one set in the builder");
    }

    @Test
    public void testSetCurrent() {
        // Arrange
        ListingBuilder builder = new ListingBuilder();
        Listing listing = new Listing();
        listing.setName("Test Listing");

        // Act
        builder.setCurrent(listing);

        // Assert
        assertEquals("Test Listing", builder.build().getName());
    }

    @Test
    public void testAddId() {
        // Arrange
        ListingBuilder builder = new ListingBuilder();
        UUID id = UUID.randomUUID();

        // Act
        builder.addId(id);

        // Assert
        assertEquals(id.toString(), builder.build().getListingId());
    }

}
