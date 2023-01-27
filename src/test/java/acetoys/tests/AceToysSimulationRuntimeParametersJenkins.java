package acetoys.tests;

import acetoys.simulation.TestPopulation;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpProtocolBuilder;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.http;

public class AceToysSimulationRuntimeParametersJenkins extends Simulation {

    private static final String TEST_TYPE = System.getenv("TEST_TYPE");


    private static final String DOMAIN = "acetoys.uk";

    private HttpProtocolBuilder httpProtocol = http
            .baseUrl("https://" + DOMAIN)
            .inferHtmlResources(AllowList(), DenyList(".*\\.js", ".*\\.css", ".*\\.gif", ".*\\.jpeg", ".*\\.jpg", ".*\\.ico", ".*\\.woff", ".*\\.woff2", ".*\\.(t|o)tf", ".*\\.png", ".*detectportal\\.firefox\\.com.*"))
            .acceptEncodingHeader("gzip, deflate")
            .acceptLanguageHeader("en-US,en;q=0.9,bn;q=0.8");


    {

        if (TEST_TYPE.equals("INSTANT_USERS")) {

            setUp(TestPopulation.instantUsers).protocols(httpProtocol);
        } else if (TEST_TYPE.equals("RAMP_USERS")) {

            setUp(TestPopulation.rampUsers).protocols(httpProtocol);

        } else if (TEST_TYPE.equals("OPEN_MODEL")) {


            setUp(TestPopulation.complexOpenModelInjectionProfile).protocols(httpProtocol);

        } else if (TEST_TYPE.equals("CLOSED_MODEL")) {


            setUp(TestPopulation.complexClosedModelInjectionProfile).protocols(httpProtocol);

        } else {

            setUp(TestPopulation.instantUsers).protocols(httpProtocol);

        }


    }


}
