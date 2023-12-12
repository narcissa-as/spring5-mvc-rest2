package nas.springframework.springmvcrest2.services;

import nas.springframework.springmvcrest2.api.v1.model.CustomerDTO;

import java.util.List;

public interface CustomerService {

    List<CustomerDTO> getAllCustomers();
    CustomerDTO getCustomerById(Long id);
    CustomerDTO createNewCustomer(CustomerDTO customerDTO);
    void deleteCustomerById(Long id);

    }
