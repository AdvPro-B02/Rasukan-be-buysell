package advpro.b2.rasukanbuysell.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class SellerControllerTest {

    @InjectMocks
    private SellerController sellerController;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testCreateListing() {
        ResponseEntity<String> response = sellerController.createListing();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Hello World!, this is halaman untuk membuat listing", response.getBody());
    }

    @Test
    void testGetAllListings() {
        String userId = "123";
        ResponseEntity<String> response = sellerController.getAllListings(userId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Hello World!, this is halaman untuk melihat semua listing yang dibuat user " + userId, response.getBody());
    }

    @Test
    void testGetListingById() {
        String listingId = "456";
        ResponseEntity<String> response = sellerController.getListingById(listingId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Hello World!, this is halaman untuk melihat detail listing", response.getBody());
    }

    @Test
    void testUpdateListing() {
        String listingId = "789";
        ResponseEntity<String> response = sellerController.updateListing(listingId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Hello World!, this is halaman untuk mengubah listing ber id-" + listingId, response.getBody());
    }

    @Test
    void testDeleteListing() {
        String listingId = "1011";
        ResponseEntity<String> response = sellerController.deleteListing(listingId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Hello World!, this is halaman untuk menghapus listing ber id-" + listingId, response.getBody());
    }

    @Test
    void testGetAllOrders() {
        String userId = "123";
        ResponseEntity<String> response = sellerController.getAllOrders(userId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Hello World!, this is halaman untuk melihat order dari user ber id-" + userId, response.getBody());
    }

    @Test
    void testUpdateOrderStatus() {
        String orderId = "1213";
        ResponseEntity<String> response = sellerController.updateOrderStatus(orderId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Hello World!, this is halaman untuk mengubah order ber id-" + orderId, response.getBody());
    }
}
