package io.imhungryhi.tddmock.dao.interfaces;

import io.imhungryhi.tddmock.model.Booking;
import java.util.List;

public interface BookingService {
    Booking findById(int id);
    List<Booking> getAll();
}
