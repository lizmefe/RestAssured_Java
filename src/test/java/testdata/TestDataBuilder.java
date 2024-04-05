package testdata;

import net.datafaker.Faker;

import java.time.LocalDate;

public class TestDataBuilder {

    private static final Faker FAKER = new Faker();

    public static BookingData getBookingData() {
        LocalDate startDate = LocalDate.now().minusDays(20);
        LocalDate endDate = LocalDate.now().plusDays(10);
        String firstName = FAKER.name().firstName();
        String lastName = FAKER.name().lastName();
        int randomNumber = FAKER.number().numberBetween(50, 2000);
        String foodDish = FAKER.food().dish();

        return createBookingData(firstName, lastName, randomNumber, startDate, endDate, foodDish);
    }

    private static BookingData createBookingData(String firstName, String lastName, int randomNumber,
            LocalDate startDate, LocalDate endDate, String foodIngredient) {
        BookingDates bookingDates = new BookingDates(startDate.toString(), endDate.toString());
        return new BookingData(firstName, lastName, randomNumber, true, bookingDates, foodIngredient);
    }

    public static AuthData getAuthData() {
        return new AuthData("admin", "password123");
    }

    public static PartialUpdateBookingData getPartialUpdateBookingData() {
        int randomNumber = FAKER.number().numberBetween(2000, 3000);
        String foodIngredient = FAKER.food().fruit();

        return new PartialUpdateBookingData(randomNumber, foodIngredient);
    }
}
