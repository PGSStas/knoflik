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

    @GetMapping("/{id}/isAdmin")
    public String isAdmin(@PathVariable final String id) {
        User user = userService.getLoggedUser();
        if (user.isAdmin()) {
            return "True";
        }
        return "False";
    }

    @PostMapping("/{id}/addUser")
    public void addUser(@PathVariable final String id) throws Exception {
        roomService.addUserToRoom(id);
    }
}
