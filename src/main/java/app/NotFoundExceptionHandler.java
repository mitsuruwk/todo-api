package app;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Provider
public class NotFoundExceptionHandler implements ExceptionMapper<NotFoundException> {

    @Context
    private HttpHeaders headers;

    public final Response toResponse(final NotFoundException ex) {
        try {
            String message = "Not Found";
            Map<String, String> m = new HashMap<>();
            m.put("message", message);

            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(m);
            return Response.status(Status.NOT_FOUND).entity(json).type(MediaType.APPLICATION_JSON)
                    .build();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
