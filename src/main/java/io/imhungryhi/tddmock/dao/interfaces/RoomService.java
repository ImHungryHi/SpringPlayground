package io.imhungryhi.tddmock.dao.interfaces;

import io.imhungryhi.tddmock.model.Room;

public interface RoomService {
    Room findById(int id);
}
