package com.example.RestTask.servicesImpl;

import com.example.RestTask.entities.TransportEntity;
import com.example.RestTask.servicesImp.TransportServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

public class TransportServiceImplTest {

    @Mock
    private WebClient.Builder webClientBuilder;
    @Mock
    private WebClient webClient;
    @Mock
    private WebClient.RequestBodyUriSpec requestBodyUriSpec;
    @Mock
    private WebClient.RequestHeadersSpec requestHeadersSpec;
    @Mock
    private WebClient.RequestHeadersUriSpec requestHeadersUriSpec;
    @Mock
    private WebClient.ResponseSpec responseSpec;

    private TransportServiceImpl transportService;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        when(webClientBuilder.build()).thenReturn(webClient);
        transportService = new TransportServiceImpl(webClientBuilder);

        // Set private fields
        Field clientIdField = TransportServiceImpl.class.getDeclaredField("clientId");
        clientIdField.setAccessible(true);
        clientIdField.set(transportService, "test-client-id");

        Field clientSecretField = TransportServiceImpl.class.getDeclaredField("clientSecret");
        clientSecretField.setAccessible(true);
        clientSecretField.set(transportService, "test-client-secret");
    }

    @Test
    void testRetrieveTransportList() {
        // Mock token response
        when(webClient.post()).thenReturn(requestBodyUriSpec);
        when(requestBodyUriSpec.uri(anyString())).thenReturn(requestBodyUriSpec);
        when(requestBodyUriSpec.header(anyString(), anyString())).thenReturn(requestBodyUriSpec);
        when(requestBodyUriSpec.bodyValue(anyString())).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(eq(String.class))).thenReturn(Mono.just("{\"access_token\":\"test-token\"}"));

        // Mock transport list response
        when(webClient.get()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri(anyString())).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.header(anyString(), anyString())).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(ArgumentMatchers.<ParameterizedTypeReference<List<TransportEntity>>>any()))
                .thenReturn(Mono.just(Collections.emptyList()));

        ResponseEntity<List<TransportEntity>> response = transportService.retrieveTransportList();
        assertEquals(204, response.getStatusCodeValue());
    }
}
