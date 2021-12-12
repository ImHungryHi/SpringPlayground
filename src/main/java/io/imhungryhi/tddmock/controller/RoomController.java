package io.imhungryhi.tddmock.controller;

import io.imhungryhi.tddmock.dao.interfaces.RoomService;
import io.imhungryhi.tddmock.model.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rooms")
public class RoomController {
    @Autowired
    RoomService roomService;

    @GetMapping("/{id}")
    public ResponseEntity<Room> getRoomById(@PathVariable int id) throws Exception {
        Room room = roomService.findById(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        return new ResponseEntity<>(room, headers, HttpStatus.OK);
    }
}
