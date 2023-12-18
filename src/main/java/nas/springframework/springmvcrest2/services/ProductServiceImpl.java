package nas.springframework.springmvcrest2.services;

import nas.springframework.springmvcrest2.api.v1.mapper.ProductMapper;
import nas.springframework.springmvcrest2.api.v1.model.CustomerDTO;
import nas.springframework.springmvcrest2.api.v1.model.ProductDTO;
import nas.springframework.springmvcrest2.api.v1.model.ProductDTOList;
import nas.springframework.springmvcrest2.repositories.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductServiceImpl(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    public List<ProductDTO> getAllProducts() {
        return productRepository
                .findAll()
                .stream()
                .map(product -> {
                    ProductDTO productDTO =
                            productDTO = productMapper.productToProductDTO(product);
                    productDTO.setUrl("/shop/v2/products/" + product.getId());
                    return productDTO;
                })
                .collect(Collectors.toList());
    }

    public ProductDTO getProductById(Long id) {
        return productRepository
                .findById(Long.valueOf(id))
                .map(productMapper::productToProductDTO)
                .orElseThrow(ResourceNotFoundException::new);
    }
}
