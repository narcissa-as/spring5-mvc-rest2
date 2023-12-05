package nas.springframework.springmvcrest2.domain;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CarMapper {

    CarMapper INSTANCE= Mappers.getMapper(CarMapper.class);
    @Mapping(source="numberOfTheSeats",target = "seatCount")
    CarDTO CarToCarDTO(Car car);


    default String fromEnum(CarType carType) {
        return carType == null ? null : carType.toString();
    }

    default CarType toEnum(String carType) {
        return carType == null ? null : CarType.fromString(carType);
    }
}
