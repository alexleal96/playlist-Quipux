package com.playlist.quipux.infraestructure.drivenadapters.webclient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.playlist.quipux.domain.model.exceptions.WebClientException;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import io.netty.resolver.DefaultAddressResolverGroup;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import javax.net.ssl.SSLException;
import java.time.Duration;

@Slf4j
class WebClientSpotify implements WebClient {

    private static final String REQUEST_CORS_ORIGIN = "Origin";
    private static final String CORRELATION_ID = "correlationId";

    @Setter
    private WebClient webClient;
    private final ObjectMapper theMapper;

    public WebClientSpotify(String baseUrl, String origin) throws SSLException {
        this.webClient = buildClient(baseUrl, origin);
        theMapper = new ObjectMapper();
    }

    @Override
    public RequestHeadersUriSpec<?> get() {
        return webClient.get();
    }

    public <T> T get(String uri,
                     String authorizationHeader,
                     Class<T> responseClass,
                     Object... uriVariables) {
		return  webClient.get()
						.uri(uri, uriVariables)
						.header(HttpHeaders.AUTHORIZATION, authorizationHeader)
						.retrieve()
						.bodyToMono(responseClass)
						.block();
    }

    @Override
    public RequestHeadersUriSpec<?> head() {
        return webClient.head();
    }

    @Override
    public WebClient.RequestBodyUriSpec post() {
        return webClient.post();
    }

    public <T> T post(String uri,
                      String authorizationHeader,
                      String headerKeyInternalHorsdv,
                      Class<T> responseClass,
                      Object... uriVariables) throws WebClientException {
        return post(uri, null, authorizationHeader, headerKeyInternalHorsdv, responseClass, uriVariables);
    }

    public <T> T post(String uri,
                      Object body,
                      String authorizationHeader,
                      String headerKeyInternalHorsdv,
                      Class<T> responseClass,
                      Object... uriVariables) throws WebClientException {
        try {
            WebClient.RequestBodySpec requestBodySpec;

                requestBodySpec = webClient.post()
                        .uri(uri, uriVariables)
                        .header(HttpHeaders.AUTHORIZATION, authorizationHeader);

            if (body != null) {
                return requestBodySpec
                        .body(BodyInserters.fromValue(theMapper.writeValueAsString(body)))
                        .retrieve()
                        .bodyToMono(responseClass)
                        .block();
            }
            return requestBodySpec
                    .retrieve()
                    .bodyToMono(responseClass)
                    .block();
        } catch (JsonProcessingException e) {
            log.error("Error sending POST request: {}", e.getMessage());
            throw new WebClientException(e);
        }
    }

    @Override
    public RequestBodyUriSpec put() {
        return webClient.put();
    }

    @Override
    public RequestBodyUriSpec patch() {
        return webClient.patch();
    }

    @Override
    public RequestHeadersUriSpec<?> delete() {
        return webClient.delete();
    }

    @Override
    public RequestHeadersUriSpec<?> options() {
        return webClient.options();
    }

    @Override
    public RequestBodyUriSpec method(HttpMethod method) {
        return webClient.method(method);
    }

    @Override
    public Builder mutate() {
        return webClient.mutate();
    }

    private WebClient buildClient(String baseUrl, String origin) throws SSLException {
        var sslContext = SslContextBuilder
                .forClient()
                .trustManager(InsecureTrustManagerFactory.INSTANCE)
                .build();

        var httpClient = HttpClient.create()
                .secure(t -> t.sslContext(sslContext))
                .responseTimeout(Duration.ofMinutes(1))
                .resolver(DefaultAddressResolverGroup.INSTANCE)
                .doOnRequest(((httpClientRequest, connection) -> connection.addHandlerFirst(new HttpLoggingHandler())));

        return WebClient.builder()
                .baseUrl(baseUrl)
                .defaultHeader(REQUEST_CORS_ORIGIN, origin.split(",")[0].trim())
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .filter((clientRequest, nextFilter) -> nextFilter.exchange(ClientRequest.from(clientRequest)
                        .header(CORRELATION_ID, MDC.get(CORRELATION_ID))
                        .build()))
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .build();
    }

    public void validateResponse(ClientResponse response) throws WebClientException {
        if (response == null || !HttpStatus.OK.equals(response.statusCode())) {
            throw new WebClientException();
        }
    }


}
