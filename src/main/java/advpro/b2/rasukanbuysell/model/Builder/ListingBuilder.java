package advpro.b2.rasukanbuysell.model.Builder;

import advpro.b2.rasukanbuysell.model.Listing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ListingBuilder {
    private Listing currentListing;
    public ListingBuilder(){
        this.reset();
    }

    public ListingBuilder reset(){
        currentListing = new Listing();
        return this;
    }

    public ListingBuilder addName(String name){
        currentListing.setName(name);
        return this;
    }

    public ListingBuilder addStock(int quantity){
        currentListing.setStock(quantity);
        return this;
    }

    public ListingBuilder addId(){
        currentListing.setListingId(String.valueOf(UUID.randomUUID()));
        return this;
    }

    public ListingBuilder addPrice(int price) {
        currentListing.setPrice(price);
        return this;
    }

    public ListingBuilder addId(UUID id){
        currentListing.setListingId(id.toString());
        return this;
    }

    public ListingBuilder setCurrent(Listing listing){
        currentListing = listing;
        return this;
    }

    public ListingBuilder addSeller(String sellerId){
        currentListing.setSeller(sellerId);
        return this;
    }

    public Listing build(){
        return currentListing;
    }
}