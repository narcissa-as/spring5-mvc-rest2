package nas.springframework.springmvcrest2.api.v1.mapper;

import nas.springframework.springmvcrest2.api.v1.model.CustomerDTO;
import nas.springframework.springmvcrest2.domain.Customer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerMapperTest {

    public static final String NAME="JOE";
    public static final Long ID=1l;

    CustomerMapper customerMapper = CustomerMapper.INSTANCE;

    @Test
    void customerToCustomerDTO() {
        //given
        Customer customer=new Customer();
        customer.setId(ID);
        customer.setFirstName(NAME);

        //when
        CustomerDTO customerDTO =customerMapper.customerToCustomerDTO(customer);

        //then
        assertNotNull(customerDTO);
        assertEquals(customerDTO.getId(),ID);
        assertEquals(customerDTO.getFirstName(),NAME);
    }
}