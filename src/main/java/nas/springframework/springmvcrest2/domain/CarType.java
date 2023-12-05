package nas.springframework.springmvcrest2.domain;

import javax.persistence.Enumerated;

public enum CarType {
    SEDAN;


    public static CarType fromString(String carType) {
        if (carType != null) {
            for (CarType t : CarType.values()) {
                if (t.toString().equals(carType)) {
                    return t;
                }
            }
        }
        return null;
    }
}
