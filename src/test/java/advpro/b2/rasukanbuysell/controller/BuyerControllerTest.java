package advpro.b2.rasukanbuysell.controller;

import advpro.b2.rasukanbuysell.model.Cart;
import advpro.b2.rasukanbuysell.model.Listing;
import advpro.b2.rasukanbuysell.model.ListingtoCart;
import advpro.b2.rasukanbuysell.repository.ListingRepository;
import advpro.b2.rasukanbuysell.repository.ListingtoCartRepository;
import advpro.b2.rasukanbuysell.service.CartService;
import advpro.b2.rasukanbuysell.service.ListingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

public class BuyerControllerTest {

    @Mock
    private ListingService listingService;

    @Mock
    private CartService cartService;

    @InjectMocks
    private BuyerController buyerController;

    @Mock
    private ListingtoCartRepository listingtoCartRepository;

    @Mock
    private ListingRepository listingRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getAllListingTest() {
        List<Listing> listingList = List.of(
                new Listing("Listing1", 10, 100, "seller123"),
                new Listing("Listing2", 20, 200, "seller456"),
                new Listing("Listing3", 30, 300, "seller789")
        );

        when(listingService.getAllListings()).thenReturn(listingList);

        ResponseEntity<List<Listing>> response = buyerController.getAllListing();

        assertEquals(listingList, response.getBody());
    }

    @Test
    void getListingTest() {
        String listingId = "listing123";
        Listing listing = new Listing("Test Listing", 10, 100, "seller123");

        when(listingService.getListing(listingId)).thenReturn(Optional.of(listing));

        ResponseEntity<?> response = buyerController.getListing(listingId);

        assertEquals(listing, response.getBody());
    }

    @Test
    void findAllListingsBySellerTest() {
        String sellerId = "seller123";
        List<Listing> listings = List.of(
                new Listing("Listing1", 10, 100, sellerId),
                new Listing("Listing2", 20, 200, sellerId)
        );

        when(listingRepository.findAllListingsBySeller(sellerId)).thenReturn(listings);

        ResponseEntity<List<Listing>> response = buyerController.findAllListingsBySeller(sellerId);

        assertEquals(listings, response.getBody());
    }

    @Test
    void getListingsTest() {
        String userId = "user123";
        Cart cart = new Cart(userId);
        List<Listing> listings = List.of(
                new Listing("Listing1", 10, 100, "seller123"),
                new Listing("Listing2", 20, 200, "seller456")
        );

        when(cartService.getCart(userId)).thenReturn(Optional.of(cart));
        when(listingtoCartRepository.getAllListingInCart(cart)).thenReturn(listings);

//        ResponseEntity<?> response = buyerController.getListings(userId);

//        assertEquals(listings, response.getBody());
    }

    @Test
    void createListingTest() {
        Listing listing = new Listing("Test", 10, 100, "seller123");
        String token = "token123";

        when(listingService.createListing(any(Listing.class), eq(token))).thenReturn(listing);

        ResponseEntity<Object> response = buyerController.createListing(listing);

//        assertEquals(listing, response.getBody());
    }

    @Test
    void updateListingTest() {
        Listing listing = new Listing("Test Listing", 10, 100, "seller123");

        when(listingService.updateListing(any(Listing.class))).thenReturn(listing);

        ResponseEntity<Object> response = buyerController.update(listing.getListingId(), listing);

        assertEquals(listing, response.getBody());
    }

    @Test
    void deleteListingTest() {
        String listingId = "listing123";

        doNothing().when(listingService).deleteListing(listingId);

        ResponseEntity<String> response = buyerController.deleteListing(listingId);

        assertEquals("successfully deleted", response.getBody());
    }

    @Test
    void findAllListingsInCartBySellerTest() {
        String userId = "user123";
        String sellerId = "seller123";
        List<Listing> listings = List.of(
                new Listing("Listing1", 10, 100, sellerId),
                new Listing("Listing2", 20, 200, sellerId)
        );

        when(listingtoCartRepository.findAllListingsInCartBySeller(any(Cart.class), eq(sellerId))).thenReturn(listings);

        ResponseEntity<List<Listing>> response = buyerController.findAllListingsInCartBySeller(userId, sellerId);

//        assertEquals(listings, response.getBody());
    }

    @Test
    void seeCartTest() {
        String userId = "user123";
        Cart cart = new Cart(userId);

        when(cartService.getCart(userId)).thenReturn(Optional.of(cart));

        ResponseEntity<Cart> response = buyerController.seeCart(userId);

        assertEquals(cart, response.getBody());
    }

    @Test
    void addListingToCartTest() {
        String userId = "user123";
        String listingId = "listing123";
        ListingtoCart listingtoCart = new ListingtoCart();

        when(cartService.addToCart(eq(userId), eq(listingId))).thenReturn(listingtoCart);

        ResponseEntity<String> response = buyerController.addListingToCart(userId, listingId);

        assertEquals("Listing added to cart successfully.", response.getBody());
    }

    @Test
    void removeListingFromCartTest() {
        String userId = "user123";
        String listingId = "listing123";
        Listing listing = new Listing("Test Listing", 10, 100, "seller123");

        when(cartService.removeFromCart(eq(userId), eq(listingId))).thenReturn(listing);

        ResponseEntity<String> response = buyerController.removeListingFromCart(userId, listingId);

        assertEquals("Listing removed from cart successfully.", response.getBody());
    }

    @Test
    void getListing_NotFoundTest() {
        String listingId = "listing123";

        when(listingService.getListing(listingId)).thenReturn(Optional.empty());

        ResponseEntity<?> response = buyerController.getListing(listingId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void findAllListingsBySeller_NotFoundTest() {
        String sellerId = "seller123";

        when(listingRepository.findAllListingsBySeller(sellerId)).thenReturn(List.of());

        ResponseEntity<List<Listing>> response = buyerController.findAllListingsBySeller(sellerId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }


    @Test
    void update_NotNullTest() {
        Listing listing = new Listing("Test Listing", 10, 100, "seller123");

        when(listingService.updateListing(any(Listing.class))).thenReturn(listing);

        ResponseEntity<Object> response = buyerController.update(listing.getListingId(), listing);

        assertEquals(listing, response.getBody());
    }

    @Test
    void findAllListingsInCartBySeller_CartPresentTest() {
        String userId = "user123";
        String sellerId = "seller123";
        Cart cart = new Cart(userId);
        List<Listing> listings = List.of(
                new Listing("Listing1", 10, 100, sellerId),
                new Listing("Listing2", 20, 200, sellerId)
        );

        when(cartService.getCart(userId)).thenReturn(Optional.of(cart));
        when(listingtoCartRepository.findAllListingsInCartBySeller(cart, sellerId)).thenReturn(listings);

        ResponseEntity<List<Listing>> response = buyerController.findAllListingsInCartBySeller(userId, sellerId);

        assertEquals(listings, response.getBody());
    }

    @Test
    void addListingToCart_NotNullTest() {
        String userId = "user123";
        String listingId = "listing123";

        when(cartService.addToCart(eq(userId), eq(listingId))).thenReturn(null);

        ResponseEntity<String> response = buyerController.addListingToCart(userId, listingId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void removeListingFromCart_NotNullTest() {
        String userId = "user123";
        String listingId = "listing123";

        when(cartService.removeFromCart(eq(userId), eq(listingId))).thenReturn(null);

        ResponseEntity<String> response = buyerController.removeListingFromCart(userId, listingId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

}
