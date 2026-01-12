//package advpro.b2.rasukanbuysell.model.Builder;
//
//import advpro.b2.rasukanbuysell.model.Cart;
//import advpro.b2.rasukanbuysell.model.Listing;
//import advpro.b2.rasukanbuysell.model.ListingtoCart;
//import advpro.b2.rasukanbuysell.repository.CartRepository;
//import lombok.Setter;
//
//@Setter
//public class ListingtoCartBuilder {
//    CartRepository cartRepository;
//
//    ListingtoCart listingToCart;
//    public ListingtoCartBuilder(){
//        listingToCart = new ListingtoCart();
//    }
//    public ListingtoCartBuilder setQuantity(int quantity){
//        listingToCart.setQuantity(quantity);
//        return this;
//    }
//
//    public ListingtoCartBuilder setListing(Listing listing){
//        listingToCart.setListing(listing);
//        return this;
//    }
//
//    public ListingtoCartBuilder setCart(String ownerId){
//        listingToCart.setCart(cartRepository.findByOwnerId(ownerId));
//        return this;
//    }
//
//    public ListingtoCart build(){
//        return listingToCart;
//    }
//}
//
