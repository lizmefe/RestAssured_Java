import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;

public class RestfulBookerTests {
    private record UserData(String firstname,
                            String lastname,
                            int totalprice,
                            boolean depositpaid,
                            BookingDates bookingdates,
                            String additionalneeds) {
    }

    private record BookingDates(String checkin, String checkout) {
    }

    private record AuthData(String username, String password) {
    }

    @Test
    public void testCreateBooking() {

        final String firstName = "Jim";
        final UserData userData = new UserData(firstName, "Brown",
                111, true, new BookingDates("2018-01-01", "2019-01-01"), "Breakfast");

        given().body(userData)
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .when()
                .log().all()
                .post("http://localhost:3001/booking")
                .then()
                .log().all()
                .statusCode(200)
                .body("bookingid", is(notNullValue()))
                .body("booking.firstname", equalTo(firstName))
                .body("booking.bookingdates.checkin", equalTo("2018-01-01"));
    }

    @Test
    public void testGetBooking() {
        given().header("Accept", "application/json")
                .when()
                .log().all()
                .get("http://localhost:3001/booking/1")
                .then()
                .log().all()
                .statusCode(200);
    }

    @Test
    public void TestUpdateBooking()
    {
        final UserData updateUserData = new UserData("Joseph", "DaFoe", 141, true, new BookingDates("2022-01-01", "2022-01-10"), "Dinner");

        given().header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .header("Cookie", "token=" + authToken())
                .body(updateUserData)
                .when()
                .log().all()
                .put("http://localhost:3001/booking/1")
                .then()
                .log().all()
                .statusCode(200);

    }

    @Test
    public String authToken() {

        final AuthData authData = new AuthData("admin", "password123");

        return given().body(authData)
        .header("Content-Type", ContentType.JSON)
        .when()
        .log().all()
        .post("http://localhost:3001/auth")
        .then()
        .log().all()
        .statusCode(200)
        .body("token", is(notNullValue()))
        .extract().path("token").toString();

    }
}