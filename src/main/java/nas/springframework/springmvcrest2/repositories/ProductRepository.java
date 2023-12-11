package nas.springframework.springmvcrest2.repositories;

import nas.springframework.springmvcrest2.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long > {
}
