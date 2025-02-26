package example.micronaut;

import example.micronaut.models.Message;
import example.micronaut.models.Payload;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import jakarta.validation.Valid;
import io.micronaut.validation.Validated;

// not found, but still builds
@Validated
@Controller("/hello")
public class HelloController {

    private final HelloRepository helloRepository;

    HelloController(HelloRepository helloRepository){
        this.helloRepository = helloRepository;
    }

    @Get
    @Produces(MediaType.TEXT_PLAIN)
    public String index() {
        return "Hello World";
    }

    // doesn't validate
    @Post("/{id}")
    public HttpResponse<?> post(@PathVariable("id") long id, @Valid @Body Payload payload){
        var counter = helloRepository.save();
        return HttpResponse
                .status(HttpStatus.CREATED)
                .body(new Message(HttpStatus.CREATED.getCode(),counter));
    }
}