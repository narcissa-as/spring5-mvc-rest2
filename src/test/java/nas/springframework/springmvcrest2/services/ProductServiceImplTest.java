package nas.springframework.springmvcrest2.services;

import nas.springframework.springmvcrest2.api.v1.mapper.ProductMapper;
import nas.springframework.springmvcrest2.api.v1.model.ProductDTO;
import nas.springframework.springmvcrest2.domain.Product;
import nas.springframework.springmvcrest2.repositories.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

class ProductServiceImplTest {

    @Mock
    ProductRepository productRepository;

    ProductMapper productMapper = ProductMapper.INSTANCE;

    ProductServiceImpl productService;

    @BeforeEach
    void setUp() {

        MockitoAnnotations.openMocks(this);
        productService = new ProductServiceImpl(productRepository, productMapper);
    }

    @Test
    void getAllProducts() throws Exception {

        //given
        Product product1 = new Product();
        product1.setId(1l);
        product1.setName("fig");

        Product product2 = new Product();
        product2.setId(2l);
        product2.setName("apple");

        //when
        when(productRepository.findAll()).thenReturn(Arrays.asList(product1, product2));
        //then
        List<ProductDTO> productListResult = productService.getAllProducts();
        assertNotNull(productListResult);
        assertEquals(productListResult.size(), 2);

    }

    @Test
    void getProductById() throws Exception {
        //given
        Product product1 = new Product();
        product1.setId(1l);
        product1.setName("fig");
        //when
        when(productRepository.findById(anyLong())).thenReturn(Optional.of(product1));
        //then
        ProductDTO productDTOResult = productService.getProductById(1l);
        assertNotNull(productDTOResult);
        assertEquals(productDTOResult.getName(), "fig");
    }
}