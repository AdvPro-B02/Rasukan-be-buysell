package advpro.b2.rasukanbuysell.repository;

import org.springframework.stereotype.Repository;
import advpro.b2.rasukanbuysell.model.Listing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ListingRepository extends JpaRepository<Listing, String> {

    Optional<Listing> findByListingId(@Param("listingId") String listingId);

    @Query("SELECT l FROM Listing l WHERE l.seller = :sellerId")
    List<Listing> findAllListingsBySeller(@Param("sellerId") String sellerId);


}