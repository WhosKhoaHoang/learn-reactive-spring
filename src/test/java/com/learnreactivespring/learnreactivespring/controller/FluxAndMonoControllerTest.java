package com.learnreactivespring.learnreactivespring.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.http.MediaType;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;


// NOTE: THESE TESTS DON'T WORK -- probably due to some version incompatibility
//       between what you set and what was set in the tutorial! Just use this file as reference

/*
// Note that @WebFluxTest scans your project for classes
// to test that have been annotated with @RestController.
// The annotation will also cause a webTestClient to be
// instantiated for the tests.
@RunWith(SpringRunner.class)
@WebFluxTest
public class FluxAndMonoControllerTest {

  // In order to test the non-blocking endpoints, we will
  // need to use a non-blocking client (WebTestClient)
  @Autowired
  WebTestClient webTestClient;

  @Test
  public void fluxApproach1() {
    // This approach checks each individual value emitted by the endpoint's data stream

    // Build an integerFlux that will make the call to the target endpoint:
    Flux<Integer> integerFlux = webTestClient.get().uri("/flux")
        .accept(MediaType.APPLICATION_JSON_UTF8)
        .exchange()  //exchange() is what will actually make the call to the endpoint
        .expectStatus().isOk()
        .returnResult(Integer.class)
        .getResponseBody();

    // I think the idea is that StepVerifier.create will trigger the call to the
    // endpoint we want to test on the integerFlux that we pass to it....
    StepVerifier.create(integerFlux)
        .expectSubscription()
        .expectNext(1)
        .expectNext(2)
        .expectNext(3)
        .expectNext(4)
        .verifyComplete();
  }


  @Test
  public void fluxApproach2() {
    // This approach will evaluate the size of the Flux as opposed to
    // the individual values within the Flux. Also note how we're testing
    // from a single expression as opposed to how we split things up in fluxApproach1
    webTestClient.get().uri("/flux")
        .accept(MediaType.APPLICATION_JSON_UTF8)
        .exchange()  //exchange() is what will actually make the call to the endpoint
        .expectStatus().isOk()
        .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
        .expectBodyList(Integer.class)
        .hasSize(4);
  }


  @Test
  public void fluxApproach3() {
    // This approach converts the entire result into a list of Integers
    // which we then check against a locally defined list of Integers

    List<Integer> expectedIntegerList = Arrays.asList(1, 2, 3, 4);

    EntityExchangeResult<List<Integer>> entityExchangeResult = webTestClient.get().uri("/flux")
        .accept(MediaType.APPLICATION_JSON_UTF8)
        .exchange()  //exchange() is what will actually make the call to the endpoint
        .expectStatus().isOk()
        .expectBodyList(Integer.class)
        .returnResult();

    assertEquals(expectedIntegerList, entityExchangeResult.getResponseBody());
  }


  @Test
  public void fluxApproach4() {
    // This approach converts the entire result into a list of Integers
    // which we then check against a locally defined list of Integers

    List<Integer> expectedIntegerList = Arrays.asList(1, 2, 3, 4);

    webTestClient.get().uri("/flux")
        .accept(MediaType.APPLICATION_JSON_UTF8)
        .exchange()  //exchange() is what will actually make the call to the endpoint
        .expectStatus().isOk()
        .expectBodyList(Integer.class)
        .consumeWith((response) -> {
          assertEquals(expectedIntegerList, response.getResponseBody());
        });
  }


  @Test
  public void fluxStreamTest() {
    Flux<Long> longStreamFlux = webTestClient.get().uri("/fluxstream")
        .accept(MediaType.APPLICATION_STREAM_JSON)   // Not APPLICATION_JSON_UTF8 since the endpoint emits data indefinitely
        .exchange()  //exchange() is what will actually make the call to the endpoint
        .expectStatus().isOk()
        .returnResult(Long.class)  // The returned result is a Flux, not some complete, finite set of values
        .getResponseBody();

    StepVerifier.create(longStreamFlux)
            .expectNext(0l)
            .expectNext(1l)
            .expectNext(2l)
            .thenCancel()   // This is to stop the subscription to the infinitely
                            // emitting data producer (i.e., the /fluxstream endpoint)
  }
}
*/
