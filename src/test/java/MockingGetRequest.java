import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import com.github.tomakehurst.wiremock.client.WireMock;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class MockingGetRequest extends Base{

    @BeforeTest
    @Override
    void defineStub() {
        mockResponse = new ResponseDefinitionBuilder();
        mockResponse.withHeader("Content-Type", "application/json");
        mockResponse.withStatus(200);
        mockResponse.withBodyFile("responseBody/index.json");

        WireMock.stubFor(WireMock.get(Endpoint).willReturn(mockResponse));
    }

    @Test
    public void verifyGETAPIResponse() {
        String testAPI = "http://localhost:" + PORT + Endpoint;
        System.out.println("Services to be hit :" + testAPI);

        Response response = RestAssured.
                given().get(testAPI).then().statusCode(200).extract().response();
        Assert.assertEquals(response.jsonPath().get("data.id"), "2");
    }
}
