package advpro.b2.rasukanbuysell.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import advpro.b2.rasukanbuysell.model.Listing;
//import advpro.b2.rasukanbuysell.model.User;
import advpro.b2.rasukanbuysell.repository.ListingRepository;

@Service
public class ListingServiceImpl implements ListingService{

    @Autowired
    private ListingRepository listingRepository;

    @Override
    public Listing createListing(Listing listing){
        Listing newListing = new Listing(listing.getName(), listing.getStock(), listing.getPrice());
        try {
            return listingRepository.save(listing);
        } catch (DataIntegrityViolationException e) {
            return null;
        }
    }

    @Override
    public Optional<Listing> getListing(String listingId) {
        return listingRepository.findById(UUID.fromString(listingId));
    }

    @Override
    public Listing updateListing(Listing listing) {
        if (listingRepository.existsById(UUID.fromString(listing.getListingId()))){
            try{
                return listingRepository.save(listing);
            } catch (DataIntegrityViolationException e){
                return null;
            }

        } else {
            return null;
        }
    }

    @Override
    public void deleteListing(String listingId) {
        listingRepository.deleteById(UUID.fromString(listingId));
    }

    @Override
    public List<Listing> getAllListings() {
        return listingRepository.findAll();
//        Iterator<Listing> listingIterator = listingRepository.findAll();
//        List<Listing> allListings = new ArrayList<>();
//        listingIterator.forEachRemaining(allListings::add);
//        return allListings;
    }

//    @Override
//    public User getSeller(String userId, String listingId) {
//        User seller = listingRepository.findSeller(userId, listingId);
//        return seller;
//    }
}
