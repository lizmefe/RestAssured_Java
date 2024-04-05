package testdata;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BookingDates {
    private final String checkin;
    private final String checkout;
}
