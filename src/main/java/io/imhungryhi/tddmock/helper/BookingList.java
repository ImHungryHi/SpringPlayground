package io.imhungryhi.tddmock.helper;

import io.imhungryhi.tddmock.model.Booking;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@AllArgsConstructor @Getter @Setter
public class BookingList {
    private List<Booking> bookings;
}
