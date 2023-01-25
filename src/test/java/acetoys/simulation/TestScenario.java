package acetoys.simulation;

import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;

import java.time.Duration;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

public class TestScenario {
    private static final Duration TEST_DURATION = Duration.ofSeconds(Integer.parseInt(System.getenv("DURATION")));

    public static ScenarioBuilder defaultLoadTest =


            scenario("Default Load TestSuite")
                    .during(TEST_DURATION)
                    .on(

                            randomSwitch().on(

                                    Choice.withWeight(60, exec(UserJourney.browseStore)),
                                    Choice.withWeight(30, exec(UserJourney.completePurchase)),
                                    Choice.withWeight(10, exec(UserJourney.abandonBasket))

                            )


                    );

    public static ScenarioBuilder highPurchaseLoadTest =

            scenario("High Volume Load TestSuite")
                    .during(TEST_DURATION)
                    .on(

                            randomSwitch().on(

                                    Choice.withWeight(30, exec(UserJourney.browseStore)),
                                    Choice.withWeight(40, exec(UserJourney.completePurchase)),
                                    Choice.withWeight(30, exec(UserJourney.abandonBasket))


                            )


                    );


}
