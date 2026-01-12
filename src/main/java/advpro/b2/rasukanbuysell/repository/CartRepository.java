package advpro.b2.rasukanbuysell.repository;

import advpro.b2.rasukanbuysell.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, String> {


    Cart findByOwnerId(@Param("ownerId") String ownerId);
}
