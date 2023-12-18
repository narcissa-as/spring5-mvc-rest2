package nas.springframework.springmvcrest2.controllers.v1;

import com.jayway.jsonpath.JsonPath;
import nas.springframework.springmvcrest2.api.v1.model.CustomerDTO;
import nas.springframework.springmvcrest2.controllers.RestResponseEntityExceptionHandler;
import nas.springframework.springmvcrest2.services.CustomerService;
import nas.springframework.springmvcrest2.services.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;

import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import java.util.Arrays;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class CustomerControllerTest extends AbstractRestController {

    @Mock
    CustomerService customerService;

    @InjectMocks
    CustomerController customerController;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        //Using controllerAdvice because of using ControllerAdvise in CustomerService, we have to bring ControllerAdvice here too
        mockMvc = MockMvcBuilders.standaloneSetup(customerController)
                .setControllerAdvice(new RestResponseEntityExceptionHandler())
                .build();
    }

    @Test
    void getAllCustomers() throws Exception {
        //given
        CustomerDTO customerDTO1 = new CustomerDTO();
        customerDTO1.setId(1L);
        customerDTO1.setFirstName("Joe");
        customerDTO1.setLastName("Weston");
        customerDTO1.setUrl("api/v1/customer/1");


        CustomerDTO customerDTO2 = new CustomerDTO();
        customerDTO2.setId(2l);
        customerDTO2.setFirstName("John");
        customerDTO2.setLastName("Axe");
        //better way to write hardcode way
        //customerDTO2.setUrl("api/v1/customer/2");
        customerDTO2.setUrl(CustomerController.BASE_URL + "/2");

        //when
        when(customerService.getAllCustomers()).thenReturn(Arrays.asList(customerDTO1, customerDTO2));

        //then
        mockMvc.perform(get(CustomerController.BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customers", hasSize(2)));

    }

    @Test
    void getCustomerById() throws Exception {
        //given
        CustomerDTO customer = new CustomerDTO();
        customer.setId(1l);
        customer.setFirstName("Joe");
        customer.setLastName("Wilson");

        //when
        when(customerService.getCustomerById(anyLong())).thenReturn(customer);
        //then
        mockMvc.perform(get(CustomerController.BASE_URL + "/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", equalTo("Joe")));
    }

    @Test
    void testNotFoundException() throws Exception {
        //when
        when(customerService.getCustomerById(anyLong())).thenThrow(ResourceNotFoundException.class);
        //then
        mockMvc.perform(get(CustomerController.BASE_URL + "/" + "10")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isNotFound());

    }

    @Test
    void creatNewCustomer() throws Exception {

        //given
        CustomerDTO customer = new CustomerDTO();
        customer.setId(1l);
        customer.setFirstName("Joe");
        customer.setLastName("William");
        customer.setUrl(CustomerController.BASE_URL + "/" + customer.getId());

        CustomerDTO returnedDTO = new CustomerDTO();
        returnedDTO.setId(customer.getId());
        returnedDTO.setFirstName(customer.getFirstName());
        returnedDTO.setLastName(customer.getLastName());

        //when
        when(customerService.createNewCustomer(any(CustomerDTO.class))).thenReturn(customer);
        //then
        mockMvc.perform(post(CustomerController.BASE_URL)
                        .content(asJsonString(customer))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isCreated())
                // .andExpect(jsonPath("$.url",equalTo("/api/v1/customers/1")))
                //related to  @JsonProperty("customer_url") in domain declaration ,to see the result, here we use
                // the JsonProperty's name
                .andExpect(jsonPath("$.customer_url", equalTo(CustomerController.BASE_URL + "/1")))
                .andExpect(jsonPath("$.firstName", equalTo("Joe")));

    }

    @Test
    void updateCustomer() throws Exception {
        //given

        CustomerDTO customer = new CustomerDTO();
        customer.setId(1l);
        customer.setFirstName("Joe");
        customer.setLastName("Weston");

        CustomerDTO returnDTO = new CustomerDTO();
        returnDTO.setFirstName(customer.getFirstName());
        returnDTO.setLastName(customer.getLastName());
        returnDTO.setUrl(CustomerController.BASE_URL + "/1");


        //when
        when(customerService.saveCustomerByDTO(anyLong(), any(CustomerDTO.class))).thenReturn(returnDTO);
        //then
        mockMvc.perform(put(CustomerController.BASE_URL + "/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(customer)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", equalTo("Joe")))
                .andExpect(jsonPath("$.customer_url", equalTo(CustomerController.BASE_URL + "/1")));

    }

    @Test
    void deleteCustomerById() throws Exception {

        mockMvc.perform(delete(CustomerController.BASE_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());

        verify(customerService, times(1)).deleteCustomerById(anyLong());

    }
}