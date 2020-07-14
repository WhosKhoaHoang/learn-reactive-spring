package com.learnreactivespring.learnreactivespring.controller;

import java.time.Duration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;
import reactor.core.publisher.Flux;


@RestController
public class FluxAndMonoController {


  @GetMapping("/flux")
  public Flux<Integer> returnFlux() {
    // When a request from, say, a browser hits this endpoint,
    // then the browser becomes the subscriber to this Flux
    return Flux.just(1,2,3,4)
            .delayElements(Duration.ofSeconds(1))
            .log();
  }


  // MediaType.APPLICATION_STREAM_JSON_VALUE is for informing the client: "Hey,
  // ever I'm producing, it's a stream kind of value (as opposed to a regular JSON
  // value) so handle the data accordingly!" This means that results will be rendered
  // by the browser only as the data is available. NOTE: Results of this endpoint access
  // are bested viewed with Google Chrome.
  @GetMapping(value = "/fluxstream", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
  public Flux<Integer> returnFluxStream() {
    // When a request from, say, a browser hits this endpoint,
    // then the browser becomes the subscriber to this Flux
    return Flux.just(1,2,3,4)
        .delayElements(Duration.ofSeconds(1))
        .log();
  }
}
