package steps;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import Interfaces.IServiceEndpoints;
import testdata.AuthData;
import testdata.BookingData;
import testdata.PartialUpdateBookingData;
import testdata.TestDataBuilder;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import net.serenitybdd.annotations.Step;
import test.BaseTest;

public class RestfulBookerSteps extends BaseTest {

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

    @Step("Create Booking")
    public void createBooking() {
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

    @Step("Get Booking")
    public void getBooking() {
        Response response = given()
                .spec(requestSpec())
                .when()
                .get(IServiceEndpoints.BOOKING_ID, this.bookingId);

        response.then()
                .spec(responseSpec());
    }

    @Step("Update Booking")
    public void updateBooking() {
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

    @Step("Delete Booking")
    public void deleteBooking() {
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
}