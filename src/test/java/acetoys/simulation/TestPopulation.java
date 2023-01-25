package acetoys.simulation;

import static io.gatling.javaapi.core.CoreDsl.*;
import io.gatling.javaapi.core.PopulationBuilder;

import java.time.Duration;

public class TestPopulation {


private static final int USER_COUNT = Integer.parseInt(System.getenv("USERS"));
private static final Duration RAMP_DURATION = Duration.ofSeconds(Integer.parseInt(System.getenv("RAMP_DURATION")));

public static PopulationBuilder instantUsers =

        TestScenario.defaultLoadTest.injectOpen(

                nothingFor(5),
                atOnceUsers(USER_COUNT));

public static PopulationBuilder rampUsers =

        TestScenario.defaultLoadTest.injectOpen(

                nothingFor(5),
                rampUsers(USER_COUNT).during(RAMP_DURATION)
        );

public static PopulationBuilder complexOpenModelInjectionProfile =

        TestScenario.defaultLoadTest.injectOpen(

                constantUsersPerSec(10).during(20).randomized(),
                rampUsersPerSec(10).to(20).during(20).randomized()
        );

public static PopulationBuilder complexClosedModelInjectionProfile =

        TestScenario.highPurchaseLoadTest.injectClosed(

          constantConcurrentUsers(10).during(20),
          rampConcurrentUsers(10).to(20).during(20)


        );




}
