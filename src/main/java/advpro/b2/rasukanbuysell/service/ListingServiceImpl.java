package advpro.b2.rasukanbuysell.service;

import java.util.*;

import advpro.b2.rasukanbuysell.model.Builder.ListingBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.support.NullValue;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import advpro.b2.rasukanbuysell.model.Listing;
//import advpro.b2.rasukanbuysell.model.User;
import advpro.b2.rasukanbuysell.repository.ListingRepository;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.client.RestTemplate;

@Service
public class ListingServiceImpl implements ListingService{

    @Autowired
    private ListingRepository listingRepository;

    @Autowired
    private ListingBuilder listingBuilder;

//    private String authUrl = "http://localhost:8080/";

    // TODO: buat test masih perlu bikin mock but restTemplate
    private RestTemplate restTemplate;

    @Override
    public Listing createListing(Listing listing, String token){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", token);
        HttpEntity<String> entity = new HttpEntity<>("body", headers);

        // TODO: uncomment habis ada authUrl
//        ResponseEntity<String > owner = restTemplate.exchange(authUrl+"user/get-username", HttpMethod.GET,entity ,String.class);


        try {
            Listing newListing = new Listing(listing.getName(), listing.getStock(), listing.getPrice(), listing.getSeller());
            return listingRepository.save(newListing);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Optional<Listing> getListing(String listingId) {
        return listingRepository.findById(listingId);
    }


    @Override
    public Listing updateListing(Listing listing) {
        Listing existingListing = listingRepository.findById(listing.getListingId()).orElse(null);

        if (existingListing != null) {
            // dummy data
            existingListing.setName(listing.getName());
            existingListing.setPrice(listing.getPrice());
            existingListing.setStock(listing.getStock());

            return listingRepository.save(existingListing);
        } else {
            return null;
        }
    }

    @Override
    public void deleteListing(String listingId) {
        listingRepository.deleteById(listingId);
    }

    @Override
    public List<Listing> getAllListings() {
        return listingRepository.findAll();
    }

    @Override
    public Listing buildListing(Listing listing, String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", token);
        HttpEntity<String> entity = new HttpEntity<>("body", headers);

        // TODO: uncomment habis ada authUrl
//        ResponseEntity<String > owner = restTemplate.exchange(authUrl+"user/get-username", HttpMethod.GET,entity ,String.class);
        Listing newListing = listingBuilder
                .reset()
                .addName(listing.getName())
                .addStock(listing.getStock())
                .addId()
                .addPrice(listing.getPrice())
                .addSeller(listing.getSeller())
                .build();

        return newListing;
    }

}
