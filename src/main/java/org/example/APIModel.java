package org.example;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class APIModel {
    RestTemplate restTemplate = new RestTemplate();
    String baseUrl = "http://localhost:8383/api/v1/houses";


    private ResponseEntity<String> apiCallGetForEntity(String value){
        ResponseEntity<String> response
                = restTemplate.getForEntity(baseUrl+value, String.class);

        return response;
    }

    private ResponseEntity<String> apiCallPostForEntity(String request){
        HttpEntity<String> entity = new HttpEntity<>(request);
        ResponseEntity<String> response = restTemplate.postForEntity(baseUrl, entity, String.class);

        return response;
    }


    public ResponseEntity<String> getForEntity(String value){
        return apiCallGetForEntity(value);
    }

    public ResponseEntity<String> postForEntity(String request){
        return apiCallPostForEntity(request);
    }


}
