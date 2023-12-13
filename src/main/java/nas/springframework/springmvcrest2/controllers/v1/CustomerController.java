package nas.springframework.springmvcrest2.controllers.v1;

import nas.springframework.springmvcrest2.api.v1.model.CustomerDTO;
import nas.springframework.springmvcrest2.api.v1.model.CustomerDTOList;
import nas.springframework.springmvcrest2.domain.Customer;
import nas.springframework.springmvcrest2.services.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
//this bellow annotation is for using ResponseEntity methods
//@Controller
@RequestMapping(CustomerController.BASE_URL)
public class CustomerController {

    public static final String BASE_URL = "/api/v1/customers";

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public CustomerDTOList getAllCustomers() {

        return new CustomerDTOList(customerService.getAllCustomers());
    }

    //we don't need to write code using ResponseEntity,But here is the way of writing code with ResponseEntity
    /*@GetMapping(BASE_URL)
    public ResponseEntity<CustomerDTOList> getAllCustomers2() {
      return new ResponseEntity<CustomerDTOList>(new CustomerDTOList(customerService.getAllCustomers()),HttpStatus.OK);
    }*/
    @GetMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public CustomerDTO getCustomerById(@PathVariable String id) {

        return customerService.getCustomerById(Long.valueOf(id));

    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerDTO createNewCustomer(@RequestBody CustomerDTO customerDTO) {
        return customerService.createNewCustomer(customerDTO);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CustomerDTO updateCustomer(@PathVariable Long id, @RequestBody CustomerDTO customerDTO) {
        return customerService.saveCustomerByDTO(id, customerDTO);
    }

    @DeleteMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public void deleteCustomerById(@PathVariable Long id) {
        customerService.deleteCustomerById(id);
    }
}
