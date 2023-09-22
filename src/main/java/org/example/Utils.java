package org.example;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Utils {
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
}