import org.example.Utils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class API {
    Utils utils = new Utils();
    RestTemplate restTemplate = new RestTemplate();
    String baseUrl = "http://localhost:8383/api/v1/houses";

    @BeforeEach
    public void convert() {
        //      Trick below: https://stackoverflow.com/questions/21854369/no-suitable-httpmessageconverter-found-for-response-type
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
        messageConverters.add(converter);
    }

    @Test
    public void getHouses() {
        ResponseEntity<String> response
                = restTemplate.getForEntity(baseUrl, String.class);
        assertEquals(response.getStatusCode(), HttpStatus.OK);

    }

    @Test
    public void getHouse() {
        ResponseEntity<String> response
                = restTemplate.getForEntity(baseUrl + "/1", String.class);
        assertEquals(response.getStatusCode(), HttpStatus.OK);

    }

    @Test
    public void postHouse() {
        String request = utils.reader("mockData/__files/add_house.json").toString();

        HttpEntity<String> entity = new HttpEntity<>(request);
        ResponseEntity<String> response = restTemplate.postForEntity(baseUrl, entity, String.class);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void postHouseWrong() {
        //      Negative Path Testing!!!
        //      Here you try to catch the 404 returned from the server and assert on it.

        String request_wrong = utils.reader("mockData/__files/add_house_wrong.json").toString();
        HttpEntity<String> entity = new HttpEntity<>(request_wrong);

        try {
            restTemplate.postForEntity(baseUrl, entity, String.class);
        } catch (HttpClientErrorException e) {
            assertEquals(HttpStatus.NOT_FOUND, e.getStatusCode());
        } catch (Exception e) {
            fail("this isn't the expected exception: " + e.getMessage());
        }
    }

}
