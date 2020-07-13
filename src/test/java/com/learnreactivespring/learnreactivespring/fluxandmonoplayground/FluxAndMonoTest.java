package com.learnreactivespring.learnreactivespring.fluxandmonoplayground;

import org.junit.jupiter.api.Test;
import org.springframework.data.mongodb.util.BsonUtils;
import reactor.core.publisher.Flux;

public class FluxAndMonoTest {

  @Test
  public void fluxTest() {

    // . You can intentionally cause an error by using concatWith() with Flux.error
    // . Once an error is emitted from a Flux, then it is not going to send you
    //   anymore data after that
    Flux<String> stringFlux = Flux.just("Spring", "Spring Boot", "Reactive Spring")
        //.concatWith(Flux.error(new RuntimeException("Exception Occurred")))
        //.concatWith(Flux.just("After Error"))
        .log();

    // . Elements from stringFlux will be passed to the subscribe method one-by-one.
    //   Based on how Flux works, the program will not block and wait after passing
    //   each single element; instead, it will keep on passing element after element
    //   with the onNext() method that is called inside of subscribe()
    // . Second arg is an event handler for an error event, third arg is an event handler
    //   for an all complete event
    stringFlux
        .subscribe(System.out::println,
            (e) -> System.err.println("Exception is " + e),
            () -> System.out.println("Completed"));



  }

}
