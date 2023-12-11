package nas.springframework.springmvcrest2.api.v1.mapper;

import nas.springframework.springmvcrest2.api.v1.model.ProductDTO;
import nas.springframework.springmvcrest2.domain.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductMapperTest {

    private final static String NAME = "JOE";
    private final static Long ID = 1L;

    ProductMapper productMapper = ProductMapper.INSTANCE;


    @Test
    void productToProductDTO() {
        //given
        Product product = new Product();
        product.setId(ID);
        product.setName(NAME);
        //when
        ProductDTO productDTOResult = productMapper.productToProductDTO(product);
        //then
        assertNotNull(productDTOResult);
        assertEquals(productDTOResult.getName(),NAME );
    }

    @Test
    void productDTOToProduct() {
        //given
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(ID);
        productDTO.setName(NAME);

        //when
        Product productResult = productMapper.productDTOToProduct(productDTO);
        //then
        assertNotNull(productResult);
        assertEquals(productResult.getName(), NAME);
    }
}