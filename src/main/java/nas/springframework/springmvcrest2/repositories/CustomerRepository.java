package nas.springframework.springmvcrest2.repositories;

import nas.springframework.springmvcrest2.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
}
