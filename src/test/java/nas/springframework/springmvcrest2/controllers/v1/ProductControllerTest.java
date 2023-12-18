package nas.springframework.springmvcrest2.controllers.v1;

import nas.springframework.springmvcrest2.api.v1.model.ProductDTO;
import nas.springframework.springmvcrest2.api.v1.model.ProductDTOList;
import nas.springframework.springmvcrest2.controllers.RestResponseEntityExceptionHandler;
import nas.springframework.springmvcrest2.domain.Product;
import nas.springframework.springmvcrest2.services.ProductService;
import nas.springframework.springmvcrest2.services.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {

    @Mock
    ProductService productService;
    @InjectMocks
    ProductController productController;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {

        mockMvc = MockMvcBuilders.standaloneSetup(productController)
                .setControllerAdvice(new RestResponseEntityExceptionHandler())
                .build();
    }

    @Test
    void getAllProducts() throws Exception {
        //given
        // List<ProductDTO> productDTOList = new ArrayList<>();
        // productList.add(new Product(1l, "fig"));
        // = Arrays.asList(new ProductDTO(1l, "fig", "api/v1/product/1"), new ProductDTO(2l, "banana", "api/v1/product/2"));
        ProductDTO productDTO1 = new ProductDTO();
        productDTO1.setId(1l);
        productDTO1.setName("fig");
  /*
        productDTOList.add(productDTO1);*/

        //when
        when(productService.getAllProducts()).thenReturn(Arrays.asList(new ProductDTO(1l, "fig", null)));
        //then
        mockMvc.perform(MockMvcRequestBuilders.get(ProductController.BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productDTOList", hasSize(1)));
    }

    @Test
    void getProductById() throws Exception {

        //given
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(1l);
        productDTO.setName("fig");

        //when
        when(productService.getProductById(anyLong())).thenReturn(productDTO);

        //then
        //"/api/v1/products"
        mockMvc.perform(MockMvcRequestBuilders.get(ProductController.BASE_URL + "/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productDTO", hasSize(1)));
        // .andExpect(jsonPath("$.name",equalTo("fig")));

    }

    @Test
    void testGetProductByIdNotFoundException() throws Exception {
        //when
        when(productService.getProductById(anyLong())).thenThrow(ResourceNotFoundException.class);
        //then
        mockMvc.perform(get(ProductController.BASE_URL + "/" + 10)
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isNotFound());
    }
}