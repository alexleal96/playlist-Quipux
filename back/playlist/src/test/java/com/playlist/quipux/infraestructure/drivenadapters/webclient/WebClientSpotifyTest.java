package com.playlist.quipux.infraestructure.drivenadapters.webclient;


import com.playlist.quipux.domain.model.exceptions.WebClientException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.net.ssl.SSLException;

class WebClientSpotifyTest {

   private WebClientSpotify webClientSpotify;
   private WebClient webClient;

   @BeforeEach
   public void setup() throws SSLException {
       webClientSpotify = new WebClientSpotify("", "");

       webClient = Mockito.mock(WebClient.class);
       webClientSpotify.setWebClient(webClient);
   }

   @Test
    void defaultGet() {
       webClientSpotify.get();

       Mockito.verify(webClient, Mockito.times(1)).get();
   }

   @Test
    void defaultHead() {
       webClientSpotify.head();

       Mockito.verify(webClient, Mockito.times(1)).head();
   }

   @Test
    void defaultPost() {
       webClientSpotify.post();

       Mockito.verify(webClient, Mockito.times(1)).post();
   }

   @Test
    void defaultPut() {
       webClientSpotify.put();

       Mockito.verify(webClient, Mockito.times(1)).put();
   }

   @Test
    void defaultPatch() {
       webClientSpotify.patch();

       Mockito.verify(webClient, Mockito.times(1)).patch();
   }

   @Test
    void defaultDelete() {
       webClientSpotify.delete();

       Mockito.verify(webClient, Mockito.times(1)).delete();
   }

   @Test
    void defaultOptions() {
       webClientSpotify.options();

       Mockito.verify(webClient, Mockito.times(1)).options();
   }

   @Test
    void defaultMethod() {
       webClientSpotify.method(HttpMethod.GET);

       Mockito.verify(webClient, Mockito.times(1)).method(Mockito.any());
   }

   @Test
    void defaultMutate() {
       webClientSpotify.mutate();

       Mockito.verify(webClient, Mockito.times(1)).mutate();
   }

   @Test
    void postComplete() throws WebClientException {
       WebClient.RequestBodyUriSpec requestBodyUriSpec = Mockito.mock(WebClient.RequestBodyUriSpec.class);
       WebClient.RequestHeadersSpec requestHeadersSpec = Mockito.mock(WebClient.RequestHeadersSpec.class);
       WebClient.ResponseSpec responseSpec = Mockito.mock(WebClient.ResponseSpec.class);
       WebClient.RequestBodySpec requestBodySpec = Mockito.mock(WebClient.RequestBodySpec.class);


       Mockito.doReturn(requestBodyUriSpec).when(webClient).post();
       Mockito.when(requestBodyUriSpec.uri(Mockito.anyString(), Mockito.any(Object.class)))
               .thenReturn(requestBodyUriSpec);
       Mockito.when(requestBodyUriSpec.header(Mockito.anyString(), Mockito.anyString()))
               .thenReturn(requestBodySpec);

       Mockito.when(requestBodySpec.body(Mockito.any())).thenReturn(requestHeadersSpec);
       Mockito.when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
       Mockito.when(responseSpec.bodyToMono(ArgumentMatchers.<Class<String>>notNull()))
               .thenReturn(Mono.just(""));

      String result = webClientSpotify.post("https://localhost:8080/{id}", "",
              "token", "", String.class, 1);

      Assertions.assertNotNull(result);
   }

   @Test
    void post() throws WebClientException {
       WebClient.RequestBodyUriSpec requestBodyUriSpec = Mockito.mock(WebClient.RequestBodyUriSpec.class);
       WebClient.RequestHeadersSpec requestHeadersSpec = Mockito.mock(WebClient.RequestHeadersSpec.class);
       WebClient.ResponseSpec responseSpec = Mockito.mock(WebClient.ResponseSpec.class);
       WebClient.RequestBodySpec requestBodySpec = Mockito.mock(WebClient.RequestBodySpec.class);


       Mockito.doReturn(requestBodyUriSpec).when(webClient).post();
       Mockito.when(requestBodyUriSpec.uri(Mockito.anyString(), Mockito.any(Object.class)))
               .thenReturn(requestBodyUriSpec);
       Mockito.when(requestBodyUriSpec.header(Mockito.anyString(), Mockito.anyString()))
               .thenReturn(requestBodySpec);

       Mockito.when(requestBodySpec.retrieve()).thenReturn(responseSpec);
       Mockito.when(responseSpec.bodyToMono(ArgumentMatchers.<Class<String>>notNull()))
               .thenReturn(Mono.just(""));

       String result = webClientSpotify.post("https://localhost:8080/{id}", "", "", String.class, 1);

       Assertions.assertNotNull(result);
   }

   @Test
    void validateResponse() throws WebClientException {
       ClientResponse response = Mockito.mock(ClientResponse.class);
       Mockito.when(response.statusCode()).thenReturn(HttpStatus.OK);

       webClientSpotify.validateResponse(response);

       Mockito.verify(response, Mockito.times(1)).statusCode();
   }

   @Test()
    void validateResponseError() {
       Assertions.assertThrows(WebClientException.class, () -> {
           ClientResponse response = ClientResponse.create(HttpStatus.INTERNAL_SERVER_ERROR).build();
           webClientSpotify.validateResponse(response);
       });

   }
}
