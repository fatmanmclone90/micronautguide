package example.micronaut;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import example.micronaut.models.Payload;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.MediaType;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Test;

import jakarta.inject.Inject;

import java.util.Set;

@MicronautTest
public class HelloControllerTest {

    @Inject
    @Client("/")
    HttpClient client;

    @Inject
    Validator validator;

    @Test
    public void testHello() {
        HttpRequest<?> request = HttpRequest.GET("/hello").accept(MediaType.TEXT_PLAIN);
        String body = client.toBlocking().retrieve(request);

        assertNotNull(body);
        assertEquals("Hello World", body);
    }

    @Test
    public void validation(){
        var p = new Payload();
        p.setCode(1);

        HttpRequest<?> request = HttpRequest.POST("/hello/1", p).accept(MediaType.APPLICATION_JSON);
        String body = client.toBlocking().retrieve(request);

        assertNotNull(body);
        assertEquals("Hello World", body);
    }

    // fails to inject
    @Test
    void testThatPersonIsValidWithValidator() {
        var p = new Payload();
        p.setCode(1);

      //  var validator = Validation.buildDefaultValidatorFactory().getValidator();

        final Set<ConstraintViolation<Payload>> constraintViolations = validator.validate(p);  // (1)

        assertEquals(2, constraintViolations.size()); // (2)
    }
}