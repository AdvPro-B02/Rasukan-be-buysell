package advpro.b2.rasukanbuysell.service;

import advpro.b2.rasukanbuysell.model.Cart;
import advpro.b2.rasukanbuysell.model.Listing;
import advpro.b2.rasukanbuysell.model.ListingtoCart;
import advpro.b2.rasukanbuysell.repository.CartRepository;
import advpro.b2.rasukanbuysell.repository.ListingRepository;
import advpro.b2.rasukanbuysell.repository.ListingtoCartRepository;
import advpro.b2.rasukanbuysell.service.CartServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CartServiceTests {

    @Mock
    private CartRepository cartRepository;

    @Mock
    private ListingRepository listingRepository;

    @Mock
    private ListingtoCartRepository listingToCartRepository;

    @InjectMocks
    private CartServiceImpl cartService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void createCartTest() {
        String userId = "user123";
        Cart cart = new Cart(userId);

        when(cartRepository.findById(userId)).thenReturn(Optional.empty());
        when(cartRepository.save(any(Cart.class))).thenReturn(cart);

        Cart newCart = cartService.createCart(userId);

        assertNotNull(newCart);
        assertEquals(userId, newCart.getOwnerId());
        verify(cartRepository, times(1)).findById(userId);
        verify(cartRepository, times(1)).save(any(Cart.class));
    }

    @Test
    void getCartTest() {
        String userId = "user123";
        Cart cart = new Cart(userId);

        when(cartRepository.findByOwnerId(userId)).thenReturn(cart);

        Optional<Cart> retrievedCart = cartService.getCart(userId);

        assertTrue(retrievedCart.isPresent());
        assertEquals(cart, retrievedCart.get());
        verify(cartRepository, times(1)).findByOwnerId(userId);
    }

    @Test
    void addToCartTest() {
        String userId = "user123";
        String listingId = "listing123";
        Cart cart = new Cart(userId);
        Listing listing = new Listing("Test Listing", 10, 100, userId);

        when(cartRepository.findByOwnerId(userId)).thenReturn(cart);
        when(listingRepository.findByListingId(listingId)).thenReturn(Optional.of(listing));
        when(listingToCartRepository.getAllListingInCart(cart)).thenReturn(new ArrayList<>());
        when(listingToCartRepository.save(any(ListingtoCart.class))).thenReturn(new ListingtoCart());

        ListingtoCart addedListing = cartService.addToCart(userId, listingId);

        assertNotNull(addedListing);
        assertEquals(listing, addedListing.getListing());
        assertEquals(cart, addedListing.getCart());
        assertEquals(1, addedListing.getQuantity());
        verify(cartRepository, times(1)).findByOwnerId(userId);
        verify(listingRepository, times(1)).findByListingId(listingId);
        verify(listingToCartRepository, times(1)).save(any(ListingtoCart.class));
    }

    @Test
    void removeFromCartTest() {
        String userId = "user123";
        String listingId = "listing123";
        Cart cart = new Cart(userId);
        Listing listing = new Listing("Test Listing", 10, 100, userId);
        ListingtoCart listingtoCart = new ListingtoCart();
        listingtoCart.setCart(cart);
        listingtoCart.setListing(listing);

        when(cartRepository.findByOwnerId(userId)).thenReturn(cart);
        when(listingRepository.findByListingId(listingId)).thenReturn(Optional.of(listing));
        when(listingToCartRepository.findListingInCart(cart, listing)).thenReturn(listingtoCart);

        Listing removedListing = cartService.removeFromCart(userId, listingId);

        assertNotNull(removedListing);
        assertEquals(listing, removedListing);
        verify(listingToCartRepository, times(1)).delete(listingtoCart);
    }

    @Test
    void createCart_ExistingCartTest() {
        String userId = "user123";
        Cart existingCart = new Cart(userId);

        when(cartRepository.findById(userId)).thenReturn(Optional.of(existingCart));

        Cart newCart = cartService.createCart(userId);

        assertNull(newCart);
        verify(cartRepository, times(1)).findById(userId);
        verify(cartRepository, times(0)).save(any(Cart.class));
    }

    @Test
    void addToCart_NoCartTest() {
        String userId = "user123";
        String listingId = "listing123";

        when(cartRepository.findByOwnerId(userId)).thenReturn(null);

        ListingtoCart addedListing = cartService.addToCart(userId, listingId);

        assertNull(addedListing);
        verify(cartRepository, times(1)).findByOwnerId(userId);
    }

    @Test
    void addToCart_NoListingTest() {
        String userId = "user123";
        String listingId = "listing123";
        Cart cart = new Cart(userId);

        when(cartRepository.findByOwnerId(userId)).thenReturn(cart);
        when(listingRepository.findByListingId(listingId)).thenReturn(Optional.empty());

        ListingtoCart addedListing = cartService.addToCart(userId, listingId);

        assertNull(addedListing);
        verify(cartRepository, times(1)).findByOwnerId(userId);
        verify(listingRepository, times(1)).findByListingId(listingId);
    }

    @Test
    void addToCart_ListingInCartTest() {
        String userId = "user123";
        String listingId = "listing123";
        Cart cart = new Cart(userId);
        Listing listing = new Listing("Test Listing", 10, 100, userId);
        List<Listing> cartListings = new ArrayList<>();
        cartListings.add(listing);

        when(cartRepository.findByOwnerId(userId)).thenReturn(cart);
        when(listingRepository.findByListingId(listingId)).thenReturn(Optional.of(listing));
        when(listingToCartRepository.getAllListingInCart(cart)).thenReturn(cartListings);

        ListingtoCart addedListing = cartService.addToCart(userId, listingId);

        assertNull(addedListing);
        verify(cartRepository, times(1)).findByOwnerId(userId);
        verify(listingRepository, times(1)).findByListingId(listingId);
    }

    @Test
    void removeFromCart_NoCartTest() {
        String userId = "user123";
        String listingId = "listing123";

        when(cartRepository.findByOwnerId(userId)).thenReturn(null);

        Listing removedListing = cartService.removeFromCart(userId, listingId);

        assertNull(removedListing);
        verify(cartRepository, times(1)).findByOwnerId(userId);
    }

}
