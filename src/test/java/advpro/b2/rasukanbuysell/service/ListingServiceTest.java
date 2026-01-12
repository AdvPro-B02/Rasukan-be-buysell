package advpro.b2.rasukanbuysell.service;

import advpro.b2.rasukanbuysell.model.Builder.ListingBuilder;
import advpro.b2.rasukanbuysell.model.Listing;
import advpro.b2.rasukanbuysell.repository.ListingRepository;
import advpro.b2.rasukanbuysell.service.ListingServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ListingServiceTests {

    @Mock
    private ListingRepository listingRepository;

    @InjectMocks
    private ListingServiceImpl listingService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void createListingTest() {
        Listing listing = new Listing("Test", 10, 100, "seller123");

        when(listingRepository.save(any(Listing.class))).thenReturn(listing);

        Listing newListing = listingService.createListing(listing, "token123");

        assertNotNull(newListing);
        assertEquals(listing.getName(), newListing.getName());
        assertEquals(listing.getStock(), newListing.getStock());
        assertEquals(listing.getPrice(), newListing.getPrice());
        assertEquals(listing.getSeller(), newListing.getSeller());
        verify(listingRepository, times(1)).save(any(Listing.class));
    }

    @Test
    void getListingTest() {
        String listingId = "listing123";
        Listing listing = new Listing("Test Listing", 10, 100, "seller123");

        when(listingRepository.findById(listingId)).thenReturn(Optional.of(listing));

        Optional<Listing> retrievedListing = listingService.getListing(listingId);

        assertTrue(retrievedListing.isPresent());
        assertEquals(listing, retrievedListing.get());
        verify(listingRepository, times(1)).findById(listingId);
    }

    @Test
    void updateListingTest() {
        Listing listing = new Listing("Test Listing", 10, 100, "seller123");

        when(listingRepository.findById(listing.getListingId())).thenReturn(Optional.of(listing));
        when(listingRepository.save(any(Listing.class))).thenReturn(listing);

        Listing updatedListing = listingService.updateListing(listing);

        assertNotNull(updatedListing);
        assertEquals(listing, updatedListing);
        verify(listingRepository, times(1)).findById(listing.getListingId());
        verify(listingRepository, times(1)).save(any(Listing.class));
    }

    @Test
    void deleteListingTest() {
        String listingId = "listing123";

        listingService.deleteListing(listingId);

        verify(listingRepository, times(1)).deleteById(listingId);
    }


    @Test
    void getAllListingsTest() {
        List<Listing> listingList = List.of(
                new Listing("Listing1", 10, 100, "seller123"),
                new Listing("Listing2", 20, 200, "seller456"),
                new Listing("Listing3", 30, 300, "seller789")
        );

        when(listingRepository.findAll()).thenReturn(listingList);

        List<Listing> retrievedListings = listingService.getAllListings();

        assertNotNull(retrievedListings);
        assertEquals(listingList.size(), retrievedListings.size());
        assertEquals(listingList, retrievedListings);
        verify(listingRepository, times(1)).findAll();
    }

    @Test
    void createListing_ExceptionTest() {
        Listing listing = new Listing("Test", 10, 100, "seller123");

        when(listingRepository.save(any(Listing.class))).thenThrow(new DataIntegrityViolationException("Exception"));

        Listing newListing = listingService.createListing(listing, "token123");

        assertNull(newListing);
        verify(listingRepository, times(1)).save(any(Listing.class));
    }

    @Test
    void updateListing_NoExistingListingTest() {
        Listing listing = new Listing("Test Listing", 10, 100, "seller123");

        when(listingRepository.findById(listing.getListingId())).thenReturn(Optional.empty());

        Listing updatedListing = listingService.updateListing(listing);

        assertNull(updatedListing);
        verify(listingRepository, times(1)).findById(listing.getListingId());
        verify(listingRepository, times(0)).save(any(Listing.class));
    }

}
