import org.example.APIModel;
import org.example.Utils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.StringContains.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


public class API {

    Utils utils = new Utils();
    APIModel api = new APIModel();
    private final String requestOK = utils.reader("mockData/__files/add_house.json").toString();
    private final String requestNOK = utils.reader("mockData/__files/add_house_wrong.json").toString();

    @BeforeEach
    public void convert() {
        utils.converter();
        utils.logger();
    }

    @Test
    public void getHouses() {
        assertEquals(api.getForEntity("").getStatusCode(), HttpStatus.OK);
        assertThat(api.getForEntity("").toString(), containsString("Amsterdam"));
    }

    @Test
    public void getHouse() {
        assertEquals(api.getForEntity("/1").getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void postHouse() {
        assertEquals(api.postForEntity(requestOK).getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void postHouseWrong() {
        //      Negative Path Testing!!!
        //      Here you try to catch the 404 returned from the server and assert on it.

        try {
            api.postForEntity(requestNOK);
        } catch (HttpClientErrorException e) {
            assertEquals(HttpStatus.NOT_FOUND, e.getStatusCode());
        } catch (Exception e) {
            fail("this isn't the expected exception: " + e.getMessage());
        }
    }

}
