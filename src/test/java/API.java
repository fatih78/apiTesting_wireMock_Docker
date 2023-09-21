import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class API {
    RestTemplate restTemplate = new RestTemplate();
    String baseUrl = "http://localhost:8383/api/v1/houses";

    @Test
    public void getHouses() {
        ResponseEntity<String> response
                = restTemplate.getForEntity(baseUrl, String.class);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);

    }

    @Test
    public void getHouse() {
        ResponseEntity<String> response
                = restTemplate.getForEntity(baseUrl + "/1", String.class);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);

    }

    @Test
    public void postHouse() {
        String requestJson = "{\"id\":4,\"title\":\"house 4\"}";
//      Trick below: https://stackoverflow.com/questions/21854369/no-suitable-httpmessageconverter-found-for-response-type
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
        messageConverters.add(converter);

        HttpEntity<String> entity = new HttpEntity<>(requestJson);
        ResponseEntity<String> response = restTemplate.postForEntity(baseUrl, entity, String.class);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);    }
}
