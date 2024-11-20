package test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Interfaces.IServiceEndpoints;
import io.restassured.http.ContentType;
import testdata.AuthData;
import testdata.BookingData;
import testdata.PartialUpdateBookingData;
import testdata.TestDataBuilder;

public class RestfulBookerTests extends BaseTest {

    private BookingData bookingData;
    private int bookingId;
    private String token;

    @BeforeClass
    public void setUp() {
        this.bookingData = TestDataBuilder.getBookingData();
        this.token = generateToken();
    }

    private String generateToken() {
        final AuthData authData = TestDataBuilder.getAuthData();

        return given()
            .spec(requestSpec())
            .body(authData)
            .header("Content-Type", ContentType.JSON)
            .when()
            .post(IServiceEndpoints.AUTH)
            .then()
            .spec(responseSpec())
            .and()
            .assertThat()
            .body("token", is(notNullValue()))
            .extract().path("token").toString();
    }

    @Test
    public void createBookingTest() {
        this.bookingId = given()
            .spec(requestSpec())
            .body(this.bookingData)
            .when()
            .post(IServiceEndpoints.BOOKING)
            .then()
            .statusCode(200)
            .and()
            .assertThat()
            .body("bookingid", is(notNullValue()))
            .body("booking.firstname", equalTo(this.bookingData.getFirstname()))
            .body("booking.bookingdates.checkin", equalTo(this.bookingData.getBookingdates().getCheckin()))
            .extract().path("bookingid");
    }

    @Test
    public void getBookingTest() {
        given()
            .spec(requestSpec())
            .when()
            .get(IServiceEndpoints.BOOKING_ID, this.bookingId)
            .then()
            .spec(responseSpec());
    }

    @Test
    public void updateBookingTest() {
        final BookingData updateData = TestDataBuilder.getBookingData();

        given()
            .spec(requestSpec())
            .header("Cookie", "token=" + token)
            .body(updateData)
            .when()
            .put(IServiceEndpoints.BOOKING_ID, this.bookingId)
            .then()
            .spec(responseSpec());
    }

    @Test
    public void updatePartialBookingTest() {
        final PartialUpdateBookingData partialUpdateData = TestDataBuilder.getPartialUpdateBookingData();

        given()
            .spec(requestSpec())
            .header("Cookie", "token=" + token)
            .body(partialUpdateData)
            .when()
            .patch(IServiceEndpoints.BOOKING_ID, this.bookingId)
            .then()
            .spec(responseSpec())
            .body("totalprice", equalTo(partialUpdateData.getTotalprice()))
            .body("additionalneeds", equalTo(partialUpdateData.getAdditionalneeds()));
    }

    @Test
    public void deleteBookingTest() {
        given()
            .spec(requestSpec())
            .header("Cookie", "token=" + token)
            .when()
            .delete(IServiceEndpoints.BOOKING_ID, this.bookingId)
            .then()
            .statusCode(201);
    }

    @Test
    public void deletedBookingTest() {
        given()
            .spec(requestSpec())
            .header("Accept", "application/json")
            .when()
            .get(IServiceEndpoints.BOOKING_ID, this.bookingId)
            .then()
            .statusCode(404);
    }
}