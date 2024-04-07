package testdata;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PartialUpdateBookingData {
    private int totalprice;
    private String additionalneeds;
}
