package nas.springframework.springmvcrest2.bootstrap;

import nas.springframework.springmvcrest2.domain.Customer;
import nas.springframework.springmvcrest2.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Bootstrap implements CommandLineRunner {

    private final CustomerRepository customerRepository;

    public Bootstrap(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        loadCustomers();

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
}
