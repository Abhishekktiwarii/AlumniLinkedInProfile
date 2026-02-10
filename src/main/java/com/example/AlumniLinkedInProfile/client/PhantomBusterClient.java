package com.example.AlumniLinkedInProfile.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Component
public class PhantomBusterClient {

    private final RestTemplate restTemplate;

    @Value("${phantombuster.api.key}")
    private String apiKey;

    @Value("${phantombuster.agent.id}")
    private String agentId;

    public PhantomBusterClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String launchLinkedInSearch(String searchUrl) {

        String url = "https://api.phantombuster.com/api/v2/agents/launch";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("X-Phantombuster-Key", apiKey);

        Map<String, Object> body = new HashMap<>();
        body.put("id", agentId);
        body.put("argument", Map.of("searchUrl", searchUrl));

        HttpEntity<Map<String, Object>> request =
                new HttpEntity<>(body, headers);

        ResponseEntity<String> response =
                restTemplate.postForEntity(url, request, String.class);

        // 🔥 DEBUG LOGS (IMPORTANT)
        System.out.println("PHANTOM STATUS = " + response.getStatusCode());
        System.out.println("PHANTOM BODY   = " + response.getBody());

        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException(
                    "PhantomBuster API failed: " + response.getStatusCode()
            );
        }

        return response.getBody();
    }
}
