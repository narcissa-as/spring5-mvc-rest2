package nas.springframework.springmvcrest2.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CarMapperTest {

    @Test
    void carToCarDTO() {
        //given
        Car car = new Car("Morris", 4,CarType.SEDAN);

        //when
        CarDTO carDTO = CarMapper.INSTANCE.CarToCarDTO(car);

        //then
        assertNotNull(carDTO);
        assertEquals(carDTO.getMake(),"Morris");
        assertEquals(carDTO.getSeatCount(),4);
        assert (carDTO.getType().equals("SEDAN"));

    }
}