package advpro.b2.rasukanbuysell.model;

import java.util.UUID;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="Listing_to_Cart")
@Getter
public class ListingtoCart {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String listingInCartId;

    @Setter
    @ManyToOne
    @JoinColumn(name="listing_id", referencedColumnName = "listingId")
    private Listing listing;

    @Setter
    @ManyToOne
    @JoinColumn(name = "owner_id", referencedColumnName = "ownerId")
    private Cart cart;

    @Setter
    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Override
    public String toString() {
        ObjectMapper om = new ObjectMapper();
        try {
            return om.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }


}
