package com.learnreactivespring.learnreactivespring.handler;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

// . Using handler functions (alongside router functions) is a different
//   implementation approach from using a class with the @RestController
//   annotation...
// . @Component will mark this class as a bean that will then be used in
//   our router function class
@Component
public class SampleHandlerFunction {

  // Take in a ServerRequest and give back a ServerResponse
  public Mono<ServerResponse> flux(ServerRequest serverRequest) {
    return ServerResponse.ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(Flux.just(1,2,3,4).log(), Integer.class);
  }

  public Mono<ServerResponse> mono(ServerRequest serverRequest) {
    return ServerResponse.ok()
        .contentType(MediaType.APPLICATION_JSON)
        .body(Mono.just(1).log(), Integer.class);
  }
}
