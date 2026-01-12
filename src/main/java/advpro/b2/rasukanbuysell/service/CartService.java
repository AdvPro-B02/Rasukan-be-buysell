package advpro.b2.rasukanbuysell.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import advpro.b2.rasukanbuysell.model.Cart;
import advpro.b2.rasukanbuysell.model.Listing;
import advpro.b2.rasukanbuysell.model.ListingtoCart;


public interface CartService {

    public Cart createCart(String user);

    public Optional<Cart> getCart(String user);

    public ListingtoCart addToCart(String user, String listingUUID);

    public Listing removeFromCart(String user, String listingId);

}
