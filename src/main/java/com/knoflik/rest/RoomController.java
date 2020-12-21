package com.knoflik.rest;

import com.knoflik.entities.Room;
import com.knoflik.entities.User;
import com.knoflik.services.RoomService;
import com.knoflik.services.UserService;
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
    @Autowired
    private UserService userService;

    @GetMapping
    public String getRooms() {
        return roomService.getAllRooms().toString();
    }

    @GetMapping("/{id}")
    public Room getRoomById(@PathVariable final String id) {
        return roomService.getRoomById(id);
    }

    @PostMapping("/{id}/addUser")
    public void addUser(@PathVariable final String id) {
        roomService.addUserToRoom(id);
    }

    @GetMapping("/{id}.isAdmin")
    public boolean isAdmin(@PathVariable final String id) {
        User user = userService.getLoggedUser();
        User admin = roomService.getRoomById(id).getAdmin();
        return user.getId().equals(admin.getId());
    }
}
