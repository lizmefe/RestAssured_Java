package testdata;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PartialUpdateBookingData {
    private final int totalprice;
    private final String additionalneeds;
}
