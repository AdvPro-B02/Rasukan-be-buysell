//package advpro.b2.rasukanbuysell.repository;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.springframework.stereotype.Repository;
//
//import advpro.b2.rasukanbuysell.model.User;
//
//
//// temp doang
//@Repository
//public class UserRepository {
//
//    private static List<User> users = new ArrayList<>();
//
//    public User create(User user) {
//        users.add(user);
//        return user;
//    }
//
//    public User findById(String id) {
//        for (User user : users) {
//            if (user.getUserId().equals(id)) {
//                return user;
//            }
//        }
//        return null;
//    }
//
//    public User updateUser(String userId, User updatedUser) {
//        for (User user : users) {
//            if (user.getUserId().equals(userId)) {
//                user.setName(updatedUser.getName());
//                user.setSaldo(updatedUser.getSaldo());
//                user.setAlamat(updatedUser.getAlamat());
//                user.setCart(updatedUser.getCart());
//                user.setListingsOwned(updatedUser.getListingsOwned());
//                return user;
//            }
//        }
//        return null;
//    }
//}
