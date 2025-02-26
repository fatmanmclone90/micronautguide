package example.micronaut.models;

import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public record Message(int statusCode, int message) {
}
