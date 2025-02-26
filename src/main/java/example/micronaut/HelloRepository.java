package example.micronaut;

import jakarta.inject.Singleton;

@Singleton
public class HelloRepository {

    private int counter = 0;

    public int save(){
        return counter++;
    }
}
