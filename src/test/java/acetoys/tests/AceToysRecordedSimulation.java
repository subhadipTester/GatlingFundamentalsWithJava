package acetoys.tests;

import java.time.Duration;
import java.util.*;

import acetoys.pageobjects.*;
import acetoys.session.UserSession;
import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;
import io.gatling.javaapi.jdbc.*;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;
import static io.gatling.javaapi.jdbc.JdbcDsl.*;

public class AceToysRecordedSimulation extends Simulation {
    private static final String DOMAIN = "acetoys.uk";

    private HttpProtocolBuilder httpProtocol = http
            .baseUrl("https://" + DOMAIN)
            .inferHtmlResources(AllowList(), DenyList(".*\\.js", ".*\\.css", ".*\\.gif", ".*\\.jpeg", ".*\\.jpg", ".*\\.ico", ".*\\.woff", ".*\\.woff2", ".*\\.(t|o)tf", ".*\\.png", ".*detectportal\\.firefox\\.com.*"))
            .acceptEncodingHeader("gzip, deflate")
            .acceptLanguageHeader("en-US,en;q=0.9,bn;q=0.8");

    private ScenarioBuilder scn = scenario("AceToysRecordedSimulation")
            .exec(UserSession.initSession)
            .exec(StaticPages.homePage)
            .pause(2)
            .exec(StaticPages.ourStoryPage)
            .pause(2)
            .exec(StaticPages.getInTouchPage)
            .pause(2)
            .exec(Category.productListByCategory)
            .pause(2)
            .exec(Category.cyclePagesOfProducts)
            .pause(2)
            .exec(Products.loadProductDetailsPage)
            .pause(2)
            .exec(Products.addProductToCart)
            .pause(2)
            .exec(Category.productListByCategory)
            .pause(2)
            .exec(Products.addProductToCart)
            .pause(2)
            .exec(Cart.viewCart)
            .pause(2)
            .exec(Cart.increaseQuantityInCart)
            .pause(2)
            .exec(Cart.increaseQuantityInCart)
            .pause(2)
            .exec(Cart.increaseQuantityInCart)
            .pause(2)
            .exec(Cart.decreaseQuantityInCart)
            .pause(2)
            .exec(Cart.viewCart)
            .pause(2)
            .exec(Cart.checkout)
            .pause(2)
            .exec(Customer.logout);

    {
        setUp(scn.injectOpen(atOnceUsers(1))).protocols(httpProtocol);
    }
}
