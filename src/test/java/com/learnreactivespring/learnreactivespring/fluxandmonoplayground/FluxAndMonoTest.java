package com.learnreactivespring.learnreactivespring.fluxandmonoplayground;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

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
    // log() ^here is useful for seeing all of the events flowing between a Flux
    // and its subscriber

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


  @Test
  public void fluxTestElementsWithoutError() {
    Flux<String> stringFlux = Flux.just("Spring", "Spring Boot", "Reactive Spring")
        .log();

    StepVerifier.create(stringFlux)
      .expectNext("Spring")
      .expectNext("Spring Boot")
      .expectNext("Reactive Spring")
      .verifyComplete();
    // Without verifyComplete(), then a proper connection to stringFlux isn't made. In
    // other words, verifyComplete() is what starts the flow of elements from stringFlux.
  }


  @Test
  public void fluxTestElementsWithError() {
    Flux<String> stringFlux = Flux.just("Spring", "Spring Boot", "Reactive Spring")
        .concatWith(Flux.error(new RuntimeException("Exception Occurred")))
        .log();

    StepVerifier.create(stringFlux)
        .expectNext("Spring")
        .expectNext("Spring Boot")
        .expectNext("Reactive Spring")
        //.expectError(RuntimeException.class)
        .expectErrorMessage("Exception Occurred")
        .verify();

    // Like verifyComplete(), verify() begins the flow of elements from stringFlux to
    // subscriber defined within the StepVerifier. I think you want to use verify() in
    // cases where the StepVerifier doesn't complete successfully?
  }


  @Test
  public void fluxTestElementsWithErrorAltSyntax() {
    Flux<String> stringFlux = Flux.just("Spring", "Spring Boot", "Reactive Spring")
        .concatWith(Flux.error(new RuntimeException("Exception Occurred")))
        .log();

    // Instead of writing expectNext() for each element, you can write
    // all the elements inside a single call.
    StepVerifier.create(stringFlux)
        .expectNext("Spring", "Spring Boot", "Reactive Spring")
        .expectErrorMessage("Exception Occurred")
        .verify();
  }


  @Test
  public void fluxTestElementsCountWithError() {
    Flux<String> stringFlux = Flux.just("Spring", "Spring Boot", "Reactive Spring")
        .concatWith(Flux.error(new RuntimeException("Exception Occurred")))
        .log();

    StepVerifier.create(stringFlux)
        .expectNextCount(3)
        .expectErrorMessage("Exception Occurred")
        .verify();
  }


  @Test
  public void monoTest() {
    Mono<String> stringMono = Mono.just("Spring");

    // . You can call log() here instead of on Mono.just("Spring")
    StepVerifier.create(stringMono.log())
        .expectNext("Spring")
        .verifyComplete();
  }


  @Test
  public void monoTestError() {
    StepVerifier.create(Mono.error(new RuntimeException("Exception occurred")).log())
        .expectError(RuntimeException.class)
        .verify();
  }
}
