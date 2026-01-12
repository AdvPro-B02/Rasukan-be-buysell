package advpro.b2.rasukanbuysell.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.UUID;


@Entity
@Table(name="listing")
@Getter @Setter
public class Listing {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String listingId;

    @Column(name="listingName", nullable = false)
    private String name;

    @Column(name="listingPrice", nullable = false)
    private int price;

    @Column(name="stock", nullable = false)
    private int stock;

    @Column(name="seller_id", nullable = false)
    private String seller;
    
    @Column(name="order_counter", nullable = false)
    private int orderCounter;

    public Listing(String name, int stock, int price, String seller){
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.seller = seller; // masih perlu dibenerin
        this.orderCounter = 0;
    }

    public Listing() {

    }

    public Listing(String listingId, String name, int price, int stock, String uuid) {
        this.listingId = listingId;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.seller = uuid; // masih perlu dibenerin
        this.orderCounter = 0;
    }

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

