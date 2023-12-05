package nas.springframework.springmvcrest2.services;

import nas.springframework.springmvcrest2.api.v1.mapper.CustomerMapper;
import nas.springframework.springmvcrest2.api.v1.model.CustomerDTO;
import nas.springframework.springmvcrest2.domain.Customer;
import nas.springframework.springmvcrest2.repositories.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

class CustomerServiceImplTest {

    private final static String NAME1 = "leo";
    private final static Long ID1 = 1l;

    private final static String NAME2 = "jeo";
    private final static Long ID2 = 2l;

    @Mock
    CustomerRepository customerRepository;

    CustomerMapper customerMapper = CustomerMapper.INSTANCE;
    CustomerServiceImpl customerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        customerService = new CustomerServiceImpl(customerRepository, customerMapper);
    }

    @Test
    void getAllCustomers() throws Exception {
        //given
        Customer customer1 = new Customer();
        customer1.setId(ID1);
        customer1.setFirstName(NAME1);

        Customer customer2 = new Customer();
        customer2.setId(ID2);
        customer2.setFirstName(NAME2);

        List<Customer> customers = new ArrayList<>();
        customers.add(customer1);
        customers.add(customer2);

        //when
        when(customerRepository.findAll()).thenReturn(customers);

        //then
        List<CustomerDTO> customerDTOS = customerService.getAllCustomers();

        assertNotNull(customerDTOS);
        assertEquals(customerDTOS.size(), 2);

    }

    @Test
    void getCustomerById() throws Exception {
        //given
        Customer customer = new Customer();
        customer.setId(1l);
        customer.setFirstName("Joe");
        customer.setLastName("Watson");

        //when
        when(customerRepository.findById(anyLong())).thenReturn(Optional.ofNullable(customer));
        //then
        CustomerDTO customerDTOResult = customerService.getCustomerById(1l);
        assertNotNull(customerDTOResult);
        assertEquals(customerDTOResult.getFirstName(), "Joe");
    }
}