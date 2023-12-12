package nas.springframework.springmvcrest2.controllers.v1;

import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class AbstractRestController {
    public String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
