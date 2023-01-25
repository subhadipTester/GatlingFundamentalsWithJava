package acetoys.simulation;
import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;
import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;
public class TestScenario {

public static ScenarioBuilder defaultLoadTest =

        scenario("Default Load TestSuite")
        .during(60)
        .on(

                randomSwitch().on(

                  Choice.withWeight(60, exec(UserJourney.browseStore)),
                  Choice.withWeight(30, exec(UserJourney.completePurchase)),
                  Choice.withWeight(10,exec(UserJourney.abandonBasket))

                )




        );

public static ScenarioBuilder highPurchaseLoadTest =

        scenario("High Volume Load TestSuite")
        .during(180)
        .on(

                randomSwitch().on(

                 Choice.withWeight(30, exec(UserJourney.browseStore)),
                 Choice.withWeight(40, exec(UserJourney.completePurchase)),
                 Choice.withWeight(30,exec(UserJourney.abandonBasket))



                )




        );




}
