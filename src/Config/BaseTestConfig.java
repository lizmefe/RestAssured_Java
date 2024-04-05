import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static org.hamcrest.Matchers.lessThan;

public class BaseTestConfig {

    protected static RequestSpecification requestSpecification = new RequestSpecBuilder()
            .setBaseUri("http://localhost:3001")
            .addHeader("Content-Type", "application/json")
            .addHeader("Accept", "application/json")
            .addFilter(new RequestLoggingFilter())
            .addFilter(new ResponseLoggingFilter())
            .build();

    ResponseSpecification responseSpecification = new ResponseSpecBuilder().expectResponseTime(lessThan(20000L))
            .build();

    protected static ResponseSpecification responseSpec() {
        final ResponseSpecBuilder responseBuilder = new ResponseSpecBuilder();
        return responseBuilder.expectStatusCode(200)
                .expectHeader("Content-Type", "application/json; charset=utf-8")
                .build();

    }
}