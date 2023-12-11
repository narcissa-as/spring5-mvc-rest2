package nas.springframework.springmvcrest2.controllers.v1;

import nas.springframework.springmvcrest2.api.v1.model.ProductDTO;
import nas.springframework.springmvcrest2.api.v1.model.ProductDTOList;
import nas.springframework.springmvcrest2.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ProductController.BASE_URL)
public class ProductController {

    public final static String BASE_URL = "/api/v1/products";

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ProductDTOList getAllProducts() {

        return new ProductDTOList(productService.getAllProducts());
    }

    @GetMapping( {"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public ProductDTO getProductById(@PathVariable String id){
        return productService.getProductById(Long.valueOf(id));
    }
}
