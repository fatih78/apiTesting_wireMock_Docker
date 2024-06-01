package org.example;

import com.jogamp.nativewindow.CapabilitiesFilter;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

public class Utils {
    private static final Logger LOGGER = Logger.getLogger(CapabilitiesFilter.Test.class
            .getClass().getName());

    RestTemplate restTemplate = new RestTemplate();
    private final String baseUrl = "http://localhost:8383/api/v1/houses";
    public Object reader(String file){
        JSONParser jsonParser = new JSONParser();

        Object requestJson;
        try (FileReader reader = new FileReader(file)) {
            requestJson = jsonParser.parse(reader);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
       return requestJson;
    }

    public void converter(){
        //      Trick below: https://stackoverflow.com/questions/21854369/no-suitable-httpmessageconverter-found-for-response-type
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
        messageConverters.add(converter);
    }

    public void logger(){
        ResponseEntity<String> response
                = restTemplate.getForEntity(baseUrl, String.class);
        LOGGER.info(String.valueOf(response));
    }
}