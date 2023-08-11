import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.http.*;
import com.github.tomakehurst.wiremock.direct.DirectCallHttpServer;
import com.github.tomakehurst.wiremock.direct.DirectCallHttpServerFactory;
import com.google.common.base.Optional;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;

abstract public class Base {
    protected static final String HOST = "localhost";
    protected static final int PORT = 8080;
    protected static final String Endpoint = "/users";
    protected static WireMockServer server = new WireMockServer(PORT);
    protected ResponseDefinitionBuilder mockResponse;

    @BeforeTest
    public void initializeServer() {
        System.out.println("--init--");
        server.start();
        WireMock.configureFor(HOST, PORT);
    }

    abstract void defineStub();


}
