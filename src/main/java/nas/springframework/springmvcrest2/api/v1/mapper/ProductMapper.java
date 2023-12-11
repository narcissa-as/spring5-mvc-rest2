package nas.springframework.springmvcrest2.api.v1.mapper;

import nas.springframework.springmvcrest2.api.v1.model.ProductDTO;
import nas.springframework.springmvcrest2.domain.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    ProductDTO productToProductDTO(Product product);
    Product productDTOToProduct(ProductDTO productDTO);
}
