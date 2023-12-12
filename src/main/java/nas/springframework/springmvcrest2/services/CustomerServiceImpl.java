
package nas.springframework.springmvcrest2.services;

import nas.springframework.springmvcrest2.api.v1.mapper.CustomerMapper;
import nas.springframework.springmvcrest2.api.v1.model.CustomerDTO;
import nas.springframework.springmvcrest2.controllers.v1.CustomerController;
import nas.springframework.springmvcrest2.domain.Customer;
import nas.springframework.springmvcrest2.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper) {

        this.customerMapper = customerMapper;
        this.customerRepository = customerRepository;

    }


    public List<CustomerDTO> getAllCustomers() {

        return customerRepository
                .findAll()
                .stream()
                .map(customer -> {
                    CustomerDTO customerDTO =
                            customerDTO = customerMapper.customerToCustomerDTO(customer);
                    customerDTO.setUrl(getCustomerURL(customer.getId()));
                    return customerDTO;
                })
                .collect(Collectors.toList());
    }

    private String getCustomerURL(Long id) {
        return CustomerController.BASE_URL + "/" + id;
    }

    public CustomerDTO getCustomerById(Long id) {

        return customerRepository.findById(id)
                .map(customerMapper::customerToCustomerDTO).orElseThrow(RuntimeException::new);
    }

    public CustomerDTO createNewCustomer(CustomerDTO customerDTO) {

        Customer customer = customerMapper.customerDTOToCustomer(customerDTO);
        Customer savedCustomer = customerRepository.save(customer);
        CustomerDTO returnDTO = customerMapper.customerToCustomerDTO(savedCustomer);
        returnDTO.setUrl("/api/v1/customers/" + savedCustomer.getId());

        return returnDTO;

    }
}
