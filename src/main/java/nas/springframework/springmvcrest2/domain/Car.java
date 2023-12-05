package nas.springframework.springmvcrest2.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Car {

    private String make;
    private int numberOfTheSeats;

    @Enumerated(value = EnumType.STRING)
    private CarType type;
}
