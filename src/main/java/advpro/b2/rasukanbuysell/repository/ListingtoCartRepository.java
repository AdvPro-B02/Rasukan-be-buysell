package advpro.b2.rasukanbuysell.repository;

import advpro.b2.rasukanbuysell.model.Cart;
import advpro.b2.rasukanbuysell.model.Listing;
import advpro.b2.rasukanbuysell.model.ListingtoCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ListingtoCartRepository extends JpaRepository<ListingtoCart, String> {


    @Query("SELECT l FROM ListingtoCart l WHERE l.cart = :cart AND l.listing = :listing")
    <Optional>
    ListingtoCart findListingInCart(@Param("cart") Cart cart, @Param("listing") Listing listing);

    @Query("SELECT l.listing FROM ListingtoCart l WHERE l.cart = :cart")
    List<Listing> getAllListingInCart(@Param("cart") Cart cart);

    @Query("SELECT l.listing FROM ListingtoCart l WHERE l.cart = :cart AND l.listing.seller = :sellerId")
    List<Listing> findAllListingsInCartBySeller(@Param("cart") Cart cart, @Param("sellerId") String sellerId);

}

