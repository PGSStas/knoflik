package com.knoflik.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.knoflik.entities.Room;
import com.knoflik.entities.User;
import com.knoflik.repositories.room.RoomRepository;
import com.knoflik.repositories.user.UserRepository;
import com.knoflik.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Random;
import java.util.Set;

@Controller
@RestController
@RequestMapping("/api/rooms")
public class RoomController {

    @Autowired
    private RoomService roomService;

    private final SimpMessagingTemplate template;

    @Autowired
    public RoomController(SimpMessagingTemplate template) {
        this.template = template;
    }

    @GetMapping
    public String getRooms() {
        return roomService.getAllRooms().toString();
    }

    @GetMapping("/new")
    public String createRoom() {
        return roomService.createRoom().getId();
    }

    @GetMapping("/{id}")
    public Room getRoomById(@PathVariable String id) {
        return roomService.getRoomById(id);
    }

    @PostMapping("/{id}/addUser")
    public void addUser(@PathVariable String id) throws Exception {
        if (roomService.addUserToRoom(id)) {
            this.template.convertAndSend("/topic/room/" + id,
                    (new ObjectMapper()).writeValueAsString(roomService.getAllUsersFromRoom(id)));
        }
    }
}
