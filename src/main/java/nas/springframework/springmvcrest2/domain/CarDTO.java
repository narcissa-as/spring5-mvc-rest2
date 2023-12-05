package nas.springframework.springmvcrest2.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CarDTO {
     private String make;
     private int seatCount;
     private String type;
}
