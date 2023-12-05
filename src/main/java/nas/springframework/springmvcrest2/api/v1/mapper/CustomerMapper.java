package nas.springframework.springmvcrest2.api.v1.mapper;


import nas.springframework.springmvcrest2.domain.Customer;
import nas.springframework.springmvcrest2.api.v1.model.CustomerDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerMapper {

    CustomerMapper INSTANCE= Mappers.getMapper(CustomerMapper.class);

    CustomerDTO customerToCustomerDTO(Customer customer);
    Customer CustomerDTOToCustomer(CustomerDTO customerDTO);

    }

