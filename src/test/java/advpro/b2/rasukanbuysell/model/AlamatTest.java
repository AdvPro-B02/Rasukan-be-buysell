//package advpro.b2.rasukanbuysell.model;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//class AlamatTest {
//    Alamat alamat;
//
//    @BeforeEach
//    void setUp() {
//        alamat = new Alamat();
//        alamat.setAlamatLengkap("Jl. Merdeka No. 123");
//        alamat.setKota("Jakarta");
//        alamat.setProvinsi("DKI Jakarta");
//        alamat.setNegara("Indonesia");
//
//        List<User> users = new ArrayList<>();
//        User user = new User();
//        user.setUserId("123");
//        user.setName("Test User");
//        users.add(user);
//
//        alamat.setUsers(users);
//    }
//
//    @Test
//    void testGetAlamatLengkap() {
//        assertEquals("Jl. Merdeka No. 123", alamat.getAlamatLengkap());
//    }
//
//    @Test
//    void testGetKota() {
//        assertEquals("Jakarta", alamat.getKota());
//    }
//
//    @Test
//    void testGetProvinsi() {
//        assertEquals("DKI Jakarta", alamat.getProvinsi());
//    }
//
//    @Test
//    void testGetNegara() {
//        assertEquals("Indonesia", alamat.getNegara());
//    }
//
//    @Test
//    void testGetUsers() {
//        List<User> users = alamat.getUsers();
//        assertEquals(1, users.size());
//        assertEquals("123", users.get(0).getUserId());
//        assertEquals("Test User", users.get(0).getName());
//    }
//}
