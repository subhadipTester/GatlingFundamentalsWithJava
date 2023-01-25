package acetoys.pageobjects;

import acetoys.session.UserSession;
import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;

import static acetoys.session.UserSession.increaseSessionBasketTotal;
import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

public class Products {

    private static final FeederBuilder<Object> productFeeder = jsonFile("data/productDetails.json").random();

    public static ChainBuilder loadProductDetailsPage =
            feed(productFeeder)
                    .exec(http("Load Product Details Page - Product : #{name}")
                            .get("/product/#{slug}")
                            .check(css("#ProductDescription").isEL("#{description}"))
                    );

    public static ChainBuilder addProductToCart =
            exec(UserSession.increaseItemsInBasketForSession)
            .exec(http("Add Product to Cart - Product Name : #{name}")
                    .get("/cart/add/#{id}")
                    .check(substring("You have <span>#{itemsInBasket}</span> products in your Basket")))
            .exec(increaseSessionBasketTotal);

}
