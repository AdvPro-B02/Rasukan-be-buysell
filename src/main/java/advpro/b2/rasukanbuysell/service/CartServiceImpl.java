package advpro.b2.rasukanbuysell.service;

import java.util.List;
import java.util.Optional;

import advpro.b2.rasukanbuysell.model.ListingtoCart;
import advpro.b2.rasukanbuysell.repository.ListingtoCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import advpro.b2.rasukanbuysell.model.Cart;
import advpro.b2.rasukanbuysell.model.Listing;
//import advpro.b2.rasukanbuysell.model.User;
import advpro.b2.rasukanbuysell.repository.CartRepository;
import advpro.b2.rasukanbuysell.repository.ListingRepository;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ListingRepository listingRepository;

    @Autowired
    private ListingtoCartRepository listingToCartRepository;

    @Override
    public Cart createCart(String user) {
        // Check if a Cart with the given UUID already exists
        Optional<Cart> existingCart = cartRepository.findById(user);

        // If the Cart does not exist, create a new one
        if (existingCart.isEmpty()) {
            return cartRepository.save(new Cart(user));
        }
        // If the Cart already exists, return null
        return null;
    }


    @Override
    public Optional<Cart> getCart(String user) {
        return Optional.ofNullable(cartRepository.findByOwnerId(user));
    }


    @Override
    public ListingtoCart addToCart(String user, String listingUUID) {
        // Get the Cart of the user
        Cart cart = cartRepository.findByOwnerId(user);
        Optional<Listing> listingOpt = listingRepository.findByListingId(listingUUID);

        if (cart != null) {
            if (listingOpt.isPresent()) {
                Listing listing = listingOpt.get();

                // Check if the listing is already in the cart
                List<Listing> cartListings = listingToCartRepository.getAllListingInCart(cart);
                for (Listing cartListing : cartListings) {
                    if (cartListing.equals(listing)) {
                        // The listing is already in the cart, so we don't add it again
                        return null;
                    }
                }

                // Create a new ListingtoCart
                ListingtoCart listingToCart = new ListingtoCart();
                listingToCart.setListing(listing);
                listingToCart.setCart(cart);
                // listingToCart.setUser(user);
                listingToCart.setQuantity(1); // Set the quantity as needed

                // Save the ListingtoCart to the repository
                listingToCartRepository.save(listingToCart);

                return listingToCart;
            }
        }
        return null;
    }


    @Override
    public Listing removeFromCart(String user, String listingId) {
        // Get the Cart of the user
        Optional<Cart> cartOpt = getCart(user);
        Optional<Listing> listingOpt = listingRepository.findByListingId(listingId);

        if (cartOpt.isPresent()) {
            Cart cart = cartOpt.get();

            if (listingOpt.isPresent()) {
                Listing listing = listingOpt.get();
                // Find the ListingtoCart that maps the Cart and the Listing
                Optional<ListingtoCart> listingToCartOpt = Optional.ofNullable(listingToCartRepository.findListingInCart(cart, listing));

                // Remove the ListingtoCart from the repository
                listingToCartOpt.ifPresent(listingtoCart -> listingToCartRepository.delete(listingtoCart));
            }
        }

        return listingOpt.orElse(null);
    }

}
