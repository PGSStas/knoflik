package com.knoflik.rest;

import com.knoflik.entities.Room;
import com.knoflik.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController
@RequestMapping("/api/rooms")
public class RoomController {
    @Autowired
    private RoomService roomService;

    @GetMapping
    public String getRooms() {
        return roomService.getAllRooms().toString();
    }

    @GetMapping("/new")
    public String createRoom() {
        return roomService.createRoom().getId();
    }

    @GetMapping("/{id}")
    public Room getRoomById(@PathVariable final String id) {
        return roomService.getRoomById(id);
    }

    @PostMapping("/{id}/addUser")
    public void addUser(@PathVariable final String id) throws Exception {
        roomService.addUserToRoom(id);
    }
}
