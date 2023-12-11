package nas.springframework.springmvcrest2.bootstrap;

import nas.springframework.springmvcrest2.domain.Customer;
import nas.springframework.springmvcrest2.domain.Product;
import nas.springframework.springmvcrest2.repositories.CustomerRepository;
import nas.springframework.springmvcrest2.repositories.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Bootstrap implements CommandLineRunner {

    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;

    public Bootstrap(CustomerRepository customerRepository, ProductRepository productRepository) {
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        loadCustomers();
        loadProducts();
    }

    private void loadCustomers() {

        //given
        Customer customer1 = new Customer();
        customer1.setId(1L);
        customer1.setFirstName("Freddy");
        customer1.setLastName("Meyers");

        Customer customer2 = new Customer();
        customer2.setId(2L);
        customer2.setFirstName("Michale");
        customer2.setLastName("Weston");

        //save
        customerRepository.save(customer1);
        customerRepository.save(customer2);

        System.out.println("Customer Data loaded: " + customerRepository.count());
    }

    private void loadProducts() {

        Product product1 = new Product();
        product1.setId(1l);
        product1.setName("Dragon-Fruit");

        Product product2 = new Product();
        product2.setId(2l);
        product2.setName("Coconut");

        Product product3 = new Product();
        product3.setId(3l);
        product3.setName("Fig");


        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.save(product3);


        System.out.println("Product Data loaded: " + productRepository.count());
    }
}
