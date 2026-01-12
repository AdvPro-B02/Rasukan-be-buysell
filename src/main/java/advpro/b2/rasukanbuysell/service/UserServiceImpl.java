//package advpro.b2.rasukanbuysell.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import advpro.b2.rasukanbuysell.model.User;
//import advpro.b2.rasukanbuysell.repository.UserRepository;
//
//@Service
//public class UserServiceImpl implements UserService {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Override
//    public User createUser(User user) {
//        return userRepository.create(user);
//    }
//
//    @Override
//    public User getUser(String userId) {
//        return userRepository.findById(userId);
//    }
//
//    @Override
//    public User updateUser(String userId, User updatedUser) {
//        return userRepository.updateUser(userId, updatedUser);
//    }
//}
