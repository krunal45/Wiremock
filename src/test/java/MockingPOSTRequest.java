import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import com.github.tomakehurst.wiremock.client.WireMock;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class MockingPOSTRequest extends Base{

    @BeforeTest
    @Override
    void defineStub() {
        mockResponse = new ResponseDefinitionBuilder();
        mockResponse.withHeader("Content-Type", "application/json");
        mockResponse.withStatus(200);
        mockResponse.withBodyFile("responseBody/loginToken.json");

        WireMock.stubFor(WireMock.post(Endpoint).willReturn(mockResponse));
    }

    @Test
    public void verifyPOSTAPIResponse() {
        String testAPI = "http://localhost:" + PORT + Endpoint;
        System.out.println("Services to be hit :" + testAPI);

        Response response = RestAssured.
                given().body("__files/requestBody/login.json").post(testAPI).then().statusCode(200).extract().response();
        Assert.assertEquals(response.jsonPath().get("token"), "QpwL5tke4Pnpja7X4");
    }

}
