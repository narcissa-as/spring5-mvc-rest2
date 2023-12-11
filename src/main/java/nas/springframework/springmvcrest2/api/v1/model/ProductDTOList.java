package nas.springframework.springmvcrest2.api.v1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductDTOList {
    private List<ProductDTO> ProductDTOList;
}
