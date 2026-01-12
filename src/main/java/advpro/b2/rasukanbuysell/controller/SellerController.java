package advpro.b2.rasukanbuysell.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/seller")
public class SellerController {

    @PostMapping("/listing")
    public ResponseEntity<String> createListing() {
        String responseBody = "Hello World!, this is halaman untuk membuat listing";
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

    @GetMapping("/listings/{userId}")
    public ResponseEntity<String> getAllListings(@PathVariable String userId) {
        String responseBody = "Hello World!, this is halaman untuk melihat semua listing yang dibuat user " + userId;
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

    @GetMapping("/listing/{listingId}")
    public ResponseEntity<String> getListingById(@PathVariable String listingId) {
        String responseBody = "Hello World!, this is halaman untuk melihat detail listing";
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

    @PutMapping("/editListing/{listingId}")
    public ResponseEntity<String> updateListing(@PathVariable String listingId) {
        String responseBody = "Hello World!, this is halaman untuk mengubah listing ber id-" + listingId;
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

    @DeleteMapping("/deleteListing/{listingId}")
    public ResponseEntity<String> deleteListing(@PathVariable String listingId) {
        String responseBody = "Hello World!, this is halaman untuk menghapus listing ber id-" + listingId;
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

    @GetMapping("/orders/{userId}")
    public ResponseEntity<String> getAllOrders(@PathVariable String userId) {
        String responseBody = "Hello World!, this is halaman untuk melihat order dari user ber id-" + userId;
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

    @PutMapping("/editOrder/{orderId}")
    public ResponseEntity<String> updateOrderStatus(@PathVariable String orderId) {
        String responseBody = "Hello World!, this is halaman untuk mengubah order ber id-" + orderId;
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }
}
