package com.knoflik.rest;

import com.knoflik.entities.Room;
import com.knoflik.repositories.room.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Random;

@Controller
@RestController
@RequestMapping("/api/rooms")
public class RoomController {
    @Autowired
    private RoomRepository roomRepository;

    private final HashMap<String, Integer> usersInRooms = new HashMap<>();
    private final Random rnd = new Random();
    private final SimpMessagingTemplate template;

    @Autowired
    public RoomController(SimpMessagingTemplate template) {
        this.template = template;
    }

    @GetMapping
    public String getRooms() {
        return roomRepository.findAll().toString();
    }

    @GetMapping("/new")
    public String createRoom() {
        Room room = new Room();
        roomRepository.save(room);
        usersInRooms.put(room.getId(), 0);
        return room.getId();
    }

    @GetMapping("/{id}")
    public String getRoom(@PathVariable String id) {
        if (roomRepository.existsById(id)) {
            return "200";
        } else {
            return "404";
        }
    }

    @PostMapping("/{id}/addUser")
    public void addUser(@PathVariable String id) {
        usersInRooms.put(id, usersInRooms.get(id) + 1);
        this.template.convertAndSend("/topic/room/"+id, usersInRooms.get(id));
    }
}
