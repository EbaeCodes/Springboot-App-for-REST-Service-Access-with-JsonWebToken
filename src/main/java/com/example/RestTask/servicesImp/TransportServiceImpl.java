package com.example.RestTask.servicesImp;


import com.example.RestTask.entities.TransportEntity;
import com.example.RestTask.services.TransportService;
import org.json.JSONObject;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

@Component
public class TransportServiceImpl implements TransportService {
    private final WebClient webClient;

    @Value("${client.id}")
    private String clientId;

    @Value("${client.secret}")
    private String clientSecret;


    public TransportServiceImpl(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    private String getAuthorizationToken() {
        if (clientId == null || clientSecret == null) {
            throw new IllegalArgumentException("Client ID and Client Secret must be provided");
        }

        return webClient.post()
                .uri("https://mbn-provider.authentication.eu12.hana.ondemand.com/oauth/token")
                .header("content-type", "application/x-www-form-urlencoded")
                .bodyValue("grant_type=client_credentials&response_type=token&client_id="+clientId+"&client_secret="+clientSecret+"&scope=interview_demo_transport_app!b923597.transportread")
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    @Override
    public ResponseEntity <List<TransportEntity>> retrieveTransportList() {
        JSONObject jsonObject = new JSONObject(getAuthorizationToken());
        String accessToken = jsonObject.getString("access_token");
        System.out.println("Access Token: " + accessToken);

        List<TransportEntity> transportList = webClient.get()
                .uri("https://interview-demo-transport-backend.cfapps.eu12.hana.ondemand.com/transports")
                .header("Authorization", "Bearer " + accessToken )
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<TransportEntity>>() {})
                .block();
        if (transportList == null || transportList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(transportList);
    }


}
