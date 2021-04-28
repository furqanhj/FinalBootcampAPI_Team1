import client.NewsAPIClient;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import load.LoadBase;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestNewsAPI extends NewsAPIClient {

    LoadBase loadBase;
    String URI;

    @BeforeMethod
    public void setUp(){
        loadBase = new LoadBase();
        this.loadBase.properties.getProperty("NewsAPIURI");

    }

    @Test
    public void testGetTopHeadline(){
        String apiURI = URI + END_POINT_TOP_HEADLINES;

        Response response = get(apiURI);
        System.out.println("STATUS CODE: " + response.statusCode());

        response.then().assertThat().statusCode(200);

    }

}
