package acetoys.tests;

import java.time.Duration;
import java.util.*;

import acetoys.pageobjects.*;
import acetoys.session.UserSession;
import acetoys.simulation.UserJourney;
import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;
import io.gatling.javaapi.jdbc.*;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;
import static io.gatling.javaapi.jdbc.JdbcDsl.*;

public class AceToysSimulationExample extends Simulation {

    private static final String DOMAIN = "acetoys.uk";

    private HttpProtocolBuilder httpProtocol = http
            .baseUrl("https://" + DOMAIN)
            .inferHtmlResources(AllowList(), DenyList(".*\\.js", ".*\\.css", ".*\\.gif", ".*\\.jpeg", ".*\\.jpg", ".*\\.ico", ".*\\.woff", ".*\\.woff2", ".*\\.(t|o)tf", ".*\\.png", ".*detectportal\\.firefox\\.com.*"))
            .acceptEncodingHeader("gzip, deflate")
            .acceptLanguageHeader("en-US,en;q=0.9,bn;q=0.8");

    private ScenarioBuilder scenario = scenario("AceToys Simulation Examples")
            .exec(UserJourney.completePurchase);

    {
        setUp(scenario.injectOpen(atOnceUsers(1))).protocols(httpProtocol);
    }


}
