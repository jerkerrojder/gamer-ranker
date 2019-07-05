package com.trustly.gamerranker.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import lombok.Value;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

class GameControllerTest {


  public static final String URL = "localhost:8080";
  @Autowired
  private WebTestClient webClient;

  @Test
  void expectsWithdrawalStateOnTopic() throws IOException {

    @Value
    class Body {
      String gamename;
    }

    webClient
        .put()
        .uri(URL + "/state")
        .exchange()
        .expectStatus()
        .isOk();

  }
}