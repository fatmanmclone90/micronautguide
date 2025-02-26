package example.micronaut.models;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.validation.constraints.Min;

@Introspected
@Serdeable
public class Payload {

    @Min(18)
    private int code;

    public int getCode(){
        return code;
    }

    public void setCode(int code){
        this.code = code;
    }

}
