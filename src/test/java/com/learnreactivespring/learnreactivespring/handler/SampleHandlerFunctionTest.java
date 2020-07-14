package com.learnreactivespring.learnreactivespring.handler;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;


// @AutoConfigureWebTestClient is needed so that a WebTestClient
// can be instantiated and configured for this test class
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureWebTestClient
public class SampleHandlerFunctionTest {

  @Autowired
  WebTestClient webTestClient;

  @Test
  public void fluxTest() {
    // Build an integerFlux that will make the call to the target endpoint:
    Flux<Integer> integerFlux = webTestClient.get().uri("/functional/flux")
        .accept(MediaType.APPLICATION_JSON_UTF8)
        .exchange()  // exchange() is what will actually make the call to the endpoint
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
  public void monoTest() {
    Integer expectedValue = new Integer(1);
    webTestClient.get().uri("/functional/mono")
        .accept(MediaType.APPLICATION_JSON_UTF8)
        .exchange()  // exchange() is what will actually make the call to the endpoint
        .expectStatus().isOk()
        .expectBody(Integer.class)
        .consumeWith((response) -> {
          assertEquals(expectedValue, response.getResponseBody());
        });
  }
}
