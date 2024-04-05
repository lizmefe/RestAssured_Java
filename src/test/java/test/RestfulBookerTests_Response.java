package test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Interfaces.IServiceEndpoints;
import testdata.AuthData;
import testdata.BookingData;
import testdata.PartialUpdateBookingData;
import testdata.TestDataBuilder;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class RestfulBookerTests_Response extends BaseTest {

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

        Response response = given()
                .spec(requestSpec())
                .body(authData)
                .contentType(ContentType.JSON)
                .when()
                .post(IServiceEndpoints.AUTH);

        response.then()
                .spec(responseSpec())
                .and()
                .assertThat()
                .body("token", is(notNullValue()));

        return response.jsonPath().getString("token");
    }

    @Test
    public void createBookingTest() {
        Response response = given()
                .spec(requestSpec())
                .body(this.bookingData)
                .when()
                .post(IServiceEndpoints.BOOKING);

        this.bookingId = response.then()
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
        Response response = given()
                .spec(requestSpec())
                .when()
                .get(IServiceEndpoints.BOOKING_ID, this.bookingId);

        response.then()
                .spec(responseSpec());
    }

    @Test
    public void updateBookingTest() {
        final BookingData updateData = TestDataBuilder.getBookingData();

        Response response = given()
                .spec(requestSpec())
                .header("Cookie", "token=" + token)
                .body(updateData)
                .when()
                .put(IServiceEndpoints.BOOKING_ID, this.bookingId);

        response.then()
                .spec(responseSpec());
    }

    @Test
    public void updatePartialBookingTest() {
        final PartialUpdateBookingData partialUpdateData = TestDataBuilder.getPartialUpdateBookingData();

        Response response = given()
                .spec(requestSpec())
                .header("Cookie", "token=" + token)
                .body(partialUpdateData)
                .when()
                .patch(IServiceEndpoints.BOOKING_ID, this.bookingId);

        response.then()
                .spec(responseSpec())
                .body("totalprice", equalTo(partialUpdateData.getTotalprice()))
                .body("additionalneeds", equalTo(partialUpdateData.getAdditionalneeds()));
    }

    @Test
    public void deleteBookingTest() {
        Response response = given()
                .spec(requestSpec())
                .header("Cookie", "token=" + token)
                .when()
                .delete(IServiceEndpoints.BOOKING_ID, this.bookingId);

        response.then()
                .statusCode(201);
    }

    @Test
    public void deletedBookingTest() {
        Response response = given()
                .spec(requestSpec())
                .header("Accept", "application/json")
                .when()
                .get(IServiceEndpoints.BOOKING_ID, this.bookingId);

        response.then()
                .statusCode(404);
    }
}
